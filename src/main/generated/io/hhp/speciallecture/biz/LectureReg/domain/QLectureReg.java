package io.hhp.speciallecture.biz.LectureReg.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QLectureReg is a Querydsl query type for LectureReg
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QLectureReg extends EntityPathBase<LectureReg> {

    private static final long serialVersionUID = -1485300218L;

    public static final QLectureReg lectureReg = new QLectureReg("lectureReg");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Long> lectureId = createNumber("lectureId", Long.class);

    public final NumberPath<Long> userId = createNumber("userId", Long.class);

    public QLectureReg(String variable) {
        super(LectureReg.class, forVariable(variable));
    }

    public QLectureReg(Path<? extends LectureReg> path) {
        super(path.getType(), path.getMetadata());
    }

    public QLectureReg(PathMetadata metadata) {
        super(LectureReg.class, metadata);
    }

}

