package edu.tongji.tjlms.dto;

import edu.tongji.tjlms.model.QueryGradeEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QueryGradeDto {
    private QueryGradeEntity queryGradeEntity;
    private String grade;
}
