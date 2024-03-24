package io.hhp.speciallecture.stub;

import io.hhp.speciallecture.biz.dto.LectureRegRequestDto;
import io.hhp.speciallecture.biz.dto.LectureRegResponseDto;
import io.hhp.speciallecture.biz.service.ILectureRegService;

import java.util.List;

public class LectureRegServiceStub implements ILectureRegService {

    private LectureRegResponseDto lectureRegResponseDto;
    private List<LectureRegResponseDto> lectureRegResponseDtoList;

    public void setReturn(LectureRegResponseDto lectureRegResponseDto){
        this.lectureRegResponseDto = lectureRegResponseDto;
    }

    public void setReturnList(List<LectureRegResponseDto> lectureRegResponseDtoList){
        this.lectureRegResponseDtoList = lectureRegResponseDtoList;
    }

    @Override
    public LectureRegResponseDto registerForLecture(LectureRegRequestDto lectureRegRequestDto) {
        return lectureRegResponseDto;
    }

    @Override
    public LectureRegResponseDto getIsRegisterForLectureByUserId(Long userId) {
        return null;
    }

    @Override
    public List<LectureRegResponseDto> getAllRegisterForLecture() {
        return null;
    }
}
