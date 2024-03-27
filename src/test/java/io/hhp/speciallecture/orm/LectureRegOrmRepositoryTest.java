package io.hhp.speciallecture.orm;

import io.hhp.speciallecture.biz.domain.LectureReg;
import io.hhp.speciallecture.biz.orm.ILectureOrmRepository;
import io.hhp.speciallecture.biz.orm.ILectureRegOrmRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.antlr.v4.runtime.tree.xpath.XPath.findAll;
import static org.assertj.core.api.Assertions.assertThat;


@DisplayName("")
@DataJpaTest
public class LectureRegOrmRepositoryTest {

    ILectureRegOrmRepository lectureRegOrmRepository;

    public LectureRegOrmRepositoryTest(@Autowired ILectureRegOrmRepository lectureRegOrmRepository) {
        this.lectureRegOrmRepository = lectureRegOrmRepository;
    }

    @DisplayName("")
    @Test
    void given_when_then() {

        assertThat(lectureRegOrmRepository).isNotNull();
    }

    @DisplayName("")
    @Test
    void given_whenFoundOne_then() {

        LectureReg savedLectureReg = lectureRegOrmRepository.save(LectureReg.of(1L,1L));

        List<LectureReg> resultList = lectureRegOrmRepository.findAll();

        assertThat(resultList.size()).isEqualTo(1);
    }
}
