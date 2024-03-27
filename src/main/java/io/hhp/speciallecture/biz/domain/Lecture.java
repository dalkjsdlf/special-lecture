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
                @Index(columnList = "REG_START_DATE")
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
    @Column(name = "REG_START_DATE", nullable = false)
    private LocalDateTime regStartDate;

    @Setter()
    @Column(name = "REG_END_DATE", nullable = false)
    private LocalDateTime regEndDate;

    @Setter
    @Column(name = "NUM_OF_STUDENTS", nullable = false)
    private int numOfStudents;

    protected Lecture (){};

    private Lecture(String lectureName, String lectureDesc, LocalDateTime regStartDate, LocalDateTime regEndDate, int numOfStudents) {
        this.lectureName = lectureName;
        this.lectureDesc = lectureDesc;
        this.regStartDate = regStartDate;
        this.regEndDate = regEndDate;
        this.numOfStudents = numOfStudents;
    }

    public static Lecture of(String lectureName, String lectureDesc, LocalDateTime regStartDate, LocalDateTime regEndDate, int numOfStudents){
        return new Lecture(lectureName, lectureDesc, regStartDate, regEndDate, numOfStudents);
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