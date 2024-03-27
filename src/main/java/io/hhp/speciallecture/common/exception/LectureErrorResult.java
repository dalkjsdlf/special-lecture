package io.hhp.speciallecture.common.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum LectureErrorResult {
    NOT_FOUND_LECTURE(HttpStatus.BAD_REQUEST,"The lecture is not found"),
    CANNOT_REGISTER_FOR_THE_PERIOD(HttpStatus.BAD_REQUEST,"you can not resgister lecture for the period"),
    ALREADY_REGISTER(HttpStatus.BAD_REQUEST,"you have been already register the lecture"),
    NUM_OF_PEOPLE_EXCEED(HttpStatus.BAD_REQUEST,"The number of people has been exceeded."),
    WRONG_USER_ID(HttpStatus.BAD_REQUEST,"The ID was entered incorrectly."),
    WRONG_STARTE_DATE(HttpStatus.BAD_REQUEST,"The date of start lecture was entered incorrectly."),
    UNKNOWN_EXCEPTION(HttpStatus.INTERNAL_SERVER_ERROR, "Unknown Exception"),
    WRONG_LECTURE_ID(HttpStatus.BAD_REQUEST,"The LectureId was entered incorrectly"),
    LECTURE_NOT_REGISTERED(HttpStatus.BAD_REQUEST,"The Lecture Not Registered by userId");
    ;

    private final HttpStatus httpStatus;
    private final String message;
}
