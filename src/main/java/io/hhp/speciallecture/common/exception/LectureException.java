package io.hhp.speciallecture.common.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class LectureException extends RuntimeException{

    private final LectureErrorResult errorResult;
}
