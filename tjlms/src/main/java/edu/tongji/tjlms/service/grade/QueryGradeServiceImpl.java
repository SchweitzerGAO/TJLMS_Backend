package edu.tongji.tjlms.service.grade;

import edu.tongji.tjlms.dto.FinalGradeDto;
import edu.tongji.tjlms.dto.QueryGradeDto;
import edu.tongji.tjlms.model.QueryGradeEntity;
import edu.tongji.tjlms.model.SummatorBasicEntity;
import edu.tongji.tjlms.repository.LabRepository;
import edu.tongji.tjlms.repository.QueryGradeRepository;
import edu.tongji.tjlms.repository.ReportRepository;
import edu.tongji.tjlms.repository.SummatorBasicRepository;
import edu.tongji.tjlms.util.ScoreConvertUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QueryGradeServiceImpl implements QueryGradeService {
    @Resource
    QueryGradeRepository queryGradeRepository;

    @Resource
    LabRepository labRepository;

    @Resource
    ReportRepository reportRepository;

    @Resource
    SummatorBasicRepository summatorBasicRepository;

    @Override
    public QueryGradeEntity queryParticularGrade(String stuId, Integer labId) {
        return queryGradeRepository.findByStuIdAndLabId(stuId,labId);
    }

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

    @Override
    public FinalGradeDto queryFinalGrade(String id) {
        FinalGradeDto ret = new FinalGradeDto();
        ret.setEachGrades(queryGrade(id));
        long numLab = labRepository.count();
        if(numLab == 0)
        {
            ret.setAttendance(0.);
        }
        Optional<SummatorBasicEntity> opt = summatorBasicRepository.findByStuId(id);

        long numReports = reportRepository.countByStuId(id);
        if(opt.isPresent())
        {
            numReports+=1;
        }

        ret.setAttendance((double)numReports*100/numLab);
        double sum = 0;
        for (QueryGradeDto grade: ret.getEachGrades())
        {
            sum += grade.getQueryGradeEntity().getScore();
        }
        double finalScore = sum*0.7+ret.getAttendance()*0.3;
        ret.setFinalScore(finalScore);
        ret.setFinalGrade(ScoreConvertUtil.score2Grade(finalScore));
        return ret;
    }

}
