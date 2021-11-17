package edu.tongji.tjlms.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FindBackPwdDto {
    private int userType;
    private String id;
    private String newPwd;
    private String verificationCode;
}
