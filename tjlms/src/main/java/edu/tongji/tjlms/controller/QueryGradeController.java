package edu.tongji.tjlms.controller;

import edu.tongji.tjlms.dto.FinalGradeDto;
import edu.tongji.tjlms.service.grade.QueryGradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@ResponseBody
public class QueryGradeController {
    @Autowired
    private QueryGradeService queryGradeService;

    @GetMapping("/get/grade/{id}")
    public ResponseEntity<?> getGrades(@PathVariable("id") String studentId)
    {
        try
        {
            FinalGradeDto grade = queryGradeService.queryFinalGrade(studentId);
            if(grade.getEachGrades().isEmpty())
            {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("暂无成绩信息");
            }
            return ResponseEntity.status(HttpStatus.OK).body(grade);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("数据库请求错误");
        }
    }
}
