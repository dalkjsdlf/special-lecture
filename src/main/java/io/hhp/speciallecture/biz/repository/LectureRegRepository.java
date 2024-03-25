package io.hhp.speciallecture.biz.repository;

import io.hhp.speciallecture.biz.domain.LectureReg;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    public List<LectureReg> findByUserId(Long userId) {
        return new ArrayList<LectureReg>();
    }

    @Override
    public Optional<LectureReg> findByUserIdAndLectureId(Long userId, Long lectureId) {

        return Optional.of(LectureReg.of(lectureId,userId));
    }


    @Override
    public Optional<LectureReg> findById(Long id) {
        return lectureRegRepository.findById(id);
    }

    @Override
    public Integer countByLectureId(Long lectureId) {
        return 0;
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
