package io.hhp.speciallecture.biz.orm;

import io.hhp.speciallecture.biz.domain.LectureReg;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import java.util.List;
import java.util.Optional;

public interface ILectureRegOrmRepository extends JpaRepository<LectureReg, Long> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    List<LectureReg> findByUserId(Long userId);
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<LectureReg> findByUserIdAndLectureId(Long userId, Long lectureId);
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Integer countByLectureId(Long lectureId);
}
