package edu.tongji.tjlms.repository;

import edu.tongji.tjlms.model.StudentWithoutGradeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentWithoutGradeRepository extends JpaRepository<StudentWithoutGradeEntity,String> {

}
