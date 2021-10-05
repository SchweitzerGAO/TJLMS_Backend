package edu.tongji.tjlms.domain;

/**
 * @author Charles Gao
 * @date 2021/10/5
 * @description class Student
 */
public class Student extends User{
    private String classId;

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }
}
