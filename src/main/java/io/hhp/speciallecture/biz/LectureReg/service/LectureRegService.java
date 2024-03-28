package io.hhp.speciallecture.biz.LectureReg.service;


import io.hhp.speciallecture.biz.Lecture.domain.Lecture;
import io.hhp.speciallecture.biz.Lecture.repository.ILectureRepository;
import io.hhp.speciallecture.biz.LectureReg.domain.LectureReg;
import io.hhp.speciallecture.biz.LectureReg.dto.LectureRegRequestDto;
import io.hhp.speciallecture.biz.LectureReg.dto.LectureRegResponseDto;
import io.hhp.speciallecture.biz.LectureReg.repository.ILectureRegRepository;
import io.hhp.speciallecture.common.exception.LectureErrorResult;
import io.hhp.speciallecture.common.exception.LectureException;
import jakarta.persistence.LockModeType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * The type Lecture reg service.
 */
@Service
@Transactional(readOnly = true)
public class LectureRegService implements ILectureRegService {

    private final ILectureRegRepository lectureRegRepository;
    private final ILectureRepository lectureRepository;

    private final int CONST_NUM_OF_PEOPLE = 30;

    private final Logger logger = LoggerFactory.getLogger(LectureRegService.class);

    /**
     * 특강 수강신청하는 서비스
     *
     * @param lectureRegRepository the lecture reg repository
     * @param lectureRepository    the lecture repository
     */
    public LectureRegService(@Autowired ILectureRegRepository lectureRegRepository,
                             @Autowired ILectureRepository lectureRepository) {
        this.lectureRegRepository = lectureRegRepository;
        this.lectureRepository = lectureRepository;
    }


    @Transactional
    @Override
    public LectureRegResponseDto registerForLecture(LectureRegRequestDto lectureRegRequestDto) {


        Long lectureId = lectureRegRequestDto.getLectureId();
        Long userId    = lectureRegRequestDto.getUserId();

        logger.info("USER_ID:[{}],    LECTURE_ID:[{}]",userId,lectureId);
        /*
         * Validation Check
         * 아이디가 NULL이 아닌지 검사
         */
        if(userId == null){
            throw new LectureException(LectureErrorResult.WRONG_USER_ID);
        }

        /*
         * Validation Check
         * 특강 ID가 NULL이 아닌지 검사
         */
        if(lectureId == null){
            throw new LectureException(LectureErrorResult.WRONG_LECTURE_ID);
        }

        /*
         * Validation Check
         * 특강이 존재하는지 검사
         */
        Optional<Lecture> optionalLecture = lectureRepository.findById(lectureId);
        Lecture lecture = optionalLecture.orElseThrow(()-> new LectureException(LectureErrorResult.NOT_FOUND_LECTURE));

        /*
         * Validation Check
         * 특강 시작일 비교
         * 특강 마감일 비교
         */
        LocalDateTime today = LocalDateTime.now();
        LocalDateTime startDate = lecture.getRegStartDate();
        LocalDateTime endDate   = lecture.getRegEndDate();

        if(startDate.isAfter(today) || endDate.isBefore(today)){
            throw new LectureException(LectureErrorResult.CANNOT_REGISTER_FOR_THE_PERIOD);
        }

        /*
         * Validation Check
         * 이미 해당 사용자가 특강이 신청된것은 아닌지
         */
        Optional<LectureReg> optionalLectureReg = lectureRegRepository.findByUserIdAndLectureId(userId,lectureId);
        optionalLectureReg.ifPresent(item -> {
            throw new LectureException(LectureErrorResult.ALREADY_REGISTER);
        });

        /*
         * Validation Check
         * 정원 초과 됐는지
         */
        int numOfPeople = lectureRegRepository.countByLectureId(lectureId);
        if(numOfPeople >= CONST_NUM_OF_PEOPLE){
            throw new LectureException(LectureErrorResult.NUM_OF_PEOPLE_EXCEED);
        }

        /*
         * 수강신청
         */
        LectureReg result = lectureRegRepository.save(LectureReg.of(lectureId,userId));

        int incNumOfStudents = lecture.getNumOfStudents() + 1;
        lecture.setNumOfStudents(incNumOfStudents);

        logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>  userId [{}] ,    incNumOfStudents [{}] ",userId,incNumOfStudents);
        /*
         * 수강인원 추가
         */
        lectureRepository.save(lecture);

        return convertLectureRegToResDto(result.getId(), result.getUserId(), result.getLectureId());
    }



    @Override
    public LectureRegResponseDto checkIsRegisterForLectureByUserId(Long userId, Long lectureId) {
        /*
         * Validation Check
         * 아이디가 NULL이 아닌지 검사
         */
        if(userId == null){
            throw new LectureException(LectureErrorResult.WRONG_USER_ID);
        }

        /*
         * 특정 사용자ID와 특강 ID로 수강 신청 여부 확인
         * 수강신청 되어있으면 객체 반환, 되어 있지 않으면 LECTURE_NOT_REGISTERED Exception 발생
         **/
        Optional<LectureReg> optLectureRegList = lectureRegRepository.findByUserIdAndLectureId(userId,lectureId);

        LectureReg lectureReg = optLectureRegList.orElseThrow(() -> new LectureException(LectureErrorResult.LECTURE_NOT_REGISTERED));

        return convertLectureRegToResDto(lectureReg.getId(), lectureReg.getUserId(),lectureReg.getLectureId());
    }

    private LectureRegResponseDto convertLectureRegToResDto(Long id, Long userId, Long lectureId){
        return LectureRegResponseDto
                .builder()
                .id(id)
                .userId(userId)
                .LectureId(lectureId)
                .build();
    }

    private List<LectureRegResponseDto> convertLectureRegsToResDtoList(List<LectureReg> lectureRegList){
        return lectureRegList.stream().map(item->LectureRegResponseDto
                .builder()
                .id(item.getId())
                .userId(item.getUserId())
                .LectureId(item.getLectureId())
                .build()).collect(Collectors.toList());

    }
}
