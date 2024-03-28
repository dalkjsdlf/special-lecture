package io.hhp.speciallecture.biz.Lecture.orm;


import io.hhp.speciallecture.biz.Lecture.domain.Lecture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ILectureOrmRepository extends JpaRepository<Lecture, Long> {
   // Optional<Lecture> findById(Long userId, Long lectureId);
}
