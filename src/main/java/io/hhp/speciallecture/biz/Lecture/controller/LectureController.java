package io.hhp.speciallecture.biz.Lecture.controller;


import io.hhp.speciallecture.biz.Lecture.dto.LectureResponseDto;
import io.hhp.speciallecture.biz.Lecture.service.ILectureService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * The type Lecture controller.
 */
@RequestMapping("api/lecture")
@RestController
public class LectureController {

    /**
     * The Lecture service.
     */
    ILectureService lectureService;

    /**
     * Instantiates a new Lecture controller.
     *
     * @param lectureService the lecture service
     */
    public LectureController(@Autowired ILectureService lectureService) {
        this.lectureService = lectureService;
    }

    /**
     * Get all lectures response entity.
     *
     * @return the response entity
     */
    @GetMapping()
    public ResponseEntity<List<LectureResponseDto>> getAllLectures(){
        return null;
    }

    /**
     * Gets lecture by id.
     *
     * @param id the id
     * @return the lecture by id
     */
    @GetMapping("{id}")
    public ResponseEntity<LectureResponseDto> getLectureById(
            @PathVariable(name ="id")Long id){
        return null;
    }

    /**
     * Add lecture response entity.
     *
     * @return the response entity
     */
    @PostMapping()
    public ResponseEntity<LectureResponseDto> addLecture(){
        return null;
    }

    /**
     * Delete all lecture by id response entity.
     *
     * @param id the id
     * @return the response entity
     */
    @PatchMapping("{id}")
    public ResponseEntity<LectureResponseDto> deleteAllLectureById(
            @PathVariable(name ="id")Long id){
        return null;
    }

    /**
     * Modify all lecture by id response entity.
     *
     * @param id the id
     * @return the response entity
     */
    @DeleteMapping("{id}")
    public ResponseEntity<LectureResponseDto> modifyAllLectureById(
            @PathVariable(name ="id")Long id){
        return null;
    }


}
