package edu.tongji.tjlms.service.clazz;

import edu.tongji.tjlms.dto.InsertClassDto;
import edu.tongji.tjlms.dto.InsertStudentDto;
import edu.tongji.tjlms.dto.InsertStudentsDto;
import edu.tongji.tjlms.model.ClassEntity;
import edu.tongji.tjlms.model.TakesEntity;
import edu.tongji.tjlms.model.TeacherEntity;
import edu.tongji.tjlms.repository.ClassRepository;
import edu.tongji.tjlms.repository.StudentRepository;
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

    @Resource
    StudentRepository studentRepository;


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
    public String insertStudents(InsertStudentsDto isd) {
        List<TakesEntity> list = ExcelResolverUtil.resolveClassStudent(isd.getFilePath(),isd.getClassId());
        if(list != null)
        {
            takesRepository.saveAll(list);
            int len = list.size();
            classRepository.updateNum(isd.getClassId(),len);
            return "添加成功";
        }
        return "添加失败";
    }

    @Override
    public String insertStudent(InsertStudentDto isd) {
        TakesEntity takes = new TakesEntity();
        takes.setStuId(isd.getStuId());
        takes.setClassId(isd.getClassId());
        takes.setStuName(studentRepository.findById(isd.getStuId()).get().getName());
        takesRepository.save(takes);
        return "学生添加成功";
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
        return teacherRepository.findAllByTypeAndVerified(0,true);
    }

    @Override
    public List<TeacherEntity> getAllTeacher() {
        List<TeacherEntity> list = getAllResp();
        list.addAll(teacherRepository.findAllByTypeAndVerified(1,true));
        return list;
    }

    @Override
    public List<TeacherEntity> getAllAssist() {
        return teacherRepository.findAllByTypeAndVerified(2,true);
    }
}
