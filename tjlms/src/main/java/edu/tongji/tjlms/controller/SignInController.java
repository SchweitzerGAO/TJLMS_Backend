package edu.tongji.tjlms.controller;

import edu.tongji.tjlms.dto.EmailDto;
import edu.tongji.tjlms.dto.SignInDto;
import edu.tongji.tjlms.model.StudentEntity;
import edu.tongji.tjlms.model.TeacherEntity;
import edu.tongji.tjlms.repository.StudentRepository;
import edu.tongji.tjlms.repository.TeacherRepository;
import edu.tongji.tjlms.util.EncryptSha256Util;
import edu.tongji.tjlms.util.VerificationCodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Optional;

/**
 * @author Charles Gao
 * @date 2021/10/5
 * @description the APIs for email and signing in
 */
@RestController
@RequestMapping("/api")
public class SignInController {
    @Autowired
    private JavaMailSender jms;

    // the sender value is in ./resources/application.properties
    @Value("${spring.mail.username}")
    private String sender;
    @Resource
    private StudentRepository studentRepository;
    @Resource
    private TeacherRepository teacherRepository;

    private String verificationCode = "";
    // role "ADMIN" is always verified

    /**
     * send email
     * @param ed the data for sending the email
     * @return the response code with information
     */
    @PostMapping("/sendEmail")
    @ResponseBody
    public ResponseEntity<String> sendEmail(@RequestBody EmailDto ed)
    {
        // generate verification code
        verificationCode = VerificationCodeUtil.generateCode();

        // query the information from the DBMS
        String id = ed.getId();
        String name = "";
        int userType = ed.getUserType();
        try
        {
            switch (userType)
            {
                // student
                case 1:
                {
                    // JPA code
                    Optional<StudentEntity> student = studentRepository.findById(id);
                    if(!student.isPresent())
                    {
                        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("学工号错误");
                    }
                    if(student.get().getVerified())
                    {
                        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("不能重复激活");
                    }
                    name = student.get().getName();
                    break;
                }
                // teacher
                case 2:
                {
                    // JPA code
                    Optional<TeacherEntity> teacher = teacherRepository.findById(id);
                    if(!teacher.isPresent())
                    {
                        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("学工号错误");
                    }
                    if(teacher.get().getVerified())
                    {
                        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("不能重复激活");
                    }
                    name = teacher.get().getName();
                    break;
                }
            }
            // set the email
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(sender);  // the sender
            message.setTo(ed.getEmailAddress());  // the receiver
            message.setSubject("TJLMS邮箱验证");  // subject
            // the text
            final String text = "您的验证码是："+ verificationCode+"\n如注册遇到问题，请发送问题至本邮箱；若非您本人操作，请忽略此邮件。"+"\nTJLMS实验管理系统";
            String head = name+((userType == 1) ? "同学：\n" :"老师：\n")+"您好！\n";
            String mainMsg = head+text;
            message.setText(mainMsg);
            // send it!
            jms.send(message);
            return ResponseEntity.status(HttpStatus.OK).body("发送成功");
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("发送失败");
        }
    }

    /**
     * sign in
     * @param sid the data for signing in
     * @return response code with information
     */
    @PostMapping("/signIn")
    @ResponseBody
    public ResponseEntity<String> signIn(@RequestBody SignInDto sid)
    {
        try
        {
            // compare the verification code
            if(!sid.getVerificationCode().equals(this.verificationCode))
            {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("验证码错误");
            }
            verificationCode = "";
            int userType = sid.getUserType();
            String id = sid.getId();
            String email = sid.getEmailAddress();
            // encrypt the password
            String password = EncryptSha256Util.getSha256Str(sid.getPassword());
            // JPA code
            int res;
            switch (userType)
            {
                case 1:
                {
                   res = studentRepository.verify(email,password,id);
                   if(res == 0)
                   {
                       return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("注册失败");
                   }
                   break;

                }
                case 2:
                {
                    res = teacherRepository.verify(email,password,id);
                    if(res == 0)
                    {
                        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("注册失败");
                    }
                    break;
                }
            }
            return ResponseEntity.status(HttpStatus.OK).body("注册成功");

        }
        catch (Exception e)
        {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("数据库请求错误");
        }

    }

}

