package edu.tongji.tjlms.repository;

import edu.tongji.tjlms.model.FeedbackEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FeedbackRepository extends JpaRepository<FeedbackEntity,Integer> {
    List<FeedbackEntity> findAllByFrom(String from);

    List<FeedbackEntity> findAllByTo(String to);
}
