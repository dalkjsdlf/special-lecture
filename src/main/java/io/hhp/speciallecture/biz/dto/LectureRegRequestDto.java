package io.hhp.speciallecture.biz.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
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

}
