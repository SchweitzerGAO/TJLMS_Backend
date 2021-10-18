package edu.tongji.tjlms.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "grade", schema = "lms", catalog = "")
@IdClass(GradeEntityPK.class)
public class GradeEntity {
    private String stuId;
    private String updateDate;
    private String labId;
    private Double score;
    private Byte visible;

    @Id
    @Column(name = "stu_id")
    public String getStuId() {
        return stuId;
    }

    public void setStuId(String stuId) {
        this.stuId = stuId;
    }

    @Basic
    @Column(name = "update_date")
    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    @Id
    @Column(name = "lab_id")
    public String getLabId() {
        return labId;
    }

    public void setLabId(String labId) {
        this.labId = labId;
    }

    @Basic
    @Column(name = "score")
    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    @Basic
    @Column(name = "visible")
    public Byte getVisible() {
        return visible;
    }

    public void setVisible(Byte visible) {
        this.visible = visible;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GradeEntity that = (GradeEntity) o;
        return Objects.equals(stuId, that.stuId) && Objects.equals(updateDate, that.updateDate) && Objects.equals(labId, that.labId) && Objects.equals(score, that.score) && Objects.equals(visible, that.visible);
    }

    @Override
    public int hashCode() {
        return Objects.hash(stuId, updateDate, labId, score, visible);
    }
}
