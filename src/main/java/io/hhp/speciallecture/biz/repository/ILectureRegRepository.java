package io.hhp.speciallecture.biz.repository;

import io.hhp.speciallecture.biz.domain.LectureReg;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ILectureRegRepository{
    public List<LectureReg> findAll();
    public LectureReg findByUserId(Long userId);
    public LectureReg findById(Long id);
    public LectureReg save(LectureReg lectureReg);
    public LectureReg delete(Long id);
}
