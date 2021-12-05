package edu.tongji.tjlms.service.notice;

import edu.tongji.tjlms.dto.GetNoticeDto;
import edu.tongji.tjlms.dto.PostNoticeDto;
import edu.tongji.tjlms.model.NoticeEntity;
import edu.tongji.tjlms.repository.NoticeRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class NoticeServiceImpl implements NoticeService{

    @Resource
    NoticeRepository noticeRepository;
    @Override
    public List<GetNoticeDto> getAllTitles() {
        List<NoticeEntity> list = noticeRepository.findAll();
        List<GetNoticeDto> ret = new ArrayList<>();
        for(NoticeEntity notice: list)
        {
            ret.add(new GetNoticeDto(notice.getId(),notice.getTitle()));
        }
        return ret;
    }

    @Override
    public NoticeEntity getNoticeById(Integer id) {
        return noticeRepository.getById(id);
    }

    @Override
    public List<NoticeEntity> getNoticeByReleaser(String teacherId) {
        return noticeRepository.findAllByReleaser(teacherId);
    }

    @Override
    public String postNotice(PostNoticeDto pnd) {
        NoticeEntity notice = new NoticeEntity();
        notice.setContent(pnd.getContent());
        notice.setReleaser(pnd.getReleaser());
        notice.setTitle(pnd.getTitle());
        notice.setReleaseTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        noticeRepository.save(notice);
        return "通知发布成功";
    }

    @Override
    public String deleteNotice(Integer id) {
        noticeRepository.deleteById(id);
        return "删除成功";
    }
}