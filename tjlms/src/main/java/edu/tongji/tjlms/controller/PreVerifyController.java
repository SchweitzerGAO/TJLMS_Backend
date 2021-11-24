package edu.tongji.tjlms.controller;

import edu.tongji.tjlms.dto.PreVerifyDto;
import edu.tongji.tjlms.service.preverify.PreVerifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class PreVerifyController {
    @Autowired
    private PreVerifyService preVerifyService;


    @PostMapping("/post/preVerify")
    @ResponseBody
    public ResponseEntity<Boolean> preVerify(@RequestBody PreVerifyDto pvd)
    {
        boolean exist = preVerifyService.exists(pvd);
        if(exist)
        {
            return ResponseEntity.status(HttpStatus.OK).body(exist);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exist);
    }

}
