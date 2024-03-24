package io.hhp.speciallecture.controller;

import com.google.gson.Gson;
import io.hhp.speciallecture.biz.controller.LectureController;
import io.hhp.speciallecture.biz.service.ILectureService;
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
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("특강 관리 테스트")
@SpringBootTest

@ExtendWith(MockitoExtension.class)
public class LectureControllerTest {

    @InjectMocks
    LectureController lectureController;

    @Mock
    ILectureService lectureService;

    @Mock
    MockMvc mockMvc;

    Gson gson;
    @BeforeEach
    void init(){
        gson = new Gson();
        mockMvc = MockMvcBuilders.standaloneSetup(lectureController)
                .setControllerAdvice(new ApiControllerAdvice())
                .build();
    }

    public LectureControllerTest(@Autowired ILectureService lectureService) {
        this.lectureService = lectureService;
    }

    @DisplayName("")
    @Test()
    public void givenUrl_whenGetAllLecture_thenListOfLecture(){
        // given
        String url = "/api/register";
        // when
        //mockMvc.perform(get())

        // then

    }
}
