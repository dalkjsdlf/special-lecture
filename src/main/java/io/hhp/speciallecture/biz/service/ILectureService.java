package io.hhp.speciallecture.biz.service;

import io.hhp.speciallecture.biz.dto.LectureResponseDto;

import java.time.LocalDateTime;
import java.util.List;

public interface ILectureService {
    public List<LectureResponseDto> getAllLectures();
    public LectureResponseDto getLectureById(Long id);
    public LectureResponseDto addLecture(Long id, String name, String desc, LocalDateTime startDate, LocalDateTime endDate);
    public LectureResponseDto modifyAllLectureById(Long id, String name, String desc, LocalDateTime startDate,LocalDateTime endDate);
    public LectureResponseDto deleteAllLectureById(Long id);
}
