package io.hhp.speciallecture.orm;

import io.hhp.speciallecture.biz.Lecture.domain.Lecture;
import io.hhp.speciallecture.biz.Lecture.orm.ILectureOrmRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


@DisplayName("물리 DB 테스트")
@DataJpaTest
public class LectureOrmRepositoryTest {

    private final ILectureOrmRepository lectureOrmRepository;

    /**
     * Logger 필요할때만 사용
     * */
    //private final Logger logger = LoggerFactory.getLogger(LectureOrmRepositoryTest.class);

    public LectureOrmRepositoryTest(@Autowired ILectureOrmRepository lectureOrmRepository) {
        this.lectureOrmRepository = lectureOrmRepository;
    }

    @DisplayName("Null Check")
    @Test
    void givenNothing_whenNullCheck_thenNullCheck() {

        assertThat(lectureOrmRepository).isNotNull();
    }

    @DisplayName("[성공] 수강등록 ID로 조회하기 단건조회")
    @Test
    void givenLectureId_whenFindById_thenLectureReg() {

        //given
        /*
         * 테스트 데이터 입력
         * */
        lectureOrmRepository.save(
                Lecture.of(
                        "백엔드강의",
                        "백엔드를 공부하는 강의입니다",
                LocalDateTime.of(2024, 3, 20, 0, 0, 0),
                LocalDateTime.of(2024, 3, 21, 0, 0, 0),
                        20));

        //when
        Optional<Lecture> optResult = lectureOrmRepository.findById(1L);
        Lecture result = optResult.orElse(null);

        //then
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(1L);
        assertThat(result.getNumOfStudents()).isEqualTo(20);
    }

    @DisplayName("[성공] 수강생 인원 증가 테스트")
    @Test
    void givenLectureId_whenUpdateCount_thenSuccessfullyUpdate() {

        //given
        /*
         * 테스트 데이터 입력
         * */
        lectureOrmRepository.save(
                Lecture.of(
                        "백엔드강의",
                        "백엔드를 공부하는 강의입니다",
                        LocalDateTime.of(2024, 3, 20, 0, 0, 0),
                        LocalDateTime.of(2024, 3, 21, 0, 0, 0),
                        20));

        //when
        Optional<Lecture> optResult = lectureOrmRepository.findById(1L);
        Lecture result = optResult.orElse(null);
        assert result != null;
        int numOfStudnets = result.getNumOfStudents();
        numOfStudnets++;

        result.setNumOfStudents(numOfStudnets);

        Lecture finalResult = lectureOrmRepository.save(result);

        //then
        assertThat(finalResult).isNotNull();
        assertThat(finalResult.getNumOfStudents()).isEqualTo(21);
    }

}
