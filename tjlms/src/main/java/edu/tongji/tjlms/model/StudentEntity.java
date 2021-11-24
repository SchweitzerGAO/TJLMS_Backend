package edu.tongji.tjlms.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "student", schema = "lms", catalog = "")
public class StudentEntity {
    private String id;
    private String emailAddr;
    private String name;
    private String password;
    private Boolean verified;
    private String classId;
    private Double attendance;

    @Id
    @Column(name = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "email_addr")
    public String getEmailAddr() {
        return emailAddr;
    }


    public void setEmailAddr(String emailAddr) {
        this.emailAddr = emailAddr;
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
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
    @Column(name = "attendance")
    public Double getAttendance() {
        return attendance;
    }

    public void setAttendance(Double attendance) {
        this.attendance = attendance;
    }

    @Basic
    @Column(name = "verified")
    public Boolean getVerified() {
        return verified;
    }


    public void setVerified(Boolean verified) {
        this.verified = verified;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentEntity that = (StudentEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(emailAddr, that.emailAddr) && Objects.equals(name, that.name) && Objects.equals(password, that.password) && Objects.equals(verified, that.verified) && Objects.equals(classId,that.classId) && Objects.equals(attendance,that.attendance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, emailAddr, name, password, verified, classId,attendance);
    }

}
