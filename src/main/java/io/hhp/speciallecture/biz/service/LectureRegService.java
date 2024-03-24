package io.hhp.speciallecture.biz.service;

import io.hhp.speciallecture.biz.dto.LectureRegRequestDto;
import io.hhp.speciallecture.biz.dto.LectureRegResponseDto;
import io.hhp.speciallecture.biz.repository.ILectureRegRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class LectureRegService implements ILectureRegService{

    ILectureRegRepository lectureRegPository;


    @Override
    public LectureRegResponseDto registerForLecture(LectureRegRequestDto lectureRegRequestDto) {
        return null;
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
