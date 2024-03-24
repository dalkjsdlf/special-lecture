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
                @Index(columnList = "NAME"),
                @Index(columnList = "START_DATE")
        }
)
public class Lecture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;

    @Setter()
    @Column(name = "NAME", nullable = false, length = 100)
    private String name;

    @Setter()
    @Column(name = "DESC", nullable = true, length = 1000)
    private String desc;

    @Setter()
    @Column(name = "START_DATE", nullable = false)
    private LocalDateTime startDate;

    @Setter()
    @Column(name = "END_DATE", nullable = false)
    private LocalDateTime endDate;

    protected Lecture (){};

    private Lecture(String name, String desc, LocalDateTime startDate, LocalDateTime endDate) {
        this.name = name;
        this.desc = desc;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Lecture of(String name, String desc, LocalDateTime startDate, LocalDateTime endDate){
        return new Lecture(name, desc, startDate, endDate);
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