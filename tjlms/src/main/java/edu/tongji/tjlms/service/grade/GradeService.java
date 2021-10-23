package edu.tongji.tjlms.service.grade;

import edu.tongji.tjlms.dto.GradeDto;
import edu.tongji.tjlms.dto.QueryGradeDto;

import java.util.List;

public interface GradeService {
    void saveGrade(List<GradeDto> list);
    void releaseGrade();
    List<QueryGradeDto> queryGrade(String studentId);
}
