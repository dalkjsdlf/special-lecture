package io.hhp.speciallecture.controller;

import com.google.gson.Gson;
import io.hhp.speciallecture.biz.controller.LectureRegController;
import io.hhp.speciallecture.biz.service.ILectureRegService;
import io.hhp.speciallecture.common.exception.ApiControllerAdvice;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("특강 관리 테스트")
@SpringBootTest

@ExtendWith(MockitoExtension.class)
public class LectureRegControllerTest {

    @InjectMocks
    LectureRegController lectureRegController;

    @Mock
    ILectureRegService lectureRegService;

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

    public LectureRegControllerTest(@Autowired ILectureRegService lectureRegService) {
        this.lectureRegService = lectureRegService;
    }

    @DisplayName("")
    @Test()
    public void givenUrlUserIdAndLectureId_whenGetRegister_thenSuccessfullyRegister() throws Exception {
        // given
        String url = "/api/register";
        Long userId = 1L;
        Long lectureId = 1L;
        // when
        ResultActions resultActions = mockMvc.perform(get(url).contentType(MediaType.APPLICATION_JSON));


        // then
        resultActions.andExpect(status().isOk());
    }
}
