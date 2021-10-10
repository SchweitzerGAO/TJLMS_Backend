package edu.tongji.tjlms.controller;

import edu.tongji.tjlms.domain.Admin;
import edu.tongji.tjlms.domain.Login;
import edu.tongji.tjlms.domain.Student;
import edu.tongji.tjlms.domain.Teacher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;
import edu.tongji.tjlms.utils.EncryptSha256Util;

/**
 * @author Charles Gao
 * @description login webAPI
 * @date 2021/9/29
 */

@RestController
@CrossOrigin
@RequestMapping("/api")
public class LoginController {
    @Resource
    private JdbcTemplate jdbcTemplate;

    // Admin row mapper
    static class AdminRowMapper implements RowMapper<Admin> {
        /**
         * map the admins' data
         * @param rs result set
         * @param rowNum number of rows
         * @return the mapped data
         * @throws SQLException exception
         */
        @Override
        public Admin mapRow(ResultSet rs, int rowNum) throws SQLException
        {
            Admin admin = new Admin();
            admin.setId(rs.getString("id"));
            admin.setName(rs.getString("name"));
            admin.setTelNum(rs.getString("tel_num"));
            admin.setEmailAddress(rs.getString("email_addr"));
            return admin;
        }

    }

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
     * login
     * @param l login info
     * @return the user's necessary information
     */
    @PostMapping("/login")
    @ResponseBody
    public ResponseEntity login(@RequestBody Login l)
    {

        try
        {
            int userType = l.getUserType();
            String email = l.getEmailAddress();
            String password = EncryptSha256Util.getSha256Str(l.getPassword());
            String[] userTypes = {"ADMIN","STUDENT","TEACHER"};
            String sql = "SELECT * FROM " +userTypes[userType]+ " WHERE EMAIL_ADDR=? AND PASSWORD=?";

            switch(userType)
            {
                // Admin
                case 0:
                {
                    // check the email and password
                    List<Admin> adminList = jdbcTemplate.query(
                            sql,
                            new Object[]{email,password},
                            new int[]{Types.VARCHAR,Types.VARCHAR},
                            new AdminRowMapper()
                    );
                    if(adminList.isEmpty())
                    {
                        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("邮箱或密码错误");
                    }
                    return ResponseEntity.status(HttpStatus.OK).body(adminList.get(0));
                }

                // Student
                case 1:
                {
                    // check the email and password
                    List<Student> studentList = jdbcTemplate.query(
                            sql,
                            new Object[]{email,password},
                            new int[]{Types.VARCHAR,Types.VARCHAR},
                            new StudentRowMapper()
                    );
                    if(studentList.isEmpty())
                    {
                        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("邮箱或密码错误");
                    }
                    // check the verification
                    Student student = studentList.get(0);
                    if(!student.isVerified())
                    {
                        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("账户尚未激活");
                    }
                    return ResponseEntity.status(HttpStatus.OK).body(student);
                }

                // Teacher
                case 2:
                {
                    // check the email and password
                    List<Teacher> TeacherList = jdbcTemplate.query(
                            sql,
                            new Object[]{email,password},
                            new int[]{Types.VARCHAR,Types.VARCHAR},
                            new TeacherRowMapper()
                    );
                    if(TeacherList.isEmpty())
                    {
                        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("邮箱或密码错误");
                    }
                    // check the verification
                    Teacher teacher = TeacherList.get(0);
                    if(!teacher.isVerified())
                    {
                        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("账户尚未激活");
                    }
                    return ResponseEntity.status(HttpStatus.OK).body(teacher);
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
