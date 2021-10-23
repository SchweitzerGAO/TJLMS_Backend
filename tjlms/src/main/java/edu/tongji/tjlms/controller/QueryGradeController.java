package edu.tongji.tjlms.controller;

import edu.tongji.tjlms.dto.QueryGradeDto;
import edu.tongji.tjlms.service.grade.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class QueryGradeController {
    @Autowired
    private GradeService gradeService;

    @GetMapping("/get/grade/{id}")
    @ResponseBody
    public ResponseEntity<?> getGrades(@PathVariable("id") String studentId)
    {
        try
        {
            List<QueryGradeDto> grades = gradeService.queryGrade(studentId);
            if(grades.isEmpty())
            {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("暂无成绩信息");
            }
            return ResponseEntity.status(HttpStatus.OK).body(grades);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("数据库请求错误");
        }
    }
}
