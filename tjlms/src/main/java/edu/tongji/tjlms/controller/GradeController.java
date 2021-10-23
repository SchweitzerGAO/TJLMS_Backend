package edu.tongji.tjlms.controller;

import edu.tongji.tjlms.dto.GradeDto;
import edu.tongji.tjlms.service.grade.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class GradeController {
    @Autowired
    GradeService gradeService;

    @PostMapping("/post/grade/save")
    @ResponseBody
    public ResponseEntity<String> save(@RequestBody List<GradeDto> list)
    {
        try
        {
            gradeService.saveGrade(list);
            return ResponseEntity.status(HttpStatus.OK).body("暂存成功");
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("数据库请求失败");
        }
    }

    @PostMapping("/post/grade/release")
    @ResponseBody
    public ResponseEntity<String> release()
    {
        try
        {
            gradeService.releaseGrade();
            return ResponseEntity.status(HttpStatus.OK).body("发布成功");
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("数据库请求失败");
        }
    }
}
