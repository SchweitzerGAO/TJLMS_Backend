package edu.tongji.tjlms.service.grade;

import edu.tongji.tjlms.dto.GradeDto;
import edu.tongji.tjlms.model.*;
import edu.tongji.tjlms.repository.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

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

    @Resource
    SummatorResultRepository summatorResultRepository;

    @Resource
    SummatorBasicRepository summatorBasicRepository;

    @Resource
    SummatorListRepository summatorListRepository;

    @Override
    public List<ReportListEntity> getReportList(String teacherId) {
        return reportListRepository.findAllByTeacherId(teacherId);
    }

    @Override
    public ReportEntity getReport(ReportEntityPK pk) {
        return reportRepository.findByStuIdAndLabId(pk.getStuId(),pk.getLabId());
    }

    @Override
    public Map<String, Object> getSummator(String id) {
        Map<String,Object> map = new HashMap<>();
        SummatorBasicEntity basic = summatorBasicRepository.findByStuId(id);
        map.put("basic",basic);
        List<SummatorResultEntity> result = summatorResultRepository.findAllByStuId(id);
        map.put("result",result);
        return map;
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
    public Page<SummatorListEntity> getSummatorListPaged(String teacherId, Integer pageNum, Integer pageSize) {
        return summatorListRepository.findAllByTeacherId(teacherId, PageRequest.of(pageNum-1,pageSize));
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
        reportRepository.updateChecked(info.getStuId(),info.getLabId());
        labGradeRepository.save(grade);

        return "暂存成功";
    }

    @Override
    public String saveSummator(GradeDto info) {
        LabGradeEntity grade = new LabGradeEntity();
        grade.setClassId(info.getClassId());
        grade.setLabId(1);
        grade.setScore(info.getScore());
        grade.setNote(info.getNote());
        grade.setVisible(false);
        grade.setStuId(info.getStuId());
        grade.setTeacherId(info.getTeacherId());
        grade.setUpdateDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        summatorBasicRepository.updateCheck(info.getStuId());
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
