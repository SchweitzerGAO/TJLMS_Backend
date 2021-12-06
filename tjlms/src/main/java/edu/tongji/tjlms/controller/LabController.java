package edu.tongji.tjlms.controller;

import edu.tongji.tjlms.dto.LabDto;
import edu.tongji.tjlms.model.LabEntity;
import edu.tongji.tjlms.service.lab.LabService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@ResponseBody
public class LabController {
    @Autowired
    LabService labService;

    @GetMapping("/get/labs")
    public ResponseEntity<?> getAllLabs()
    {
        try
        {
            List<LabEntity> labs = labService.getAll();
            return ResponseEntity.status(HttpStatus.OK).body(labs);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("数据库请求错误");
        }
    }

    @PostMapping("/post/release")
    public ResponseEntity<String> releaseLab(@RequestBody LabDto lab)
    {
        try
        {
            String ret = labService.releaseLab(lab);
            return ResponseEntity.status(HttpStatus.OK).body(ret);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("数据库请求错误");
        }
    }
}
