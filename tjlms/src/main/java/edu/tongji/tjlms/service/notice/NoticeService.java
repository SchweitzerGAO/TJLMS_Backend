package edu.tongji.tjlms.service.notice;

import edu.tongji.tjlms.dto.GetNoticeDto;
import edu.tongji.tjlms.dto.PostNoticeDto;
import edu.tongji.tjlms.model.NoticeEntity;

import java.util.List;

public interface NoticeService {
    List<GetNoticeDto> getAllTitles();
    NoticeEntity getNoticeById(Integer id);
    List<NoticeEntity> getNoticeByReleaser(String teacherId);
    String postNotice(PostNoticeDto pnd);
    String deleteNotice(Integer id);
}
