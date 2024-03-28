package io.hhp.speciallecture.service;


import io.hhp.speciallecture.biz.Lecture.domain.Lecture;
import io.hhp.speciallecture.biz.Lecture.repository.LectureRepository;
import io.hhp.speciallecture.biz.LectureReg.domain.LectureReg;
import io.hhp.speciallecture.biz.LectureReg.dto.LectureRegRequestDto;
import io.hhp.speciallecture.biz.LectureReg.dto.LectureRegResponseDto;
import io.hhp.speciallecture.biz.LectureReg.repository.LectureRegRepository;
import io.hhp.speciallecture.biz.LectureReg.service.LectureRegService;
import io.hhp.speciallecture.common.exception.LectureErrorResult;
import io.hhp.speciallecture.common.exception.LectureException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doReturn;

@DisplayName("수강신청 서비스 테스트")
@ExtendWith(MockitoExtension.class)
public class LectureRegServiceTest {

    @InjectMocks
    private LectureRegService lectureRegService;

    @Mock
    private LectureRegRepository lectureRegRepository;

    @Mock
    private LectureRepository lectureRepository;


    @DisplayName("Not null check")
    @Test()
    public void givenNothing_whenCheckNotnull_thenNotnull(){
        // given

        // when

        // then
        assertThat(lectureRegService).isNotNull();
        assertThat(lectureRegRepository).isNotNull();
    }

    /*
     * 특강 3월20일 ~ 4월28일 open
     * */
    @DisplayName("[성공] 수강신청 성공")
    @Test()
    public void givenUserIdAndLectureId_whenRegisterForLecture_thenSuccessfullyRegister(){
        // given

        Long userId = 1L;
        Long lectureId = 1L;
        Optional<Lecture> lecture = Optional.of(Lecture.of("백엔드강의"
                , "백엔드 능력을 배양하기 위한 강의"
                , LocalDateTime.of(2024, 3, 20, 0, 0, 0)
                , LocalDateTime.of(2024, 4, 28, 0, 0, 0)
                ,10));

        LectureRegRequestDto lectureRegRequestDto = getLectureRegReqDto(userId, lectureId);

        /*
         * 특강정보 테이블에 특강이 등록되어 있는지 확인
         * */
        doReturn(lecture).when(lectureRepository).findById(lectureId);

        /*
         * 이미 신청된 특강인지 확인 -  이미 신청된 특강이 아님
         * */
        doReturn(Optional.empty()).when(lectureRegRepository).findByUserIdAndLectureId(userId, lectureId);

        /*
         * 해당 특강을 수강한 학생수를 반환
         * */
        doReturn(10).when(lectureRegRepository).countByLectureId(lectureId);

        /*
         * 수강신청
         * */
        doReturn(LectureReg.of(lectureId,userId)).when(lectureRegRepository).save(LectureReg.of(lectureId,userId));


        // when
        LectureRegResponseDto lectureRegResponseDto = lectureRegService.registerForLecture(lectureRegRequestDto);

        // then
        assertThat(lectureRegResponseDto.getUserId()).isEqualTo(userId);

    }

    @DisplayName("[실패] 수강신청 실패 - 특강이 없을경우")
    @Test()
    public void givenUserIdAndLectureId_whenRegisterForLecture_thenThrowNotFoundLecture(){
        // given

        Long userId = 1L;
        Long lectureId = 1L;
        LectureRegRequestDto lectureRegRequestDto = getLectureRegReqDto(userId, lectureId);

        /*
         * 특강 정보 테이블에 해당 특강이 등록되어 있지 않아 에러
         * */
        doReturn(Optional.empty()).when(lectureRepository).findById(lectureId);

        // when
        LectureException lectureException = assertThrows(LectureException.class, () -> lectureRegService.registerForLecture(lectureRegRequestDto));

        // then
        // 등록하려는 특강이 존재하지 않음
        assertThat(lectureException.getErrorResult()).isEqualTo(LectureErrorResult.NOT_FOUND_LECTURE);
    }

