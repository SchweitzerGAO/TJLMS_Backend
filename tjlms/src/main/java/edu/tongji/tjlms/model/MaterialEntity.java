package edu.tongji.tjlms.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "material", schema = "lms", catalog = "")
@IdClass(MaterialEntityPK.class)
public class MaterialEntity {
    private int labId;
    private String location;

    @Id
    @Column(name = "lab_id")
    public int getLabId() {
        return labId;
    }

    public void setLabId(int labId) {
        this.labId = labId;
    }

    @Id
    @Column(name = "location")
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MaterialEntity that = (MaterialEntity) o;
        return labId == that.labId && Objects.equals(location, that.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(labId, location);
    }
}
