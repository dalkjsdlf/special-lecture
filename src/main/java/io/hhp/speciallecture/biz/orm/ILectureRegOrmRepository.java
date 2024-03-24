package io.hhp.speciallecture.biz.orm;

import io.hhp.speciallecture.biz.domain.LectureReg;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ILectureRegOrmRepository extends JpaRepository<LectureReg, Long> {


}
