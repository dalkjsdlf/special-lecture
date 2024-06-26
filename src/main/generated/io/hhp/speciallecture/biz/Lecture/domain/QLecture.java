package io.hhp.speciallecture.biz.Lecture.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QLecture is a Querydsl query type for Lecture
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QLecture extends EntityPathBase<Lecture> {

    private static final long serialVersionUID = 644112674L;

    public static final QLecture lecture = new QLecture("lecture");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath lectureDesc = createString("lectureDesc");

    public final StringPath lectureName = createString("lectureName");

    public final NumberPath<Integer> numOfStudents = createNumber("numOfStudents", Integer.class);

    public final DateTimePath<java.time.LocalDateTime> regEndDate = createDateTime("regEndDate", java.time.LocalDateTime.class);

    public final DateTimePath<java.time.LocalDateTime> regStartDate = createDateTime("regStartDate", java.time.LocalDateTime.class);

    public QLecture(String variable) {
        super(Lecture.class, forVariable(variable));
    }

    public QLecture(Path<? extends Lecture> path) {
        super(path.getType(), path.getMetadata());
    }

    public QLecture(PathMetadata metadata) {
        super(Lecture.class, metadata);
    }

}

