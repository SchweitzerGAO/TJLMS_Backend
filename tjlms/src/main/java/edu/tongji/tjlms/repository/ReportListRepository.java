package edu.tongji.tjlms.repository;

import edu.tongji.tjlms.model.ReportListEntity;
import edu.tongji.tjlms.model.ReportListPK;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReportListRepository extends JpaRepository<ReportListEntity, ReportListPK> {
    List<ReportListEntity> findAllByTeacherId(String id);
    Page<ReportListEntity> findAllByTeacherId(String id,Pageable pageable);
}
