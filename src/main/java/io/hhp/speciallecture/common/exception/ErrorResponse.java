package io.hhp.speciallecture.common.exception;

public record ErrorResponse(
        String code,
        String message
) {
}
