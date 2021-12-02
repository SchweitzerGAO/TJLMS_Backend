package edu.tongji.tjlms.controller;

import edu.tongji.tjlms.dto.InsertClassDto;
import edu.tongji.tjlms.dto.InsertStudentDto;
import edu.tongji.tjlms.model.ClassEntity;
import edu.tongji.tjlms.model.TakesEntity;
import edu.tongji.tjlms.model.TeacherEntity;
import edu.tongji.tjlms.service.clazz.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ClassController {
    @Autowired
    ClassService classService;

    @GetMapping("/get/resp")
    @ResponseBody
    public ResponseEntity<?> getResp()
    {
        try
        {
            List<TeacherEntity> ret = classService.getAllResp();
            if(ret != null)
            {
                return ResponseEntity.status(HttpStatus.OK).body(ret);
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("无责任教师信息");

        }
        catch (Exception e)
        {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("数据库请求错误");
        }
    }

    @GetMapping("/get/teacher")
    @ResponseBody
    public ResponseEntity<?> getTeacher()
    {
        try
        {
            List<TeacherEntity> ret = classService.getAllTeacher();
            if(ret != null)
            {
                return ResponseEntity.status(HttpStatus.OK).body(ret);
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("无普通教师信息");

        }
        catch (Exception e)
        {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("数据库请求错误");
        }
    }

    @GetMapping("/get/assist")
    @ResponseBody
    public ResponseEntity<?> getAssist()
    {
        try
        {
            List<TeacherEntity> ret = classService.getAllAssist();
            if(ret != null)
            {
                return ResponseEntity.status(HttpStatus.OK).body(ret);
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("无助教信息");

        }
        catch (Exception e)
        {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("数据库请求错误");
        }
    }

    @GetMapping("/get/classes")
    @ResponseBody
    public ResponseEntity<?> getClasses()
    {
        try
        {
            List<ClassEntity> ret = classService.getAllClasses();
            if(ret != null)
            {
                return ResponseEntity.status(HttpStatus.OK).body(ret);
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("无班级信息");

        }
        catch (Exception e)
        {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("数据库请求错误");
        }
    }

    @GetMapping("/get/students")
    @ResponseBody
    public ResponseEntity<?> getStudents(String classId)
    {
        try
        {
            List<TakesEntity> ret = classService.getAllStudentsByClassId(classId);
            if(ret != null)
            {
                return ResponseEntity.status(HttpStatus.OK).body(ret);
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("此班级暂无学生");

        }
        catch (Exception e)
        {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("数据库请求错误");
        }
    }



    @PostMapping("/post/insert/class")
    @ResponseBody
    public ResponseEntity<String> insertClass(@RequestBody InsertClassDto icd)
    {
        try
        {
            return ResponseEntity.status(HttpStatus.OK).body(classService.insertClass(icd));
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("数据库请求错误");
        }
    }

    @PostMapping("/post/delete/class")
    @ResponseBody
    public ResponseEntity<String> deleteClass(String id)
    {
        try
        {
            return ResponseEntity.status(HttpStatus.OK).body(classService.deleteClass(id));
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("数据库请求错误");
        }
    }

    @PostMapping("/post/insert/student")
    @ResponseBody
    public ResponseEntity<String> insertStudent(@RequestBody InsertStudentDto isd)
    {
        try
        {
            return ResponseEntity.status(HttpStatus.OK).body(classService.insertStudents(isd));
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("数据库请求错误");
        }
    }

    @PostMapping("/post/delete/student")
    @ResponseBody
    public ResponseEntity<String> deleteStudent(String id)
    {
        try
        {
            return ResponseEntity.status(HttpStatus.OK).body(classService.deleteStudent(id));
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("数据库请求错误");
        }
    }





}
