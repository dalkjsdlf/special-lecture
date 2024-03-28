package io.hhp.speciallecture.orm;

import io.hhp.speciallecture.biz.LectureReg.domain.LectureReg;
import io.hhp.speciallecture.biz.LectureReg.orm.ILectureRegOrmRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


@DisplayName("물리 DB 테스트")
@DataJpaTest
public class LectureRegOrmRepositoryTest {

    private final ILectureRegOrmRepository lectureRegOrmRepository;

    /*
    * Logger 필요할때만 사용하기
    * */
    //private final Logger logger = LoggerFactory.getLogger(LectureRegOrmRepositoryTest.class);

    public LectureRegOrmRepositoryTest(@Autowired ILectureRegOrmRepository lectureRegOrmRepository) {
        this.lectureRegOrmRepository = lectureRegOrmRepository;
    }

    @DisplayName("Null Check")
    @Test
    void givenNothing_whenNullCheck_thenNullCheck() {

        assertThat(lectureRegOrmRepository).isNotNull();
    }

    @DisplayName("[성공] 수강등록 ID로 조회하기 단건조회")
    @Test
    void givenLectureRegId_whenFindById_thenLectureReg() {

        //given
        /*
         * 테스트 데이터 입력
         * */
        lectureRegOrmRepository.save(LectureReg.of(1L,1L));  //id = 1
        lectureRegOrmRepository.save(LectureReg.of(2L,1L));  //id = 2
        lectureRegOrmRepository.save(LectureReg.of(3L,1L));  //id = 3
        lectureRegOrmRepository.save(LectureReg.of(3L,2L));  //id = 4

        Long userId    = 1L;
        Long lectureId = 1L;

        //when
        Optional<LectureReg> optResult = lectureRegOrmRepository.findById(1L);
        LectureReg result = optResult.orElse(null);

        //then
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(1L);
        assertThat(result.getUserId()).isEqualTo(userId);
        assertThat(result.getLectureId()).isEqualTo(lectureId);
    }

    @DisplayName("[성공] 사용자ID로 조회하기 다건조회")
    @Test
    void givenUserId_whenFindByUserId_thenLectureRegList() {

        //given
        /*
         * 테스트 데이터 입력
         * */
        lectureRegOrmRepository.save(LectureReg.of(1L,1L));  //id = 1
        lectureRegOrmRepository.save(LectureReg.of(2L,1L));  //id = 2
        lectureRegOrmRepository.save(LectureReg.of(3L,1L));  //id = 3
        lectureRegOrmRepository.save(LectureReg.of(3L,2L));  //id = 4

        Long userId    = 1L;

        //when
        List<LectureReg> resultList = lectureRegOrmRepository.findByUserId(userId);

        //then
        assertThat(resultList).hasSize(3);
    }

    /*
     * USERID -> 1 LECTUREID 2를 찾아 반환
     * */
    @DisplayName("[성공] 사용자ID와 특강ID로 조회하기 단건조회")
    @Test
    void givenUserIdAndLectureId_whenFindByUserIdAndLectureId_thenLectureReg() {

        //given
        /*
         * 테스트 데이터 입력
         * */
        lectureRegOrmRepository.save(LectureReg.of(1L,1L));  //id = 1
        lectureRegOrmRepository.save(LectureReg.of(2L,1L));  //id = 2
        lectureRegOrmRepository.save(LectureReg.of(3L,1L));  //id = 3
        lectureRegOrmRepository.save(LectureReg.of(3L,2L));  //id = 4

        Long userId    = 1L;
        Long lectureId = 2L;

        //when
        Optional<LectureReg> optResult = lectureRegOrmRepository.findByUserIdAndLectureId(userId,lectureId);
        LectureReg result = optResult.orElse(null);

        //then
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(2L);
        assertThat(result.getUserId()).isEqualTo(userId);
        assertThat(result.getLectureId()).isEqualTo(lectureId);
    }

    @DisplayName("[성공] 특강ID로 카운드하기")
    @Test
    void givenLectureId_whenFindByUserIdAndLectureId_thenCountValue() {

        //given
        /*
         * 테스트 데이터 입력
         * */
        lectureRegOrmRepository.save(LectureReg.of(1L,1L));  //id = 1
        lectureRegOrmRepository.save(LectureReg.of(1L,2L));  //id = 2
        lectureRegOrmRepository.save(LectureReg.of(1L,3L));  //id = 3
        lectureRegOrmRepository.save(LectureReg.of(2L,2L));  //id = 4

        Long userId    = 1L;

        //when
        int result = lectureRegOrmRepository.countByLectureId(userId);

        //then
        assertThat(result).isEqualTo(3);
    }
}
