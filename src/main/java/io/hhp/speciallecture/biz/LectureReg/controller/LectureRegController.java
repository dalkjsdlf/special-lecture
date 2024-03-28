package io.hhp.speciallecture.biz.LectureReg.controller;

import io.hhp.speciallecture.biz.LectureReg.dto.LectureRegRequestDto;
import io.hhp.speciallecture.biz.LectureReg.dto.LectureRegResponseDto;
import io.hhp.speciallecture.biz.LectureReg.service.ILectureRegService;
import io.hhp.speciallecture.common.constants.WebApiConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * The type Lecture reg controller.
 */
@RequestMapping("api/lecture/register")
@RestController

public class LectureRegController {

    private static Logger logger = LoggerFactory.getLogger(LectureRegController.class);
    private final ILectureRegService lectureRegService;

    public LectureRegController(
            @Autowired ILectureRegService lectureRegService) {
        this.lectureRegService = lectureRegService;
    }

    /**
     * 특강은 신청하는 컨트롤러
     *
     * @param lectureRegRequestDto the lecture reg request dto
     * @return the response entity
     */
    @PostMapping()
    public ResponseEntity<LectureRegResponseDto> registerForLecture(
            @Validated @RequestBody LectureRegRequestDto lectureRegRequestDto){

        /*
        * 특강 신청 서비스
        * */
        LectureRegResponseDto lectureRegResponseDto = lectureRegService.registerForLecture(lectureRegRequestDto);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(lectureRegResponseDto);
    }


    /**
     * 사용자ID와 특강 정보를 기반으로 수강신청 여부를 확인한다.
     *
     * @param userId    the user id
     * @param lectureId the lecture id
     * @return the response entity
     */
    @GetMapping("/check/{lectureId}")
    public ResponseEntity<LectureRegResponseDto> checkIsRegisterForLectureByUserId(
            @RequestHeader(WebApiConstants.USER_ID_HEADER) final Long userId,
            @PathVariable(name ="lectureId")Long lectureId){

        /*
         * 특강 신청여부 확인
         * */
        return ResponseEntity.ok(lectureRegService.checkIsRegisterForLectureByUserId(userId,lectureId));

    }

//    /**
//     * Get all lecture histories response entity.
//     *
//     * @return the response entity
//     */
//    @GetMapping()
//    public ResponseEntity<List<LectureRegResponseDto>> getAllLectureHistories(){
//        return ResponseEntity.ok(lectureRegService.getAllRegisterForLecture());
//    }
}
