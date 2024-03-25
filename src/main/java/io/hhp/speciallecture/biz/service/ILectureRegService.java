package io.hhp.speciallecture.biz.service;

import io.hhp.speciallecture.biz.dto.LectureRegRequestDto;
import io.hhp.speciallecture.biz.dto.LectureRegResponseDto;

import java.util.List;

public interface ILectureRegService {

    public LectureRegResponseDto registerForLecture(LectureRegRequestDto lectureRegRequestDto);
    public List<LectureRegResponseDto> getRegisterForLectureByUserId(Long userId);
    public List<LectureRegResponseDto> getAllRegisterForLecture();

}
