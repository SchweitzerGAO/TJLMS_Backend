package edu.tongji.tjlms.service.grade;

import edu.tongji.tjlms.dto.GradeDto;
import edu.tongji.tjlms.model.*;
import edu.tongji.tjlms.repository.ClassRepository;
import edu.tongji.tjlms.repository.LabGradeRepository;
import edu.tongji.tjlms.repository.ReportListRepository;
import edu.tongji.tjlms.repository.ReportRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class GradeServiceImpl implements GradeService{

    @Resource
    ReportListRepository reportListRepository;

    @Resource
    ReportRepository reportRepository;

    @Resource
    LabGradeRepository labGradeRepository;

    @Resource
    ClassRepository classRepository;

    @Override
    public List<ReportListEntity> getReportList(String teacherId) {
        return reportListRepository.findAllByTeacherId(teacherId);
    }

    @Override
    public ReportEntity getReport(ReportEntityPK pk) {
        return reportRepository.findByStuIdAndLabId(pk.getStuId(),pk.getLabId());
    }

    @Override
    public List<ClassEntity> getMyClasses(String teacherId) {
        return classRepository.findAllByTeacherIdOrRespIdOrAssistId(teacherId,teacherId,teacherId);
    }

    @Override
    public Page<ReportListEntity> getReportListPaged(String teacherId, Integer pageNum, Integer pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNum-1,pageSize);
        return reportListRepository.findAllByTeacherId(teacherId,pageRequest);
    }

    @Override
    public String save(GradeDto info) {
        LabGradeEntity grade = new LabGradeEntity();
        grade.setClassId(info.getClassId());
        grade.setLabId(info.getLabId());
        grade.setScore(info.getScore());
        grade.setNote(info.getNote());
        grade.setVisible(false);
        grade.setStuId(info.getStuId());
        grade.setTeacherId(info.getTeacherId());
        grade.setUpdateDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        labGradeRepository.save(grade);

        return "暂存成功";
    }

    @Override
    public String release(String classId) {
        String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        labGradeRepository.release(classId,time);
        return "发布成功";
    }
}
