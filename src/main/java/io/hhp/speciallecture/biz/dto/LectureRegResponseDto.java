package io.hhp.speciallecture.biz.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
/*
 * TODO 항목별 제약조건 추가해야함
 * */

@Builder
@Getter
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
public class LectureRegResponseDto {
    private final Long id;
    private final Long LectureId;
    private final Long userId;
    private final LocalDateTime regStartDate;
    private final LocalDateTime regEndDate;
}
