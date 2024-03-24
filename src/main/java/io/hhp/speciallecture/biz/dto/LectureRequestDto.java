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
public class LectureRequestDto {
    private final String lectureName;
    private final String lectureDesc;
    private final LocalDateTime startDate;
    private final LocalDateTime endDate;
}
