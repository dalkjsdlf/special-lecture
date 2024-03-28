package io.hhp.speciallecture.biz.Lecture.service;


import io.hhp.speciallecture.biz.Lecture.dto.LectureResponseDto;
import io.hhp.speciallecture.biz.Lecture.repository.ILectureRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LectureService implements ILectureService {

    ILectureRepository lectureRepository;

    @Override
    public List<LectureResponseDto> getAllLectures() {
        return null;
    }

    @Override
    public LectureResponseDto getLectureById(Long id) {
        return null;
    }

    @Override
    public LectureResponseDto addLecture(Long id, String name, String desc, LocalDateTime startDate, LocalDateTime endDate) {
        return null;
    }

    @Override
    public LectureResponseDto modifyAllLectureById(Long id, String name, String desc, LocalDateTime startDate, LocalDateTime endDate) {
        return null;
    }

    @Override
    public LectureResponseDto deleteAllLectureById(Long id) {
        return null;
    }
}
