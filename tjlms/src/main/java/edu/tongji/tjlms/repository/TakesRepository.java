package edu.tongji.tjlms.repository;

import edu.tongji.tjlms.model.TakesEntity;
import edu.tongji.tjlms.model.TakesEntityPK;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TakesRepository extends JpaRepository<TakesEntity, TakesEntityPK> {
    void deleteByStuId(String id);

    List<TakesEntity> findAllByClassId(String classId);
}
