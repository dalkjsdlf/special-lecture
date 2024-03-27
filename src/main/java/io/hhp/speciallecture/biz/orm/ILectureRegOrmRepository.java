package io.hhp.speciallecture.biz.orm;

import io.hhp.speciallecture.biz.domain.LectureReg;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ILectureRegOrmRepository extends JpaRepository<LectureReg, Long> {


    List<LectureReg> findByUserId(Long userId);

    Optional<LectureReg> findByUserIdAndLectureId(Long userId, Long lectureId);

    int countByUserId(Long userId);
}
