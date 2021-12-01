package edu.tongji.tjlms.controller;

import edu.tongji.tjlms.dto.FeedBackDto;
import edu.tongji.tjlms.service.feedback.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class FeedbackController {
    @Autowired
    FeedbackService feedbackService;

    @PostMapping("/post/feedback")
    @ResponseBody
    public ResponseEntity<String> feedback(@RequestBody FeedBackDto fbd)
    {
        try
        {
            return ResponseEntity.status(HttpStatus.OK).body(feedbackService.feedback(fbd));
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("数据库请求错误");
        }
    }

    @PostMapping("/post/reply")
    @ResponseBody
    public ResponseEntity<String> reply(@RequestBody FeedBackDto fbd)
    {
        try
        {
            return ResponseEntity.status(HttpStatus.OK).body(feedbackService.reply(fbd));
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("数据库请求错误");
        }
    }

    @GetMapping("/get/from/feedback")
    @ResponseBody
    public ResponseEntity<?> getFromFeedback(String from)
    {
        try
        {
            List<?> ret = feedbackService.getFromFeedback(from);
            if (ret == null)
            {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("暂无发出的反馈");
            }
            return ResponseEntity.status(HttpStatus.OK).body(ret);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("数据库请求错误");
        }
    }

    @GetMapping("/get/to/feedback")
    @ResponseBody
    public ResponseEntity<?> getToFeedback(String to)
    {
        try
        {
            List<?> ret = feedbackService.getToFeedback(to);
            if (ret == null)
            {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("暂无收到的反馈");
            }
            return ResponseEntity.status(HttpStatus.OK).body(ret);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("数据库请求错误");
        }
    }

    @GetMapping("/get/from/reply")
    @ResponseBody
    public ResponseEntity<?> getFromReply(String from)
    {
        try
        {
            List<?> ret = feedbackService.getFromReply(from);
            if (ret == null)
            {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("暂无发出的回复");
            }
            return ResponseEntity.status(HttpStatus.OK).body(ret);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("数据库请求错误");
        }
    }

    @GetMapping("/get/to/reply")
    @ResponseBody
    public ResponseEntity<?> getToReply(String to)
    {
        try
        {
            List<?> ret = feedbackService.getToReply(to);
            if (ret == null)
            {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("暂无收到的回复");
            }
            return ResponseEntity.status(HttpStatus.OK).body(ret);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("数据库请求错误");
        }
    }

}
