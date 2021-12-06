package edu.tongji.tjlms.repository;

import edu.tongji.tjlms.model.ReplyEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReplyRepository extends JpaRepository<ReplyEntity,Integer> {
    List<ReplyEntity> findAllByFrom(String from);

    List<ReplyEntity> findAllByTo(String to);

    Page<ReplyEntity> findAllByFrom(String from, Pageable pageable);

    Page<ReplyEntity> findAllByTo(String to, Pageable pageable);
}
