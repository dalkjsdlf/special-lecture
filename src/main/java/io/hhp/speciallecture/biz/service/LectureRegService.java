package io.hhp.speciallecture.biz.service;

import io.hhp.speciallecture.biz.dto.LectureRegRequestDto;
import io.hhp.speciallecture.biz.dto.LectureRegResponseDto;
import io.hhp.speciallecture.biz.repository.ILectureRegRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class LectureRegService implements ILectureRegService{

    ILectureRegRepository lectureRegPository;


    @Transactional()
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
