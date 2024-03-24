package io.hhp.speciallecture.biz.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Entity
@Table(name = "LECTURE_REG")
public class LectureReg {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;

    @Setter()
    @Column(name = "LECTURE_ID", nullable = false)
    private Long lectureId;

    @Setter()
    @Column(name = "USER_ID", nullable = false)
    private Long userId;

    protected LectureReg(){};

    private LectureReg(Long lectureId, long userId) {
        this.lectureId = lectureId;
        this.userId = userId;
    }

    public LectureReg of(Long lectureId, long userId){
        return new LectureReg(lectureId, userId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LectureReg that = (LectureReg) o;
        return Objects.equals(lectureId, that.lectureId) && Objects.equals(userId, that.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lectureId, userId);
    }
}