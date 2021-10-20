package edu.tongji.tjlms.controller;

import edu.tongji.tjlms.dto.LoginDto;
import edu.tongji.tjlms.service.login.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author Charles Gao
 * @description login webAPI
 * @date 2021/10/11
 */

@RestController
@RequestMapping("/api")
public class LoginController {
    @Autowired
    private LoginService loginService;
    /**
     * login
     * @param ld login DTO info
     * @return the user's necessary information
     */
    @PostMapping("/post/login")
    @ResponseBody
    public ResponseEntity<String> login(@RequestBody LoginDto ld)
    {

        try
        {
           String ret = loginService.login(ld);
           if(ret.equals("登录成功"))
           {
               return ResponseEntity.status(HttpStatus.OK).body(ret);
           }
           else
           {
               return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ret);
           }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("数据库请求错误");
        }
    }
}
