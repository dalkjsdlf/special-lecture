package io.hhp.speciallecture.biz.repository;

import io.hhp.speciallecture.biz.domain.LectureReg;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ILectureRegPository extends JpaRepository<LectureReg, Long> {

    List<LectureReg> findByUserId(Long userId);
}
