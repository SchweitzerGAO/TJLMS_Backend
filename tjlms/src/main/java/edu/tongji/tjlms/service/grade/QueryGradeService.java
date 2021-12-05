package edu.tongji.tjlms.service.grade;

import edu.tongji.tjlms.dto.QueryGradeDto;

import java.util.List;

public interface QueryGradeService {
    List<QueryGradeDto> queryGrade(String id);
}
