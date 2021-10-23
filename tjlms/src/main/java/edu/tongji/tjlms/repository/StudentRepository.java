package edu.tongji.tjlms.repository;

import edu.tongji.tjlms.model.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository("StudentRepository")
public interface StudentRepository extends JpaRepository<StudentEntity,String> {
        Optional<StudentEntity> findByEmailAddrAndPassword(String emailAddr, String password);

        @Transactional
        @Modifying
        @Query("UPDATE StudentEntity s SET s.emailAddr=?1,s.password=?2,s.verified=1 WHERE s.id=?3")
        int verify(String email, String password, String id);
}
