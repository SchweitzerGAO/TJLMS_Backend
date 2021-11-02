package edu.tongji.tjlms.controller;

import edu.tongji.tjlms.dto.PermDto;
import edu.tongji.tjlms.model.StudentEntity;
import edu.tongji.tjlms.model.TeacherEntity;
import edu.tongji.tjlms.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserManageController {
    @Autowired
    UserService userService;

    @GetMapping("/get/all/students")
    @ResponseBody
    public ResponseEntity<?> getAllStudents()
    {
        try
        {
            List<StudentEntity> students = userService.getAllStudents();
            return ResponseEntity.status(HttpStatus.OK).body(students);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("数据库请求错误");
        }
    }

    @GetMapping("/get/all/teachers")
    @ResponseBody
    public ResponseEntity<?> getAllTeachers()
    {
        try
        {
            List<TeacherEntity> students = userService.getAllTeachers();
            return ResponseEntity.status(HttpStatus.OK).body(students);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("数据库请求错误");
        }
    }

    @PostMapping("/post/students")
    @ResponseBody
    public ResponseEntity<String> insertStudents(String filePath)
    {
        try
        {
            String ret = userService.insertStudent(filePath);
            if(ret.equals("添加失败"))
            {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ret);
            }
            else
            {
                return ResponseEntity.status(HttpStatus.OK).body(ret);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("数据库请求错误");
        }
    }

    @PostMapping("/post/teachers")
    @ResponseBody
    public ResponseEntity<String> insertTeachers(@RequestBody String filePath)
    {
        try
        {
            String ret = userService.insertTeacher(filePath);
            if(ret.equals("添加失败"))
            {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ret);
            }
            else
            {
                return ResponseEntity.status(HttpStatus.OK).body(ret);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("数据库请求错误");
        }
    }

    @PostMapping("/delete/student/{id}")
    @ResponseBody
    public ResponseEntity<String> deleteStudentById(@PathVariable("id") String id)
    {
        try
        {
            return ResponseEntity.status(HttpStatus.OK).body(userService.deleteStudent(id));
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("删除失败");
        }
    }

    @PostMapping("/delete/teacher/{id}")
    @ResponseBody
    public ResponseEntity<String> deleteTeacherById(@PathVariable("id") String id)
    {
        try
        {
            return ResponseEntity.status(HttpStatus.OK).body(userService.deleteTeacher(id));
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("删除失败");
        }
    }

    @PostMapping("/post/modify/permission")
    @ResponseBody
    public ResponseEntity<String> modifyPerm(@RequestBody List<PermDto> list)
    {
        try
        {
            return ResponseEntity.status(HttpStatus.OK).body(userService.modifyPerm(list));
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("权限更改失败");
        }
    }



}
