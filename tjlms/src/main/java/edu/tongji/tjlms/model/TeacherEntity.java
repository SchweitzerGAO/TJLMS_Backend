package edu.tongji.tjlms.model;

import com.sun.org.apache.xpath.internal.operations.Bool;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "teacher", schema = "lms", catalog = "")
public class TeacherEntity {
    private String id;
    private String emailAddr;
    private String name;
    private String password;
    private String telNum;
    private Boolean verified;
    private Boolean isResp;
    private Boolean isAssist;
    private Boolean canGrade;

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
    @Column(name = "tel_num")
    public String getTelNum() {
        return telNum;
    }

    public void setTelNum(String telNum) {
        this.telNum = telNum;
    }

    @Basic
    @Column(name = "verified")
    public Boolean getVerified() {
        return verified;
    }

    public void setVerified(Boolean verified) {
        this.verified = verified;
    }

    @Basic
    @Column(name = "is_resp")
    public Boolean getIsResp() {
        return isResp;
    }

    public void setIsResp(Boolean isResp) {
        this.isResp = isResp;
    }

    @Basic
    @Column(name = "is_assist")
    public Boolean getIsAssist() {
        return isAssist;
    }

    public void setIsAssist(Boolean isAssist) {
        this.isAssist = isAssist;
    }

    @Basic
    @Column(name = "can_grade")
    public Boolean getCanGrade() {
        return canGrade;
    }

    public void setCanGrade(Boolean canGrade) {
        this.canGrade = canGrade;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TeacherEntity that = (TeacherEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(emailAddr, that.emailAddr) && Objects.equals(name, that.name) && Objects.equals(password, that.password) && Objects.equals(telNum, that.telNum) && Objects.equals(verified, that.verified) && Objects.equals(isResp, that.isResp) && Objects.equals(isAssist, that.isAssist) && Objects.equals(canGrade, that.canGrade);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, emailAddr, name, password, telNum, verified, isResp, isAssist, canGrade);
    }
}
