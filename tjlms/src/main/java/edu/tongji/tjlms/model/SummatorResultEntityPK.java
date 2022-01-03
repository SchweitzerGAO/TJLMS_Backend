package edu.tongji.tjlms.model;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class SummatorResultEntityPK implements Serializable {
    private String stuId;
    private Integer b1;
    private Integer b2;
    private Integer b3;
    private Integer b4;
    private Integer s1;
    private Integer s2;
    private Integer s3;
    private Integer s4;
    private Integer c;

    @Column(name = "stu_id", nullable = false, length = 45)
    @Id
    public String getStuId() {
        return stuId;
    }

    public void setStuId(String stuId) {
        this.stuId = stuId;
    }

    @Column(name = "B1", nullable = false)
    @Id
    public Integer getB1() {
        return b1;
    }

    public void setB1(Integer b1) {
        this.b1 = b1;
    }

    @Column(name = "B2", nullable = false)
    @Id
    public Integer getB2() {
        return b2;
    }

    public void setB2(Integer b2) {
        this.b2 = b2;
    }

    @Column(name = "B3", nullable = false)
    @Id
    public Integer getB3() {
        return b3;
    }

    public void setB3(Integer b3) {
        this.b3 = b3;
    }

    @Column(name = "B4", nullable = false)
    @Id
    public Integer getB4() {
        return b4;
    }

    public void setB4(Integer b4) {
        this.b4 = b4;
    }

    @Column(name = "S1", nullable = false)
    @Id
    public Integer getS1() {
        return s1;
    }

    public void setS1(Integer s1) {
        this.s1 = s1;
    }

    @Column(name = "S2", nullable = false)
    @Id
    public Integer getS2() {
        return s2;
    }

    public void setS2(Integer s2) {
        this.s2 = s2;
    }

    @Column(name = "S3", nullable = false)
    @Id
    public Integer getS3() {
        return s3;
    }

    public void setS3(Integer s3) {
        this.s3 = s3;
    }

    @Column(name = "S4", nullable = false)
    @Id
    public Integer getS4() {
        return s4;
    }

    public void setS4(Integer s4) {
        this.s4 = s4;
    }

    @Column(name = "C", nullable = false)
    @Id
    public Integer getC() {
        return c;
    }

    public void setC(Integer c) {
        this.c = c;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SummatorResultEntityPK that = (SummatorResultEntityPK) o;
        return Objects.equals(stuId, that.stuId) && Objects.equals(b1, that.b1) && Objects.equals(b2, that.b2) && Objects.equals(b3, that.b3) && Objects.equals(b4, that.b4) && Objects.equals(s1, that.s1) && Objects.equals(s2, that.s2) && Objects.equals(s3, that.s3) && Objects.equals(s4, that.s4) && Objects.equals(c, that.c);
    }

    @Override
    public int hashCode() {
        return Objects.hash(stuId, b1, b2, b3, b4, s1, s2, s3, s4, c);
    }
}
