package edu.tongji.tjlms.service.clazz;

import edu.tongji.tjlms.dto.InsertClassDto;
import edu.tongji.tjlms.dto.InsertStudentDto;
import edu.tongji.tjlms.model.ClassEntity;
import edu.tongji.tjlms.model.TakesEntity;
import edu.tongji.tjlms.model.TeacherEntity;
import edu.tongji.tjlms.repository.ClassRepository;
import edu.tongji.tjlms.repository.TakesRepository;
import edu.tongji.tjlms.repository.TeacherRepository;
import edu.tongji.tjlms.util.ExcelResolverUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ClassServiceImpl implements ClassService{

    @Resource
    ClassRepository classRepository;
    @Resource
    TeacherRepository teacherRepository;
    @Resource
    TakesRepository takesRepository;


    @Override
    public String insertClass(InsertClassDto icd) {
        ClassEntity ce = new ClassEntity();
        ce.setId(icd.getClassId());
        ce.setAssistId(icd.getAssistId());
        ce.setRespId(icd.getRespId());
        ce.setTeacherId(icd.getTeacherId());
        ce.setStuNum(0);
        classRepository.save(ce);
        return "班级添加成功";
    }

    @Override
    public String deleteClass(String id) {
        classRepository.deleteById(id);
        return "班级删除成功";
    }

    @Override
    public List<ClassEntity> getAllClasses() {
        return classRepository.findAll();
    }

    @Override
    public String insertStudents(InsertStudentDto isd) {
        List<TakesEntity> list = ExcelResolverUtil.resolveClassStudent(isd.getFilePath(),isd.getClassId());
        if(list != null)
        {
            takesRepository.saveAll(list);
            return "添加成功";
        }
        return "添加失败";
    }

    @Override
    public String deleteStudent(String id) {
        takesRepository.deleteByStuId(id);
        return "学生删除成功";
    }

    @Override
    public List<TakesEntity> getAllStudentsByClassId(String id) {
        return takesRepository.findAllByClassId(id);

    }

    @Override
    public List<TeacherEntity> getAllResp() {
        return teacherRepository.findAllByType(0);
    }

    @Override
    public List<TeacherEntity> getAllTeacher() {
        return teacherRepository.findAllByType(1);
    }

    @Override
    public List<TeacherEntity> getAllAssist() {
        return teacherRepository.findAllByType(2);
    }
}
