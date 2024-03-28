package io.hhp.speciallecture.biz.LectureReg.repository;


import io.hhp.speciallecture.biz.LectureReg.domain.LectureReg;

import java.util.List;
import java.util.Optional;


public interface ILectureRegRepository{
    public List<LectureReg> findAll();
    public List<LectureReg> findByUserId(Long userId);

    public Optional<LectureReg> findByUserIdAndLectureId(Long userId, Long lectureId);

    public Optional<LectureReg> findById(Long id);

    public Integer countByLectureId(Long lectureId);

    public LectureReg save(LectureReg lectureReg);

    void delete(LectureReg lectureReg);

}
