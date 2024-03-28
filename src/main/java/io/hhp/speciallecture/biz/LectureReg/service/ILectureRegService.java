package io.hhp.speciallecture.biz.LectureReg.service;


import io.hhp.speciallecture.biz.LectureReg.dto.LectureRegRequestDto;
import io.hhp.speciallecture.biz.LectureReg.dto.LectureRegResponseDto;

/**
 * The interface Lecture reg service.
 */
public interface ILectureRegService {

    /**
     * 수강신청 서비스
     *
     * @param lectureRegRequestDto the lecture reg request dto
     * @return the lecture reg response dto
     */
    public LectureRegResponseDto registerForLecture(LectureRegRequestDto lectureRegRequestDto);


    /**
     * 수강신청 여부 확인서비스
     *
     * @param userId    the user id
     * @param lectureId the lecture id
     * @return the list
     */
    LectureRegResponseDto checkIsRegisterForLectureByUserId(Long userId, Long lectureId);

}
