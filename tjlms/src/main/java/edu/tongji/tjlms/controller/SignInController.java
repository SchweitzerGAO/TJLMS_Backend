package edu.tongji.tjlms.controller;
import edu.tongji.tjlms.domain.EmailInfo;
import edu.tongji.tjlms.domain.SignIn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import edu.tongji.tjlms.domain.Student;
import edu.tongji.tjlms.domain.Teacher;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.annotation.Resource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Random;
import java.util.List;

import edu.tongji.tjlms.utils.EncryptSha256Util;

/**
 * @author Charles Gao
 * @date 2021/10/5
 * @description the APIs for email and signing in
 */
@RestController
@RequestMapping("/api")
@CrossOrigin
@Service
public class SignInController {
    @Autowired
    private JavaMailSender jms;
    // the sender value is in application.properties
    @Value("${spring.mail.username}")
    private String sender;

    @Resource
    private JdbcTemplate jdbcTemplate;

    private String verificationCode = "";
    // role "ADMIN" is always verified
    private final String[] userTypes = {"ADMIN","STUDENT","TEACHER"};
    // Student row mapper
    static class StudentRowMapper implements RowMapper<Student>{
        /**
         * map the students' data
         * @param rs result set
         * @param rowNum number of rows
         * @return the mapped data
         * @throws SQLException exception
         */
        @Override
        public Student mapRow(ResultSet rs, int rowNum) throws SQLException
        {
            Student student = new Student();
            student.setId(rs.getString("id"));
            student.setName(rs.getString("name"));
            student.setVerified(rs.getBoolean("verified"));
            student.setClassId(rs.getString("class_id"));
            student.setEmailAddress(rs.getString("email_addr"));
            return student;
        }
    }

    // Teacher row mapper
    static class TeacherRowMapper implements RowMapper<Teacher>{
        /**
         * map the teachers' data
         * @param rs result set
         * @param rowNum number of rows
         * @return the mapped data
         * @throws SQLException exception
         */
        @Override
        public Teacher mapRow(ResultSet rs, int rowNum) throws SQLException
        {
            Teacher teacher = new Teacher();
            teacher.setId(rs.getString("id"));
            teacher.setName(rs.getString("name"));
            teacher.setVerified(rs.getBoolean("verified"));
            teacher.setAssist(rs.getBoolean("is_assist"));
            teacher.setTelNum(rs.getString("tel_num"));
            teacher.setResp(rs.getBoolean("is_resp"));
            teacher.setEmailAddress(rs.getString("email_addr"));
            return teacher;

        }
    }

    /**
     * send email
     * @param ei the data for sending the email
     * @return the response code with information
     */
    @PostMapping("/sendEmail")
    @ResponseBody
    public ResponseEntity<String> sendEmail(@RequestBody EmailInfo ei)
    {
        // generate verification code
        StringBuilder code = new StringBuilder();
        Random r = new Random();
        for(int i = 0;i<6;i++)
        {
            code.append(r.nextInt(10));
        }
        verificationCode = code.toString();

        // query the information from the DBMS
        String id = ei.getId();
        String name = "";
        int userType = ei.getUserType();
        String sql = "SELECT * FROM "+userTypes[userType]+" WHERE ID=?";
        try
        {
            switch (userType)
            {
                // student
                case 1:
                {
                    List<Student> studentList = jdbcTemplate.query(
                            sql,
                            new Object[]{id},
                            new int[]{Types.VARCHAR},
                            new StudentRowMapper()
                            );
                    if(studentList.isEmpty())
                    {
                        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("学工号错误");
                    }
                    if(studentList.get(0).isVerified())
                    {
                        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("不能重复激活");
                    }
                    name = studentList.get(0).getName();
                    break;
                }
                // teacher
                case 2:
                {
                    List<Teacher> teacherList = jdbcTemplate.query(
                            sql,
                            new Object[]{id},
                            new int[]{Types.VARCHAR},
                            new TeacherRowMapper()
                    );
                    if(teacherList.isEmpty())
                    {
                        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("学工号错误");
                    }
                    if(teacherList.get(0).isVerified())
                    {
                        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("不能重复激活");
                    }
                    name = teacherList.get(0).getName();
                    break;
                }
            }
            // send the email
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(sender);  // the sender
            message.setTo(ei.getEmailAddress());  // the receiver
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
     * @param si the data for signing in
     * @return response code with information
     */
    @PostMapping("/signIn")
    @ResponseBody
    public ResponseEntity<String> signIn(@RequestBody SignIn si)
    {
        try
        {
            // compare the verification code
            if(!si.getVerificationCode().equals(this.verificationCode))
            {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("验证码错误");
            }
            verificationCode = "";
            int userType = si.getUserType();
            String id = si.getId();
            String email = si.getEmailAddress();
            // encrypt the password
            String password = EncryptSha256Util.getSha256Str(si.getPassword());
            // update the DBMS
            String sql = "UPDATE "+userTypes[userType]+" SET EMAIL_ADDR=?,PASSWORD=?,VERIFIED=1 WHERE ID=?";
            int res = jdbcTemplate.update(
                    sql,
                    new Object[]{email,password,id},
                    new int[]{Types.VARCHAR,Types.VARCHAR,Types.VARCHAR}
            );
            if(res == 0)
            {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("注册失败");
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

