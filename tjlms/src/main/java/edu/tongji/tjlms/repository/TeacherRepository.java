package edu.tongji.tjlms.repository;


import edu.tongji.tjlms.model.TeacherEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository("TeacherRepository")
public interface TeacherRepository extends JpaRepository<TeacherEntity,String> {
    Optional<TeacherEntity> findByEmailAddrAndPassword(String email, String password);
    @Transactional
    @Modifying
    @Query("UPDATE TeacherEntity t SET t.emailAddr=?1,t.password=?2,t.verified=1 WHERE t.id=?3")
    int verify( String email, String password, String id);
}
