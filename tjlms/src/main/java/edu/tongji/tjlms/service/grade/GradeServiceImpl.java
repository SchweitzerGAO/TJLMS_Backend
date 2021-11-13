package edu.tongji.tjlms.service.grade;

import edu.tongji.tjlms.dto.GradeDto;
import edu.tongji.tjlms.dto.QueryGradeDto;
import edu.tongji.tjlms.model.GradeEntity;
import edu.tongji.tjlms.model.QueryGradeEntity;
import edu.tongji.tjlms.repository.GradeRepository;
import edu.tongji.tjlms.repository.QueryGradeRepository;
import edu.tongji.tjlms.repository.StudentFinderRepository;
import edu.tongji.tjlms.util.ScoreConvertUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class GradeServiceImpl implements GradeService{
    @Resource
    GradeRepository gradeRepository;
    @Resource
    StudentFinderRepository studentFinderRepository;
    @Resource
    QueryGradeRepository queryGradeRepository;
    public void saveGrade(List<GradeDto> list) {
        List<GradeEntity> gradeInfo = new ArrayList<>();

        for(GradeDto grade: list)
        {
            GradeEntity temp = new GradeEntity();
            temp.setStuId(grade.getStuId());
            temp.setClassId(grade.getClassId());
            temp.setLabId(grade.getLabId());
            temp.setNote(grade.getNote());
            temp.setTeacherId(grade.getTeacherId());
            temp.setScore(grade.getScore());
            temp.setVisible(false);
            temp.setUpdateDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            gradeInfo.add(temp);
        }
        gradeRepository.saveAll(gradeInfo);
    }

    @Override
    public void releaseGrade(String teacherId) {
        List<String> classes = studentFinderRepository.getClasses(teacherId);
        String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        for(String classId: classes)
        {
            gradeRepository.release(classId,time);
        }
    }

    @Override
    public List<QueryGradeDto> queryGrade(String studentId) {
        List<QueryGradeEntity> queryGradeEntities = queryGradeRepository.findAllByStuId(studentId);
        List<QueryGradeDto> grades = new ArrayList<>();
        for(QueryGradeEntity entity:queryGradeEntities)
        {
            QueryGradeDto temp = new QueryGradeDto();
            temp.setQueryGradeEntity(entity);
            temp.setGrade(ScoreConvertUtil.score2Grade(entity.getScore()));
            grades.add(temp);
        }
        return grades;
    }

}
