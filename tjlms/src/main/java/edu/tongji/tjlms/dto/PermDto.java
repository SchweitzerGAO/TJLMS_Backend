package edu.tongji.tjlms.dto;

public class PermDto {
    private int type;
    private boolean grade;
    private boolean releaseLab;
    private String id;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public boolean isGrade() {
        return grade;
    }

    public void setGrade(boolean grade) {
        this.grade = grade;
    }

    public boolean isReleaseLab() {
        return releaseLab;
    }

    public void setReleaseLab(boolean releaseLab) {
        this.releaseLab = releaseLab;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
