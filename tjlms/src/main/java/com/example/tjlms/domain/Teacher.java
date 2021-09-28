package com.example.tjlms.domain;

public class Teacher extends User
{
    private String telNum;
    private boolean isResp;
    private boolean isAssist;


    public String getTelNum() {
        return telNum;
    }

    public void setTelNum(String telNum) {
        this.telNum = telNum;
    }

    public boolean isResp() {
        return isResp;
    }

    public void setResp(boolean resp) {
        isResp = resp;
    }

    public boolean isAssist() {
        return isAssist;
    }

    public void setAssist(boolean assist) {
        isAssist = assist;
    }
}
