package edu.tongji.tjlms.repository;

import edu.tongji.tjlms.model.ReportEntity;
import edu.tongji.tjlms.model.ReportEntityPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportRepository extends JpaRepository<ReportEntity, ReportEntityPK> {
    ReportEntity findByStuIdAndLabId(String stuId,Integer labId);
    Long countByStuId(String id);
}
