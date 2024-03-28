package io.hhp.speciallecture.stub;


import io.hhp.speciallecture.biz.LectureReg.dto.LectureRegRequestDto;
import io.hhp.speciallecture.biz.LectureReg.dto.LectureRegResponseDto;
import io.hhp.speciallecture.biz.LectureReg.service.ILectureRegService;

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
    public LectureRegResponseDto checkIsRegisterForLectureByUserId(Long userId, Long lectureId) {
        return null;
    }
}
