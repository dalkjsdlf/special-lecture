package io.hhp.speciallecture.biz.orm;

import io.hhp.speciallecture.biz.domain.Lecture;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ILectureOrmRepository extends JpaRepository<Lecture, Long> {
   // Optional<Lecture> findById(Long userId, Long lectureId);
}
