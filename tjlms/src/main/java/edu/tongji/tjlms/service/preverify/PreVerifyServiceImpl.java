package edu.tongji.tjlms.service.preverify;

import edu.tongji.tjlms.dto.PreVerifyDto;
import edu.tongji.tjlms.model.StudentEntity;
import edu.tongji.tjlms.model.TeacherEntity;
import edu.tongji.tjlms.repository.StudentRepository;
import edu.tongji.tjlms.repository.TeacherRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Optional;

@Service
public class PreVerifyServiceImpl implements PreVerifyService{
    @Resource
    StudentRepository studentRepository;
    @Resource
    TeacherRepository teacherRepository;
    @Override
    public boolean exists(PreVerifyDto pvd) {
        int userType = pvd.getUserType();
        String id = pvd.getId();
        switch (userType)
        {
            case 1:
            {
                Optional<StudentEntity> student = studentRepository.findById(id);
                return student.isPresent();
            }
            case 2:
            {
                Optional<TeacherEntity> teacher = teacherRepository.findById(id);
                return teacher.isPresent();
            }
        }
        return false;
    }
}
