package edu.tongji.tjlms.repository;

import edu.tongji.tjlms.model.GradeEntity;
import edu.tongji.tjlms.model.GradeEntityPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GradeRepository extends JpaRepository<GradeEntity, GradeEntityPK> {
}
