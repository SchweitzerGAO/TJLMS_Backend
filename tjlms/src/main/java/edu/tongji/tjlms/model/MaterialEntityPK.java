package edu.tongji.tjlms.model;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class MaterialEntityPK implements Serializable {
    private int labId;
    private String location;

    @Column(name = "lab_id")
    @Id
    public int getLabId() {
        return labId;
    }

    public void setLabId(int labId) {
        this.labId = labId;
    }

    @Column(name = "location")
    @Id
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
        MaterialEntityPK that = (MaterialEntityPK) o;
        return labId == that.labId && Objects.equals(location, that.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(labId, location);
    }
}
