package edu.tongji.tjlms.controller;

import edu.tongji.tjlms.dto.PostCheckDto;
import edu.tongji.tjlms.model.CheckEntity;
import edu.tongji.tjlms.service.check.CheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@ResponseBody
@RequestMapping("/api")
public class CheckController {

    @Autowired
    CheckService checkService;

    @PostMapping("post/check")
    public ResponseEntity<String> postCheck(@RequestBody PostCheckDto pcd)
    {
        try
        {
            return ResponseEntity.status(HttpStatus.OK).body(checkService.postCheck(pcd));
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("数据库请求错误");
        }
    }

    @GetMapping("/get/check")
    public ResponseEntity<?> getCheck(String stuId)
    {
        try
        {
            List<CheckEntity> checks = checkService.getAllCheckByStuId(stuId);
            if(checks == null)
            {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("暂无签到信息");
            }
            return ResponseEntity.status(HttpStatus.OK).body(checks);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("数据库请求错误");
        }

    }

    @PostMapping("/check/in")
    public ResponseEntity<String> checkIn(String stuId,Integer checkId)
    {
        try
        {
            return ResponseEntity.status(HttpStatus.OK).body(checkService.submitCheck(stuId,checkId));
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("数据库请求错误");
        }
    }
}
