package io.hhp.speciallecture.service;

import io.hhp.speciallecture.biz.domain.Lecture;
import io.hhp.speciallecture.biz.domain.LectureReg;
import io.hhp.speciallecture.biz.dto.LectureRegRequestDto;
import io.hhp.speciallecture.biz.dto.LectureRegResponseDto;
import io.hhp.speciallecture.biz.repository.ILectureRegRepository;
import io.hhp.speciallecture.biz.repository.ILectureRepository;
import io.hhp.speciallecture.biz.service.ILectureRegService;
import io.hhp.speciallecture.common.exception.LectureErrorResult;
import io.hhp.speciallecture.common.exception.LectureException;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicReference;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("수강신청 서비스 테스트")
@SpringBootTest
public class LectureRegServiceTotalTest {

    private ILectureRegService lectureRegService;

    private ILectureRegRepository lectureRegRepository;

    private ILectureRepository lectureRepository;

    private static Long userId = 1L;

    private Logger logger = LoggerFactory.getLogger(LectureRegServiceTotalTest.class);

    public LectureRegServiceTotalTest(@Autowired ILectureRegService lectureRegService,
                                      @Autowired ILectureRegRepository lectureRegRepository,
                                      @Autowired ILectureRepository lectureRepository) {
        this.lectureRegService = lectureRegService;
        this.lectureRegRepository = lectureRegRepository;
        this.lectureRepository = lectureRepository;
    }

    @DisplayName("Not null check")
    @Test()
    public void givenNothing_whenCheckNotnull_thenNotnull(){
        // given

        // when

        // then
        assertThat(lectureRegService).isNotNull();
        assertThat(lectureRegRepository).isNotNull();
        assertThat(lectureRepository).isNotNull();
    }

    @DisplayName("[성공] 수강신청 성공")
    @Test()
    public void givenUserIdAndLectureId_whenRegisterForLecture_thenSuccessfullyRegister(){
        //logger.debug("asefsae>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>f");
        // given
        Long userId = 1L;
        Lecture lecture = Lecture.of("백엔드강의"
                , "백엔드 능력을 배양하기 위한 강의"
                , LocalDateTime.of(2024, 3, 20, 0, 0, 0)
                , LocalDateTime.of(2024, 3, 28, 0, 0, 0)
                ,10);

        /*
        * 수강신청을 하기 위해서는 특강 정보가 등록되어있어야 한다.
        **/
        Lecture savedLecture = lectureRepository.save(lecture);
        Long lectureId = savedLecture.getId();

        /*
         * 등록된 특강정보를 바탕으로 수강등록요청 DTO를 생성
         **/
        LectureRegRequestDto lectureRegRequestDto = getLectureRegReqDto(userId, lectureId);

        // when
        LectureRegResponseDto lectureRegResponseDto = lectureRegService.registerForLecture(lectureRegRequestDto);

        // then
        Optional<Lecture> optLectureAfter = lectureRepository.findById(lectureId);
        Lecture lectureAfter = optLectureAfter.orElseThrow(() -> new LectureException(LectureErrorResult.NOT_FOUND_LECTURE));

        assertThat(lectureRegResponseDto.getUserId()).isEqualTo(userId);

        assertThat(lectureAfter.getNumOfStudents()).isEqualTo(11);

    }

    private LectureRegRequestDto getLectureRegReqDto(Long userId, Long lectureId){
        return LectureRegRequestDto
                .builder()
                .userId(userId)
                .LectureId(lectureId)
                .build();
    }

    private LectureRegResponseDto getLectureRegResDto(Long id, Long userId, Long lectureId){
        return LectureRegResponseDto
                .builder()
                .id(id)
                .userId(userId)
                .LectureId(lectureId)
                .build();
    }


    @DisplayName("[성공] 여러 사용자가 동시에 수강신청하여도 순차처리됨")
    @Test()
    public void given10User_whenRegisterSimultaneously_thenSyncResult() throws InterruptedException {
        // given
        logger.info("잘돼나요?");
        int threadCount = 10;
        ExecutorService executorService = Executors.newFixedThreadPool(26);

        CountDownLatch latch = new CountDownLatch(threadCount);

        AtomicReference<Long> userId = new AtomicReference<>(1L);
        Lecture lecture = Lecture.of("백엔드강의"
                , "백엔드 능력을 배양하기 위한 강의"
                , LocalDateTime.of(2024, 3, 20, 0, 0, 0)
                , LocalDateTime.of(2024, 3, 28, 0, 0, 0)
                ,0);

        /*
         * 수강신청을 하기 위해서는 특강 정보가 등록되어있어야 한다.
         **/
        Lecture savedLecture = lectureRepository.save(lecture);
        Long lectureId = savedLecture.getId();

        //when
        for (int i = 0; i < threadCount; i++) {
            executorService.submit(() -> {
                try {
                    Random random = new Random();


                    random.setSeed(System.currentTimeMillis());
                    Long userId2 = random.nextLong(100);
                    logger.info("Thread ID [{}],   userId >>>[{}]",Thread.currentThread().getId(),userId2);

                    LectureRegRequestDto lectureRegRequestDto = getLectureRegReqDto(userId2, lectureId);
                    lectureRegService.registerForLecture(lectureRegRequestDto);

                } finally {
                    latch.countDown();
                }
            });
        }
        latch.await();
        Lecture resultLecture = lectureRepository.findById(lectureId).orElseThrow(() -> new LectureException(LectureErrorResult.NOT_FOUND_LECTURE));
        List<LectureReg> lectureRegList = lectureRegRepository.findAll();
        //then
        assertThat(resultLecture.getId()).isEqualTo(lectureId);
        assertThat(resultLecture.getNumOfStudents()).isEqualTo(10);
        assertThat(lectureRegList.size()).isEqualTo(10);

    }

}
