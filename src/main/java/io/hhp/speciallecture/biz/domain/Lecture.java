package io.hhp.speciallecture.biz.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Objects;
@Getter
@Entity
@Table(name = "LECTURE",
        indexes = {
                @Index(columnList = "LECTURE_NAME"),
                @Index(columnList = "START_DATE")
        }
)
public class Lecture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;

    @Setter()
    @Column(name = "LECTURE_NAME", nullable = false, length = 100)
    private String lectureName;

    @Setter()
    @Column(name = "LECTURE_DESC", nullable = true, length = 1000)
    private String lectureDesc;

    @Setter()
    @Column(name = "START_DATE", nullable = false)
    private LocalDateTime startDate;

    @Setter()
    @Column(name = "END_DATE", nullable = false)
    private LocalDateTime endDate;

    protected Lecture (){};

    private Lecture(String lectureName, String lectureDesc, LocalDateTime startDate, LocalDateTime endDate) {
        this.lectureName = lectureName;
        this.lectureDesc = lectureDesc;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public static Lecture of(String lectureName, String lectureDesc, LocalDateTime startDate, LocalDateTime endDate){
        return new Lecture(lectureName, lectureDesc, startDate, endDate);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lecture lecture = (Lecture) o;
        return Objects.equals(id, lecture.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}