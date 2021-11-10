package edu.tongji.tjlms.model;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class ReportEntityPK implements Serializable {
    private String stuId;
    private String labId;
    private String classId;

    @Column(name = "stu_id")
    @Id
    public String getStuId() {
        return stuId;
    }

    public void setStuId(String stuId) {
        this.stuId = stuId;
    }

    @Column(name = "lab_id")
    @Id
    public String getLabId() {
        return labId;
    }

    public void setLabId(String labId) {
        this.labId = labId;
    }

    @Column(name = "class_id")
    @Id
    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReportEntityPK that = (ReportEntityPK) o;
        return Objects.equals(stuId, that.stuId) && Objects.equals(labId, that.labId) && Objects.equals(classId, that.classId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(stuId, labId, classId);
    }
}
