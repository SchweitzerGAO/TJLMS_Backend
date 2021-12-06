package edu.tongji.tjlms.service.grade;

import edu.tongji.tjlms.dto.GradeDto;
import edu.tongji.tjlms.model.ClassEntity;
import edu.tongji.tjlms.model.ReportEntity;
import edu.tongji.tjlms.model.ReportEntityPK;
import edu.tongji.tjlms.model.ReportListEntity;
import org.springframework.data.domain.Page;

import java.util.List;

public interface GradeService {
    List<ReportListEntity> getReportList(String teacherId);
    ReportEntity getReport(ReportEntityPK pk);
    List<ClassEntity> getMyClasses(String teacherId);
    Page<ReportListEntity> getReportListPaged(String teacherId,Integer pageNum,Integer pageSize);
    String save(GradeDto info);
    String release(String classId);
}