    @DisplayName("[실패] 수강신청 실패 - 특강을 이미 수강한 경우")
    @Test()
    public void givenUserIdAndLectureId_whenRegisterForLecture_thenThrowAlreadyRegister(){
        // given

        Long userId = 1L;
        Long lectureId = 1L;
        Optional<Lecture> lecture = Optional.of(Lecture.of("백엔드강의"
                , "백엔드 능력을 배양하기 위한 강의"
                , LocalDateTime.of(2024, 3, 20, 0, 0, 0)
                , LocalDateTime.of(2024, 4, 28, 0, 0, 0)
                ,10));
        LectureRegRequestDto lectureRegRequestDto = getLectureRegReqDto(userId, lectureId);

        /*
         * 특강정보 테이블에 특강이 등록되어 있음
         * */
        doReturn(lecture).when(lectureRepository).findById(lectureId);

        /*
         * 이미 신청된 특강인지 확인 -  이미 신청된 특강임
         * */
        doReturn(Optional.of(LectureReg.of(lectureId,userId))).when(lectureRegRepository).findByUserIdAndLectureId(userId, lectureId);

        // when

        LectureException lectureException = assertThrows(LectureException.class, () -> lectureRegService.registerForLecture(lectureRegRequestDto));

        // then
        assertThat(lectureException.getErrorResult()).isEqualTo(LectureErrorResult.ALREADY_REGISTER);
    }

    @DisplayName("[실패] 수강신청 실패 - 정원초과")
    @Test()
    public void givenUserIdAndLectureId_whenRegisterForLecture_thenThrowNumOfPeopleExceed(){
        // given

        Long userId = 1L;
        Long lectureId = 1L;
        Optional<Lecture> lecture = Optional.of(Lecture.of("백엔드강의"
                , "백엔드 능력을 배양하기 위한 강의"
                , LocalDateTime.of(2024, 3, 20, 0, 0, 0)
                , LocalDateTime.of(2024, 4, 28, 0, 0, 0)
                ,30));
        LectureRegRequestDto lectureRegRequestDto = getLectureRegReqDto(userId, lectureId);

        /*
         * 특강정보 테이블에 특강이 등록되어 있음
         * */
        doReturn(lecture).when(lectureRepository).findById(lectureId);

        /*
         * 이미 신청된 특강인지 확인 -  이미 신청된 특강이 아님
         * */
        doReturn(Optional.empty()).when(lectureRegRepository).findByUserIdAndLectureId(userId, lectureId);

        /*
         * 정원초과 됨 (30명 넘음)
         * */
        doReturn(30).when(lectureRegRepository).countByLectureId(lectureId);

        // when

        LectureException lectureException = assertThrows(LectureException.class, () -> lectureRegService.registerForLecture(lectureRegRequestDto));

        // then
        assertThat(lectureException.getErrorResult()).isEqualTo(LectureErrorResult.NUM_OF_PEOPLE_EXCEED);
    }

    @DisplayName("[실패] 수강신청 실패 - 수강기간이 아님")
    @Test()
    public void givenUserIdAndLectureId_whenRegisterForLecture_thenThrowNotPeriod(){
        // given

        Long userId = 1L;
        Long lectureId = 1L;
        Optional<Lecture> lecture = Optional.of(Lecture.of("백엔드강의"
                , "백엔드 능력을 배양하기 위한 강의"
                , LocalDateTime.of(2024, 3, 20, 0, 0, 0)
                , LocalDateTime.of(2024, 3, 21, 0, 0, 0)
                ,10));
        LectureRegRequestDto lectureRegRequestDto = getLectureRegReqDto(userId, lectureId);

        /*
         * 특강정보 테이블에 특강이 등록되어 있음
         * */
        doReturn(lecture).when(lectureRepository).findById(lectureId);

        // when

        LectureException lectureException = assertThrows(LectureException.class, () -> lectureRegService.registerForLecture(lectureRegRequestDto));

        // then
        assertThat(lectureException.getErrorResult()).isEqualTo(LectureErrorResult.CANNOT_REGISTER_FOR_THE_PERIOD);
    }

    @DisplayName("[성공] 특강 등록여부 확인")
    @Test()
    public void givenUserId_whenGetRegisterForLectureByUserId_thenLectureRegList(){
        // given
        Long userId = 1L;
        Long lectureId = 1L;
        doReturn(Optional.of(LectureReg.of(lectureId,userId))).when(lectureRegRepository).findByUserIdAndLectureId(userId,lectureId);

        // when
        LectureRegResponseDto lectureReg = lectureRegService.checkIsRegisterForLectureByUserId(userId,lectureId);

        // then
        assertThat(lectureReg).isNotNull();
    }

    private LectureRegRequestDto getLectureRegReqDto(Long userId, Long lectureId){
        return LectureRegRequestDto
                .builder()
                .userId(userId)
                .LectureId(lectureId)
                .build();
    }

    private LectureRegResponseDto getLectureRegResDto(Long id, Long userId, Long lectureId){
        return LectureRegResponseDto
                .builder()
                .id(id)
                .userId(userId)
                .LectureId(lectureId)
                .build();
    }

}
