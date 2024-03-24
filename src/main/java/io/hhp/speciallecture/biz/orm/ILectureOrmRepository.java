package io.hhp.speciallecture.biz.orm;

import io.hhp.speciallecture.biz.domain.Lecture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ILectureOrmRepository extends JpaRepository<Lecture, Long> {
}
