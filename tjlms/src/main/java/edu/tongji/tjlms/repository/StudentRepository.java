package edu.tongji.tjlms.repository;

import edu.tongji.tjlms.model.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
@Repository("StudentRepository")
public interface StudentRepository extends JpaRepository<StudentEntity,String> {
        Optional<StudentEntity> findById(String id);
        Optional<StudentEntity> findByEmailAddrAndPassword(String emailAddr, String password);
        @Transactional
        @Modifying
        @Query(nativeQuery = true,value="UPDATE student s SET s.email_addr=:email,s.password=:password,s.verified=1 WHERE s.id=:id")
        int verify(@Param("email") String email, @Param("password") String password, @Param("id") String id);
}
