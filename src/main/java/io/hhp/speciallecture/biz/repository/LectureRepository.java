package io.hhp.speciallecture.biz.repository;

import io.hhp.speciallecture.biz.domain.Lecture;
import io.hhp.speciallecture.biz.orm.ILectureOrmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class LectureRepository implements ILectureRepository{

    ILectureOrmRepository lectureOrmRepository;

    public LectureRepository(@Autowired ILectureOrmRepository lectureOrmRepository) {
        this.lectureOrmRepository = lectureOrmRepository;
    }

    @Override
    public List<Lecture> findAll() {
        return lectureOrmRepository.findAll();
    }

    @Override
    public Lecture findById(Long id) {
        Optional<Lecture> optionLecture = lectureOrmRepository.findById(id);
        return null;
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
