package edu.tongji.tjlms.domain;

/**
 * @author Charles Gao
 * @date 2021/10/5
 * @description class Admin
 */
public class Admin extends GeneralUser
{
    private String telNum;

    public String getTelNum() {
        return telNum;
    }

    public void setTelNum(String telNum) {
        this.telNum = telNum;
    }
}
