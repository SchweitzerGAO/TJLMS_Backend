package edu.tongji.tjlms.controller;

import edu.tongji.tjlms.dto.FeedBackDto;
import edu.tongji.tjlms.model.FeedbackEntity;
import edu.tongji.tjlms.model.ReplyEntity;
import edu.tongji.tjlms.service.feedback.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@ResponseBody
public class FeedbackController {
    @Autowired
    FeedbackService feedbackService;

    Integer countFromFeedback;
    Integer countToFeedback;
    Integer countFromReply;
    Integer countToReply;
    @PostMapping("/post/feedback")
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

    @GetMapping("/get/from/feedback/{from}")
    public ResponseEntity<?> getFromFeedback(@PathVariable("from") String from)
    {
        try
        {
            List<?> ret = feedbackService.getFromFeedback(from);
            if (ret.isEmpty())
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

    @GetMapping("/get/to/feedback/{to}")
    public ResponseEntity<?> getToFeedback(@PathVariable("to") String to)
    {
        try
        {
            List<?> ret = feedbackService.getToFeedback(to);
            if (ret.isEmpty())
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

    @GetMapping("/get/from/reply/{from}")
    public ResponseEntity<?> getFromReply(@PathVariable("from") String from)
    {
        try
        {
            List<?> ret = feedbackService.getFromReply(from);
            if (ret.isEmpty())
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

    @GetMapping("/get/to/reply/{to}")
    public ResponseEntity<?> getToReply(@PathVariable("to") String to)
    {
        try
        {
            List<?> ret = feedbackService.getToReply(to);
            if (ret.isEmpty())
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

    @GetMapping("/get/from/feedback/page/{from}")
    public ResponseEntity<?> getFromFeedbackPaged(@PathVariable("from") String from,
                                                  @RequestParam(defaultValue = "1") Integer pageNum,
                                                  @RequestParam(defaultValue = "20") Integer pageSize)
    {
        try
        {
            if(pageNum == null || pageNum == 0)
            {
                pageNum = 1;
            }
            if(countFromFeedback != null && pageNum >= countFromFeedback)
            {
                pageNum = countFromFeedback;
            }
            Page<FeedbackEntity> page = feedbackService.getFromFeedbackPaged(from,pageNum,pageSize);
            if(page.isEmpty())
            {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("暂无发出的反馈");
            }
            countFromFeedback = page.getTotalPages();
            Map<String, Object> map = new HashMap<>();
            map.put("data",page);
            map.put("pageNum",countFromFeedback);
            return ResponseEntity.status(HttpStatus.OK).body(map);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("数据库请求错误");
        }
    }

    @GetMapping("/get/to/feedback/page/{to}")
    public ResponseEntity<?> getToFeedbackPaged(@PathVariable("to") String to,
                                                  @RequestParam(defaultValue = "1") Integer pageNum,
                                                  @RequestParam(defaultValue = "20") Integer pageSize)
    {
        try
        {
            if(pageNum == null || pageNum == 0)
            {
                pageNum = 1;
            }
            if(countToFeedback != null && pageNum >= countToFeedback)
            {
                pageNum = countToFeedback;
            }
            Page<FeedbackEntity> page = feedbackService.getFromFeedbackPaged(to,pageNum,pageSize);
            if(page.isEmpty())
            {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("暂无收到的反馈");
            }
            countToFeedback = page.getTotalPages();
            Map<String, Object> map = new HashMap<>();
            map.put("data",page);
            map.put("pageNum",countFromFeedback);
            return ResponseEntity.status(HttpStatus.OK).body(map);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("数据库请求错误");
        }
    }

    @GetMapping("/get/from/reply/page/{from}")
    public ResponseEntity<?> getFromReplyPaged(@PathVariable("from") String from,
                                                  @RequestParam(defaultValue = "1") Integer pageNum,
                                                  @RequestParam(defaultValue = "20") Integer pageSize)
    {
        try
        {
            if(pageNum == null || pageNum == 0)
            {
                pageNum = 1;
            }
            if(countFromFeedback != null && pageNum >= countFromFeedback)
            {
                pageNum = countFromFeedback;
            }
            Page<ReplyEntity> page = feedbackService.getFromReplyPaged(from,pageNum,pageSize);
            if(page.isEmpty())
            {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("暂无发出的回复");
            }
            countFromReply = page.getTotalPages();
            Map<String, Object> map = new HashMap<>();
            map.put("data",page);
            map.put("pageNum",countFromFeedback);
            return ResponseEntity.status(HttpStatus.OK).body(map);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("数据库请求错误");
        }
    }

    @GetMapping("/get/to/reply/page/{to}")
    public ResponseEntity<?> getToReplyPaged(@PathVariable("to") String to,
                                               @RequestParam(defaultValue = "1") Integer pageNum,
                                               @RequestParam(defaultValue = "20") Integer pageSize)
    {
        try
        {
            if(pageNum == null || pageNum == 0)
            {
                pageNum = 1;
            }
            if(countFromFeedback != null && pageNum >= countFromFeedback)
            {
                pageNum = countFromFeedback;
            }
            Page<ReplyEntity> page = feedbackService.getToReplyPaged(to,pageNum,pageSize);
            if(page.isEmpty())
            {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("暂无收到的回复");
            }
            countToReply = page.getTotalPages();
            Map<String, Object> map = new HashMap<>();
            map.put("data",page);
            map.put("pageNum",countFromFeedback);
            return ResponseEntity.status(HttpStatus.OK).body(map);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("数据库请求错误");
        }
    }

}
