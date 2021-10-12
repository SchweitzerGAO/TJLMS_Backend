package edu.tongji.tjlms.controller;

import edu.tongji.tjlms.dto.LoginDto;
import edu.tongji.tjlms.model.AdminEntity;
import edu.tongji.tjlms.model.StudentEntity;
import edu.tongji.tjlms.model.TeacherEntity;
import edu.tongji.tjlms.repository.AdminRepository;
import edu.tongji.tjlms.repository.StudentRepository;
import edu.tongji.tjlms.repository.TeacherRepository;
import edu.tongji.tjlms.util.EncryptSha256Util;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Optional;

/**
 * @author Charles Gao
 * @description login webAPI
 * @date 2021/10/11
 */

@RestController
@RequestMapping("/api")
public class LoginController {
    @Resource
    private AdminRepository adminRepository;
    @Resource
    private StudentRepository studentRepository;
    @Resource
    private TeacherRepository teacherRepository;

    /**
     * login
     * @param l login info
     * @return the user's necessary information
     */
    @PostMapping("/login")
    @ResponseBody
    public ResponseEntity<String> login(@RequestBody LoginDto l)
    {

        try
        {
            int userType = l.getUserType();
            String email = l.getEmailAddress();
            String password = EncryptSha256Util.getSha256Str(l.getPassword());
            switch(userType)
            {
                // Admin
                case 0:
                {
                    // JPA code
                    Optional<AdminEntity> admin = adminRepository.findByEmailAddrAndPassword(email, password);
                    if(!admin.isPresent())
                    {
                        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("邮箱或密码错误。");
                    }
                    return ResponseEntity.status(HttpStatus.OK).body("登录成功");
                }

                // Student
                case 1:
                {
                    // JPA code
                    Optional<StudentEntity> student = studentRepository.findByEmailAddrAndPassword(email,password);
                    if(!student.isPresent())
                    {
                        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("邮箱或密码错误");
                    }
                    if(!student.get().getVerified())
                    {
                        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("尚未激活");
                    }
                    return ResponseEntity.status(HttpStatus.OK).body("登录成功");
                }

                // Teacher
                case 2:
                {
                    // JPA code
                    Optional<TeacherEntity> teacher = teacherRepository.findByEmailAddrAndPassword(email, password);
                    if(!teacher.isPresent())
                    {
                        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("邮箱或密码错误");
                    }
                    if(!teacher.get().getVerified())
                    {
                        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("尚未激活");
                    }
                    return ResponseEntity.status(HttpStatus.OK).body("登录成功");

                }
                default:
                {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("错误用户类型");
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("数据库请求错误");
        }
    }
}
