package io.hhp.speciallecture.biz.repository;

import io.hhp.speciallecture.biz.domain.LectureReg;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface ILectureRegRepository{
    public List<LectureReg> findAll();
    public List<LectureReg> findByUserId(Long userId);

    public Optional<LectureReg> findByUserIdAndLectureId(Long userId, Long lectureId);

    public Optional<LectureReg> findById(Long id);

    public Integer countByLectureId(Long lectureId);

    public LectureReg save(LectureReg lectureReg);
    public LectureReg delete(Long id);
}
