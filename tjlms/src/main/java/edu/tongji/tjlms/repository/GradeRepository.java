package edu.tongji.tjlms.repository;

import edu.tongji.tjlms.model.GradeEntity;
import edu.tongji.tjlms.model.GradeEntityPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository("GradeRepository")
public interface GradeRepository extends JpaRepository<GradeEntity, GradeEntityPK>{
    @Transactional
    @Modifying
    @Query("UPDATE GradeEntity g SET g.visible=1 WHERE g.classId=?1")
    int release(String classId);
}
