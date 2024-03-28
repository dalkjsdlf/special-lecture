package io.hhp.speciallecture.biz.Lecture.repository;


import io.hhp.speciallecture.biz.Lecture.domain.Lecture;
import io.hhp.speciallecture.biz.Lecture.orm.ILectureOrmRepository;
import jakarta.persistence.LockModeType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
@Component
public class LectureRepository implements ILectureRepository {

    ILectureOrmRepository lectureOrmRepository;

    public LectureRepository(@Autowired ILectureOrmRepository lectureOrmRepository) {
        this.lectureOrmRepository = lectureOrmRepository;
    }

    @Override
    public List<Lecture> findAll() {
        return lectureOrmRepository.findAll();
    }
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Override
    public Optional<Lecture> findById(Long id) {
        return lectureOrmRepository.findById(id);
    }
    @Override
    public Lecture save(Lecture lecture) {
        return lectureOrmRepository.save(lecture);
    }

    @Override
    public Lecture update(Lecture lecture) {
        return null;
    }

    @Override
    public Lecture delete(Long id) {
        return null;
    }
}
