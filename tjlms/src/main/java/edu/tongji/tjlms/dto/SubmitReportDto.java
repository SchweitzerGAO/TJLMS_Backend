package edu.tongji.tjlms.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SubmitReportDto {
    private String stuId;
    private String labId;
    private String classId;
    private String aim;
    private String principle;
    private String step;
    private String result;
}
