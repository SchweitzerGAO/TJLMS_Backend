package edu.tongji.tjlms.service.finder;

import edu.tongji.tjlms.model.TeachingStudentEntity;

import java.util.List;

public interface FinderService {
    List<TeachingStudentEntity> findAllStudent(String teacherId,Integer
            labId);
}
