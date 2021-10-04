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

@RestController
@RequestMapping("/api")
@Service
public class SignInController {
    @Autowired
    private JavaMailSender jms;

    @Value("${spring.mail.username}")
    private String sender;

    @Resource
    private JdbcTemplate jdbcTemplate;

    private String verificationCode = "";
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
    
    @PostMapping("/sendEmail")
    @ResponseBody
    public ResponseEntity<String> sendEmail(@RequestBody EmailInfo ei)
    {
        StringBuilder code = new StringBuilder();
        Random r = new Random();
        for(int i = 0;i<6;i++)
        {
            code.append(r.nextInt(10));
        }
        verificationCode = code.toString();
        String[] userTypes = {"ADMIN","STUDENT","TEACHER"};
        String id = ei.getId();
        String name = "";
        int userType = ei.getUserType();
        String sql = "SELECT * FROM "+userTypes[userType]+" WHERE ID=?";
        try
        {
            switch (userType)
            {
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
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(sender);
            message.setTo(ei.getEmailAddress());
            message.setSubject("TJLMS邮箱验证");
            final String text = "您的验证码是："+ verificationCode+"\n\nTJLMS";
            String head = name+((userType == 1) ? "同学：\n" :"老师：\n")+"您好！\n";
            String mainMsg = head+text;
            message.setText(mainMsg);
            jms.send(message);
            return ResponseEntity.status(HttpStatus.OK).body("发送成功");
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("发送失败");
        }
    }

}

