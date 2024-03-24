package io.hhp.speciallecture.biz.controller;

import io.hhp.speciallecture.biz.dto.LectureResponseDto;
import io.hhp.speciallecture.biz.service.ILectureService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("api/lecture")
@RestController
public class LectureController {

    ILectureService lectureService;

    public LectureController(@Autowired ILectureService lectureService) {
        this.lectureService = lectureService;
    }

    @GetMapping()
    public ResponseEntity<List<LectureResponseDto>> getAllLectures(){
        return null;
    }
    @GetMapping("{id}")
    public ResponseEntity<LectureResponseDto> getLectureById(
            @PathVariable(name ="id")Long id){
        return null;
    }
    @PostMapping()
    public ResponseEntity<LectureResponseDto> addLecture(){
        return null;
    }
    @PatchMapping("{id}")
    public ResponseEntity<LectureResponseDto> deleteAllLectureById(
            @PathVariable(name ="id")Long id){
        return null;
    }
    @DeleteMapping("{id}")
    public ResponseEntity<LectureResponseDto> modifyAllLectureById(
            @PathVariable(name ="id")Long id){
        return null;
    }


}
