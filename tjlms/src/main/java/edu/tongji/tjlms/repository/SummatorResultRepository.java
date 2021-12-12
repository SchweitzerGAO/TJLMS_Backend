package edu.tongji.tjlms.repository;

import edu.tongji.tjlms.model.SummatorResultEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SummatorResultRepository extends JpaRepository<SummatorResultEntity,String> {
    List<SummatorResultEntity> findAllByStuId(String id);

}
