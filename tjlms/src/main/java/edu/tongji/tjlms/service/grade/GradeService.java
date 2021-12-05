package edu.tongji.tjlms.service.grade;

import edu.tongji.tjlms.dto.GradeDto;
import edu.tongji.tjlms.model.ReportEntity;
import edu.tongji.tjlms.model.ReportEntityPK;
import edu.tongji.tjlms.model.ReportListEntity;

import java.util.List;

public interface GradeService {
    List<ReportListEntity> getReportList(String teacherId);
    ReportEntity getReport(ReportEntityPK pk);
    String save(GradeDto info);
    String release(String classId);
}
