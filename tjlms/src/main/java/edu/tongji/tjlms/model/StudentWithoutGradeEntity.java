package edu.tongji.tjlms.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "student_without_grade", schema = "lms", catalog = "")
public class StudentWithoutGradeEntity {
    private String id;
    private String name;
    private String classId;
    private String teacherId;
    private String respId;
    private String assistId;

    @Id
    @Column(name = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "class_id")
    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    @Basic
    @Column(name = "teacher_id")
    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    @Basic
    @Column(name = "resp_id")
    public String getRespId() {
        return respId;
    }

    public void setRespId(String respId) {
        this.respId = respId;
    }

    @Basic
    @Column(name = "assist_id")
    public String getAssistId() {
        return assistId;
    }

    public void setAssistId(String assistId) {
        this.assistId = assistId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentWithoutGradeEntity that = (StudentWithoutGradeEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(classId, that.classId) && Objects.equals(teacherId, that.teacherId) && Objects.equals(respId, that.respId) && Objects.equals(assistId, that.assistId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, classId, teacherId, respId, assistId);
    }
}
