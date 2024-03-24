package io.hhp.speciallecture.biz.controller;

import io.hhp.speciallecture.biz.dto.LectureRegRequestDto;
import io.hhp.speciallecture.biz.dto.LectureRegResponseDto;
import io.hhp.speciallecture.biz.dto.LectureResponseDto;
import io.hhp.speciallecture.biz.service.ILectureRegService;
import io.hhp.speciallecture.biz.service.LectureRegService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("api/lecture/register")
@RestController

public class LectureRegController {

    private static Logger logger = LoggerFactory.getLogger(LectureRegController.class);
    private final ILectureRegService lectureRegService;

    public LectureRegController(@Autowired ILectureRegService lectureRegService) {
        this.lectureRegService = lectureRegService;
    }

    @PostMapping()
    public ResponseEntity<LectureRegResponseDto> registerForLecture(@RequestBody LectureRegRequestDto lectureRegRequestDto){

        LectureRegResponseDto lectureRegResponseDto = lectureRegService.registerForLecture(lectureRegRequestDto);
        logger.info("lectureRegResponseDto [{}]",lectureRegResponseDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(lectureRegResponseDto);
    }

    @GetMapping("{userId}")
    public ResponseEntity<LectureRegResponseDto> getIsRegisterForLectureByUserId(
            @PathVariable(name ="userId")Long userId){
        return ResponseEntity.ok(lectureRegService.getIsRegisterForLectureByUserId(userId));
    }

    @GetMapping()
    public ResponseEntity<List<LectureRegResponseDto>> getAllLectures(){
        return ResponseEntity.ok(lectureRegService.getAllRegisterForLecture());
    }
}
