package io.hhp.speciallecture.stub;



import io.hhp.speciallecture.biz.LectureReg.domain.LectureReg;
import io.hhp.speciallecture.biz.LectureReg.repository.ILectureRegRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class LectureRegRepositoryStub implements ILectureRegRepository {

    Map<Long, LectureReg> table = new ConcurrentHashMap<Long, LectureReg>();

    @Override
    public List<LectureReg> findAll() {
        List<LectureReg> tableList = new ArrayList<>(table.values());
        return tableList;
    }

    @Override
    public List<LectureReg> findByUserId(Long userId) {
        List<LectureReg> tableList = new ArrayList<>(table.values());

        //userId로 조회
        return tableList.stream().filter(item->item.getUserId().equals(userId)).collect(Collectors.toList());
    }

    @Override
    public Optional<LectureReg> findByUserIdAndLectureId(Long userId, Long lectureId) {
        return null;
    }

    @Override
    public Optional<LectureReg> findById(Long id) {
        return null;
    }

    @Override
    public Integer countByLectureId(Long lectureId) {
        return 0;
    }


    @Override
    public LectureReg save(LectureReg lectureReg) {
        Long id = lectureReg.getId();
        table.put(id,lectureReg);
        return lectureReg;
    }

    @Override
    public void delete(LectureReg lectureReg) {
        Long id = lectureReg.getId();
        LectureReg foundLectureReg = table.get(id);
        if(foundLectureReg != null){
            table.remove(id);
        }
    }
}
