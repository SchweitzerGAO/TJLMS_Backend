package edu.tongji.tjlms.controller;

import edu.tongji.tjlms.domain.Test;
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

/**
 * @author Charles Gao
 * @description test webAPIs
 * @date 2021/9/12
 */
@RestController
@RequestMapping("/api")
public class TestController {
    @Resource
    private JdbcTemplate jdbcTemplate;

    static class TestRowMapper implements RowMapper<Test> {
        // table to json
        @Override
        public Test mapRow(ResultSet rs, int rowNum) throws SQLException {
            Test test = new Test();
            test.setId(rs.getLong("id"));
            test.setName(rs.getString("name"));
            test.setPassword(rs.getString("password"));
            return test;
        }
    }
    // get all
    @GetMapping("/allTest")
    @ResponseBody
    public ResponseEntity getAll()
    {
        try
        {
            List<Test> test = jdbcTemplate.query("SELECT * FROM TEST",new TestRowMapper());
            if(!test.isEmpty())
            {
                return ResponseEntity.status(HttpStatus.OK).body(test);
            }
            else
            {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("未找到信息");
            }
        }

        catch(Exception e)
        {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("数据库请求错误");
        }

    }

    // get by id
    @GetMapping("/getTest/{id}")
    @ResponseBody
    public ResponseEntity getById(@PathVariable("id") Long id)
    {
        try
        {
            final String sql = "SELECT * FROM TEST WHERE ID=?";
            List<Test> list = jdbcTemplate.query(
                    sql,
                    new Object[]{id},
                    new int[]{Types.BIGINT},
                    new TestRowMapper());
            if(!list.isEmpty())
            {
                return ResponseEntity.status(HttpStatus.OK).body(list.get(0));
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("未找到信息");
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("数据库请求错误");
        }

    }

    // add a new test object
    @PostMapping("/newTest")
    @ResponseBody
    public ResponseEntity<String> newTest(@RequestBody Test t)
    {
        try
        {
            final String sql = "INSERT INTO TEST VALUES(?,?,?)";
            int res = jdbcTemplate.update(
                    sql,
                    new Object[]{t.getId(),t.getName(),t.getPassword()},
                    new int[]{Types.BIGINT,Types.VARCHAR,Types.VARCHAR}
            );
            if(res == 0)
            {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("添加失败");
            }
            return ResponseEntity.status(HttpStatus.OK).body("添加成功");

        }
        catch (Exception e)
        {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("数据库请求错误");
        }
    }

    @PostMapping("/newTestList")
    @ResponseBody
    public ResponseEntity newTestList(@RequestBody List<Test> l)
    {
        try
        {
            for(Test t:l)
            {
                final String sql = "INSERT INTO TEST VALUES(?,?,?)";
                jdbcTemplate.update(
                        sql,
                        new Object[]{t.getId(),t.getName(),t.getPassword()},
                        new int[]{Types.BIGINT,Types.VARCHAR,Types.VARCHAR}
                );
            }
            return ResponseEntity.status(HttpStatus.OK).body("批量添加成功");
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("数据库请求错误");
        }
    }

    @GetMapping("/deleteTest/{id}")
    @ResponseBody
    public ResponseEntity deleteTestById(@PathVariable("id") Long id)
    {
        try
        {
            final String sql = "DELETE FROM TEST WHERE ID=?";
            int res = jdbcTemplate.update(
                    sql,
                    new Object[]{id},
                    new int[]{Types.BIGINT}
            );
            if(res == 0)
            {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("删除失败");
            }
            return ResponseEntity.status(HttpStatus.OK).body("删除成功");
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("数据库请求错误");
        }
    }



}
