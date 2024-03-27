package io.hhp.speciallecture.controller;

import com.google.gson.Gson;
import io.hhp.speciallecture.biz.controller.LectureRegController;
import io.hhp.speciallecture.biz.dto.LectureRegRequestDto;
import io.hhp.speciallecture.biz.dto.LectureRegResponseDto;
import io.hhp.speciallecture.biz.service.LectureRegService;
import io.hhp.speciallecture.common.exception.ApiControllerAdvice;
import io.hhp.speciallecture.stub.LectureRegServiceStub;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("특강 관리 테스트")
@ExtendWith(MockitoExtension.class)
public class LectureRegControllerTest {

    @InjectMocks
    private LectureRegController lectureRegController;

    @Mock
    private LectureRegService lectureRegService;

    @Mock
    MockMvc mockMvc;

    Gson gson;
    @BeforeEach
    void init(){
        gson = new Gson();
        mockMvc = MockMvcBuilders.standaloneSetup(lectureRegController)
                .setControllerAdvice(new ApiControllerAdvice())
                .build();
    }


    @DisplayName("[성공] null 검사")
    @Test()
    public void givenNothing_whenCheckNull_thenNotNull(){
        // given

        // when

        // then
        assertThat(lectureRegController).isNotNull();
        assertThat(lectureRegService).isNotNull();
    }

    @DisplayName("[성공] 특강신청")
    @Test()
    public void givenUrlUserIdAndLectureId_whenGetRegister_thenSuccessfullyRegister() throws Exception {
        // given
        String url = "/api/lecture/register";
        Long lectureRegId = 1L;
        Long userId = 1L;
        Long lectureId = 1L;

        LocalDateTime startDate = LocalDateTime.of(2024, 3, 20, 0, 0, 0);
        LocalDateTime endDate = LocalDateTime.of(2024, 3, 20, 0, 0, 0);

        //요청 DTO객체 생성
        LectureRegRequestDto req = getLectureRegReqDto(userId,lectureId,startDate,endDate);

        //응답 DTO객체 생성
        LectureRegResponseDto res = getLectureRegResDto(lectureRegId, userId,lectureId,startDate,endDate);

        LectureRegServiceStub lectureRegService = new LectureRegServiceStub();

        // 결과값 세팅
        lectureRegService.setReturn(getLectureRegResDto(lectureRegId,userId,lectureId,startDate,endDate));

        //요청 DTO객체 등록요청하면 가짜 응답 DTO 반환
        lectureRegController =  new LectureRegController(lectureRegService);


        // when
        ResultActions resultActions = mockMvc
                .perform(post(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(gson.toJson(req)));

        // then
        resultActions.andExpect(status().isCreated());
    }

    @DisplayName("[성공] 수강신청여부확인")
    @Test()
    public void givenUserId_whenGetLectureRegByUserID_thenGetListLectureReg() throws Exception {
        // given
        String url = "/api/lecture/register";
        Long lectureRegId = 1L;
        Long userId = 1L;
        Long lectureId = 1L;
        LocalDateTime startDate = LocalDateTime.of(2024, 3, 20, 0, 0, 0);
        LocalDateTime endDate = LocalDateTime.of(2024, 3, 20, 0, 0, 0);

        //요청 DTO객체 생성
        LectureRegRequestDto req = getLectureRegReqDto(userId,lectureId,startDate,endDate);

        //응답 DTO객체 생성
        LectureRegResponseDto res = getLectureRegResDto(lectureRegId, userId,lectureId,startDate,endDate);

        LectureRegServiceStub lectureRegService = new LectureRegServiceStub();

        // 결과값 세팅
        lectureRegService.setReturnList(List.of(getLectureRegResDto(lectureRegId,userId,lectureId,startDate,endDate)));

        //요청 DTO객체 등록요청하면 가짜 응답 DTO 반환
        lectureRegController =  new LectureRegController(lectureRegService);

        // when
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders
                .get(url)
                .param("userId","1")
                .contentType(MediaType.APPLICATION_JSON));

        // then
        resultActions.andExpect(status().isOk());
        //resultActions.andExpect(MockMvcResultMatchers.jsonPath("$[0].userId").value(userId));
    }

    private LectureRegRequestDto getLectureRegReqDto(Long userId, Long lectureId, LocalDateTime regStartDate, LocalDateTime regEndDate){
        return LectureRegRequestDto
                .builder()
                .userId(userId)
                .LectureId(lectureId)
                .regStartDate(regStartDate)
                .regEndDate(regEndDate)
                .build();
    }

    private LectureRegResponseDto getLectureRegResDto(Long id, Long userId, Long lectureId, LocalDateTime regStartDate, LocalDateTime regEndDate){
        return LectureRegResponseDto
                .builder()
                .id(id)
                .userId(userId)
                .LectureId(lectureId)
                .regStartDate(regStartDate)
                .regEndDate(regEndDate)
                .build();
    }
}
