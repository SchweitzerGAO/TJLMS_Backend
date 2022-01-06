package edu.tongji.tjlms.service.grade;

import edu.tongji.tjlms.dto.GradeDto;
import edu.tongji.tjlms.model.ClassEntity;
import edu.tongji.tjlms.model.ReportEntityPK;
import edu.tongji.tjlms.model.ReportListEntity;
import edu.tongji.tjlms.model.SummatorListEntity;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface GradeService {
    List<ReportListEntity> getReportList(String teacherId);
    Map<String,Object> getReport(ReportEntityPK pk);
    Map<String, Object> getSummator(String id);
    List<ClassEntity> getMyClasses(String teacherId);
    Page<ReportListEntity> getReportListPaged(String teacherId,Integer pageNum,Integer pageSize);
    Page<SummatorListEntity> getSummatorListPaged(String teacherId,Integer pageNum,Integer pageSize);
    List<ReportListEntity> getReportByTeacherIdAndLabId(String teacherId,Integer labId);

    String save(GradeDto info);
    String saveSummator(GradeDto info);
    String release(String classId);
}
