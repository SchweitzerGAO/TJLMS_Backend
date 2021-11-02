package edu.tongji.tjlms.service.user;

import edu.tongji.tjlms.dto.PermDto;
import edu.tongji.tjlms.model.StudentEntity;
import edu.tongji.tjlms.model.TeacherEntity;

import java.util.List;

public interface UserService {
    List<StudentEntity> getAllStudents();
    List<TeacherEntity> getAllTeachers();
    String insertStudent(String filePath);
    String insertTeacher(String filePath);
    String deleteStudent(String id);
    String deleteTeacher(String id);
    String modifyPerm(List<PermDto> list);
}
