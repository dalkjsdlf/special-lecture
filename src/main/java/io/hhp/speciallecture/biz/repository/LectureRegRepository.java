package io.hhp.speciallecture.biz.repository;

import io.hhp.speciallecture.biz.domain.LectureReg;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class LectureRegRepository implements ILectureRegRepository{

    ILectureRegRepository lectureRegRepository;

    public LectureRegRepository(@Autowired ILectureRegRepository lectureRegRepository) {
        this.lectureRegRepository = lectureRegRepository;
    }

    @Override
    public List<LectureReg> findAll() {
        return lectureRegRepository.findAll();
    }

    @Override
    public LectureReg findByUserId(Long userId) {
        return lectureRegRepository.findByUserId(userId);
    }

    @Override
    public LectureReg findById(Long id) {
        return lectureRegRepository.findById(id);
    }

    @Override
    public LectureReg save(LectureReg lectureReg) {
        return lectureRegRepository.save(lectureReg);
    }

    @Override
    public LectureReg delete(Long id) {
        return lectureRegRepository.delete(id);
    }
}
