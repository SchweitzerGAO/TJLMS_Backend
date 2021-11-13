package edu.tongji.tjlms.repository;

import edu.tongji.tjlms.model.TeachingStudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("StudentFinderRepository")
public interface StudentFinderRepository extends JpaRepository<TeachingStudentEntity, String>,
        JpaSpecificationExecutor<TeachingStudentEntity> {

    @Query("SELECT DISTINCT classId FROM TeachingStudentEntity WHERE teacherId=?1")
    List<String> getClasses(String teacherId);

}
