package edu.tongji.tjlms.service.grade;

import edu.tongji.tjlms.dto.QueryGradeDto;
import edu.tongji.tjlms.model.QueryGradeEntity;
import edu.tongji.tjlms.repository.QueryGradeRepository;
import edu.tongji.tjlms.util.ScoreConvertUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class QueryGradeServiceImpl implements QueryGradeService{
    @Resource
    QueryGradeRepository queryGradeRepository;
    @Override
    public List<QueryGradeDto> queryGrade(String id) {
        List<QueryGradeDto> ret = new ArrayList<>();
        List<QueryGradeEntity> grades = queryGradeRepository.findAllByStuId(id);
        for(QueryGradeEntity grade: grades)
        {
            ret.add(new QueryGradeDto(grade, ScoreConvertUtil.score2Grade(grade.getScore())));
        }
        return ret;
    }
}
