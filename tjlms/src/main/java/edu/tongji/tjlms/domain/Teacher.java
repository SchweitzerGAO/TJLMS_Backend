package edu.tongji.tjlms.domain;
/**
 * @author Charles Gao
 * @date 2021/10/5
 * @description class Teacher
 */
public class Teacher extends User
{
    private String telNum;
    private boolean resp;
    private boolean assist;


    public String getTelNum() {
        return telNum;
    }

    public void setTelNum(String telNum) {
        this.telNum = telNum;
    }

    public boolean isResp() {
        return resp;
    }

    public void setResp(boolean resp) {
        this.resp = resp;
    }

    public boolean isAssist() {
        return assist;
    }

    public void setAssist(boolean assist) {
        this.assist = assist;
    }
}
