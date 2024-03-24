package io.hhp.speciallecture.biz.repository;

import io.hhp.speciallecture.biz.domain.Lecture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ILectureRepository extends JpaRepository<Lecture, Long> {

}
