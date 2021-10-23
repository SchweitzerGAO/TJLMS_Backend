package edu.tongji.tjlms.controller;

import edu.tongji.tjlms.model.LabEntity;
import edu.tongji.tjlms.service.lab.LabService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class GetLabController {
    @Autowired
    LabService labService;

    @GetMapping("/get/lab")
    @ResponseBody
    public ResponseEntity<?> getAllLabs()
    {
        try
        {
            List<LabEntity> labs = labService.getAllLabs();
            return ResponseEntity.status(HttpStatus.OK).body(labs);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("数据库请求错误");
        }


    }
}
