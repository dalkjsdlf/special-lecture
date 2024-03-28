package io.hhp.speciallecture.biz.LectureReg.repository;


import io.hhp.speciallecture.biz.LectureReg.domain.LectureReg;
import io.hhp.speciallecture.biz.LectureReg.orm.ILectureRegOrmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Component
public class LectureRegRepository implements ILectureRegRepository {

    ILectureRegOrmRepository lectureRegOrmRepository;

    public LectureRegRepository(@Autowired ILectureRegOrmRepository lectureRegOrmRepository) {
        this.lectureRegOrmRepository = lectureRegOrmRepository;
    }

    @Override
    public List<LectureReg> findAll() {
        return lectureRegOrmRepository.findAll();
    }

    @Override
    public List<LectureReg> findByUserId(Long userId) {
        return new ArrayList<LectureReg>();
    }

    @Override
    public Optional<LectureReg> findByUserIdAndLectureId(Long userId, Long lectureId) {
        return lectureRegOrmRepository.findByUserIdAndLectureId(userId, lectureId);
    }


    @Override
    public Optional<LectureReg> findById(Long id) {
        return lectureRegOrmRepository.findById(id);
    }

    @Override
    public Integer countByLectureId(Long lectureId) {
        return lectureRegOrmRepository.countByLectureId(lectureId);
    }
    @Override
    public LectureReg save(LectureReg lectureReg) {
        return lectureRegOrmRepository.save(lectureReg);
    }

    @Override
    public void delete(LectureReg lectureReg) {
        lectureRegOrmRepository.delete(lectureReg);
    }
}
