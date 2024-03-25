package io.hhp.speciallecture.biz.repository;

import io.hhp.speciallecture.biz.domain.Lecture;

import java.util.List;
import java.util.Optional;

public interface ILectureRepository{
    public List<Lecture> findAll();
    public Optional<Lecture> findById(Long id);
    public Lecture save(Lecture lecture);
    public Lecture update(Lecture lecture);
    public Lecture delete(Long id);
}
