package edu.tongji.tjlms.repository;

import edu.tongji.tjlms.model.NoticeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeRepository extends JpaRepository<NoticeEntity,Integer> {
}
