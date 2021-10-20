package edu.tongji.tjlms.repository;


import edu.tongji.tjlms.model.TeacherEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
@Repository("TeacherRepository")
public interface TeacherRepository extends JpaRepository<TeacherEntity,String> {
    Optional<TeacherEntity> findByEmailAddrAndPassword(String email, String password);
    @Transactional
    @Modifying
    @Query(nativeQuery = true,value = "UPDATE teacher t SET t.email_addr=:email,t.password=:password,t.verified=1 WHERE t.id=:id")
    int verify(@Param("email") String email, @Param("password") String password, @Param("id") String id);
}
