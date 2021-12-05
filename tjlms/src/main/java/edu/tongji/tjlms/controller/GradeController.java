package edu.tongji.tjlms.controller;

import edu.tongji.tjlms.dto.GradeDto;
import edu.tongji.tjlms.model.ReportEntity;
import edu.tongji.tjlms.model.ReportEntityPK;
import edu.tongji.tjlms.model.ReportListEntity;
import edu.tongji.tjlms.service.grade.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@ResponseBody
public class GradeController {
    @Autowired
    GradeService gradeService;

    @GetMapping("/get/report/list")
    public ResponseEntity<?> getReportList(String teacherId)
    {
        try
        {
            List<ReportListEntity> list = gradeService.getReportList(teacherId);
            if(list != null)
            {
                return ResponseEntity.status(HttpStatus.OK).body(list);
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("暂无实验报告信息");
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("数据库请求错误");
        }
    }

    @GetMapping("/get/report/detail")
    public ResponseEntity<?> getReportDetail(ReportEntityPK pk)
    {
        try
        {
            ReportEntity report = gradeService.getReport(pk);
            if(report != null)
            {
                return ResponseEntity.status(HttpStatus.OK).body(report);
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("暂无此学生报告信息");
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("数据库请求错误");
        }
    }

    @PostMapping("/post/save/grade")
    public ResponseEntity<String> saveGrade(@RequestBody GradeDto grade)
    {
        try
        {
            return ResponseEntity.status(HttpStatus.OK).body(gradeService.save(grade));
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("数据库请求错误");
        }
    }

    @PostMapping("/post/release/grade")
    public ResponseEntity<String> releaseGrade(String classId)
    {
        try
        {
            return ResponseEntity.status(HttpStatus.OK).body(gradeService.release(classId));
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("数据库请求错误");
        }
    }

}
