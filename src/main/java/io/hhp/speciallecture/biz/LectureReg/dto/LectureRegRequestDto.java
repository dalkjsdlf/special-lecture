package io.hhp.speciallecture.biz.LectureReg.dto;

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
public class LectureRegRequestDto {
    private final Long LectureId;
    private final Long userId;
    private final LocalDateTime regStartDate;
    private final LocalDateTime regEndDate;

}
