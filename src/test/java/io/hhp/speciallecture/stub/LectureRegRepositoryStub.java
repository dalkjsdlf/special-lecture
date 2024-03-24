package io.hhp.speciallecture.stub;

import io.hhp.speciallecture.biz.domain.LectureReg;
import io.hhp.speciallecture.biz.repository.ILectureRegRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class LectureRegRepositoryStub implements ILectureRegRepository {

    Map<Long, LectureReg> table = new ConcurrentHashMap<Long, LectureReg>();

    @Override
    public List<LectureReg> findAll() {
        List<LectureReg> tableList = new ArrayList<>(table.values());
        return tableList;
    }

    @Override
    public LectureReg findByUserId(Long userId) {
        List<LectureReg> tableList = new ArrayList<>(table.values());

        //userId로 조회
        for(LectureReg lectureReg : tableList){
            if(lectureReg.getUserId().equals(userId)){
                return lectureReg;
            }
        }
        return null;
    }

    @Override
    public LectureReg findById(Long id) {
        return table.get(id);
    }

    @Override
    public LectureReg save(LectureReg lectureReg) {
        Long id = lectureReg.getId();
        table.put(id,lectureReg);
        return lectureReg;
    }

    @Override
    public LectureReg delete(Long id) {
        LectureReg lectureReg = table.get(id);
        if(lectureReg != null){
            table.remove(id);
        }

        return lectureReg;
    }
}
