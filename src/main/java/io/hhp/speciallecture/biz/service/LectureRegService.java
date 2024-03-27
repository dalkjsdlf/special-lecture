package io.hhp.speciallecture.biz.service;

import io.hhp.speciallecture.biz.domain.Lecture;
import io.hhp.speciallecture.biz.domain.LectureReg;
import io.hhp.speciallecture.biz.dto.LectureRegRequestDto;
import io.hhp.speciallecture.biz.dto.LectureRegResponseDto;
import io.hhp.speciallecture.biz.repository.ILectureRegRepository;
import io.hhp.speciallecture.biz.repository.ILectureRepository;
import io.hhp.speciallecture.common.exception.LectureErrorResult;
import io.hhp.speciallecture.common.exception.LectureException;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class LectureRegService implements ILectureRegService{

    private final ILectureRegRepository lectureRegRepository;
    private final ILectureRepository lectureRepository;

    private final int CONST_NUM_OF_PEOPLE = 30;

    public LectureRegService(@Autowired ILectureRegRepository lectureRegRepository,
                             @Autowired ILectureRepository lectureRepository) {
        this.lectureRegRepository = lectureRegRepository;
        this.lectureRepository = lectureRepository;
    }

    @Transactional()
    @Override
    public LectureRegResponseDto registerForLecture(LectureRegRequestDto lectureRegRequestDto) {

        Long lectureId = lectureRegRequestDto.getLectureId();
        Long userId    = lectureRegRequestDto.getUserId();

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

        lectureRepository.save(lecture);

        return convertLectureRegToResDto(result.getId(), result.getUserId(), result.getLectureId());
    }

    @Override
    public List<LectureRegResponseDto> getRegisterForLectureByUserId(Long userId) {
        /*
         * Validation Check
         * 아이디가 NULL이 아닌지 검사
         */
        if(userId == null){
            throw new LectureException(LectureErrorResult.WRONG_USER_ID);
        }

        List<LectureReg> lectureRegList = lectureRegRepository.findByUserId(userId);

        return convertLectureRegsToResDtoList(lectureRegList);
    }


    @Override
    public List<LectureRegResponseDto> getAllRegisterForLecture() {
        return null;
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
