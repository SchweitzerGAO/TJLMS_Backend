package edu.tongji.tjlms.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "lab", schema = "lms", catalog = "")
public class LabEntity {
    private String id;
    private String name;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LabEntity labEntity = (LabEntity) o;
        return Objects.equals(id, labEntity.id) && Objects.equals(name, labEntity.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
