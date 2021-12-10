package edu.tongji.tjlms.controller;

import edu.tongji.tjlms.dto.GetNoticeDto;
import edu.tongji.tjlms.dto.PostNoticeDto;
import edu.tongji.tjlms.model.NoticeEntity;
import edu.tongji.tjlms.service.notice.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@ResponseBody
public class NoticeController {
    @Autowired
    NoticeService noticeService;

    @GetMapping("/get/titles")
    public ResponseEntity<?> getAllTitles()
    {
        try
        {
            List<GetNoticeDto> titles = noticeService.getAllTitles();
            if(!titles.isEmpty())
            {
                return ResponseEntity.status(HttpStatus.OK).body(titles);
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("暂无公告");
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("数据库请求错误");
        }
    }

    @GetMapping("/get/notice/id/{id}")
    public ResponseEntity<?> getNoticeById(@PathVariable("id") Integer id)
    {
        try
        {
            NoticeEntity notice = noticeService.getNoticeById(id);
            if(notice != null)
            {
                return ResponseEntity.status(HttpStatus.OK).body(notice);
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("公告不存在");
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("公告不存在");
        }
    }

    @GetMapping("/get/notice/releaser/{releaser}")
    public ResponseEntity<?> getNoticeByReleaser(@PathVariable("releaser") String releaser)
    {
        try
        {
            List<NoticeEntity> notices = noticeService.getNoticeByReleaser(releaser);
            if(!notices.isEmpty())
            {
                return ResponseEntity.status(HttpStatus.OK).body(notices);
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("暂无公告");
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("数据库请求错误");
        }
    }

    @PostMapping("/post/notice")
    public ResponseEntity<String> postNotice(@RequestBody PostNoticeDto pnd)
    {
        try
        {
            return ResponseEntity.status(HttpStatus.OK).body(noticeService.postNotice(pnd));
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("数据库请求错误");
        }
    }

    @PostMapping("/delete/notice/{id}")
    public ResponseEntity<String> deleteNotice(@PathVariable("id") Integer id)
    {
        try
        {
            return ResponseEntity.status(HttpStatus.OK).body(noticeService.deleteNotice(id));
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("数据库请求错误");
        }
    }
}
