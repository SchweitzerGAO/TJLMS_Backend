package edu.tongji.tjlms.controller;

import edu.tongji.tjlms.service.email.EmailSendServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@ResponseBody
public class VerificationCodeCheckController {
    @PostMapping("post/check/code")
    public ResponseEntity<Boolean> checkCode(String code)
    {
        String verificationCode = EmailSendServiceImpl.getVerificationCode();
        System.out.println(verificationCode);
        // compare the verification code
        if(!code.equals(verificationCode))
        {
            EmailSendServiceImpl.setVerificationCodeToEmpty();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(false);
        }
        EmailSendServiceImpl.setVerificationCodeToEmpty();
        return ResponseEntity.status(HttpStatus.OK).body(true);
    }

}
