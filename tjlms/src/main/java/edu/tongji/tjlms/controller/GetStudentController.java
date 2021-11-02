package edu.tongji.tjlms.controller;

import edu.tongji.tjlms.dto.GetStudentDto;
import edu.tongji.tjlms.model.TeacherEntity;
import edu.tongji.tjlms.model.TeachingStudentEntity;
import edu.tongji.tjlms.repository.TeacherRepository;
import edu.tongji.tjlms.service.finder.FinderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/api")
public class GetStudentController {
    @Autowired
    private FinderService finderService;
    @Resource
    TeacherRepository teacherRepository;

    @PostMapping("/get/students")
    @ResponseBody
    public ResponseEntity<?> getStudents(GetStudentDto info)
    {
        try
        {
            TeacherEntity teacher = teacherRepository.getById(info.getTeacherId());
            if(!teacher.getGrade())
            {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("暂无权限");
            }
            List<TeachingStudentEntity> list = finderService.findAllStudent(info.getTeacherId(),info.getLabId());
            if(list.isEmpty())
            {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("未找到学生信息");
            }
            return ResponseEntity.status(HttpStatus.OK).body(list);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("数据库请求错误");
        }

    }
}
