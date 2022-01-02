package edu.tongji.tjlms.repository;

import edu.tongji.tjlms.model.ClassEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ClassRepository extends JpaRepository<ClassEntity,String> {
    @Transactional
    @Modifying
    @Query("UPDATE ClassEntity Set stuNum=?2 where id=?1")
    void updateNum(String id,Integer num);

    List<ClassEntity> findAllByTeacherIdOrRespIdOrAssistId(String teacherId,String respId,String assistId);
    ClassEntity findAllById(String id);
}
