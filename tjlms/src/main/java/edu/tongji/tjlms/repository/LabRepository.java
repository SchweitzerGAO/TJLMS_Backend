package edu.tongji.tjlms.repository;

import edu.tongji.tjlms.model.LabEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LabRepository extends JpaRepository<LabEntity,Integer> {
}
