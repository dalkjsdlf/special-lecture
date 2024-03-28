package io.hhp.speciallecture.biz.Lecture.orm;


import io.hhp.speciallecture.biz.Lecture.domain.Lecture;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ILectureOrmRepository extends JpaRepository<Lecture, Long> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select lt from Lecture lt where lt.id = :id")
    Optional<Lecture> findByIdWithLock(Long id);
}
