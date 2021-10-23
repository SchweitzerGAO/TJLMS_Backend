package edu.tongji.tjlms.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GradeDto {
    private String stuId;
    private String classId;
    private String teacherId;
    private String labId;
    private double score;
    private String note;

}
