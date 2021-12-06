package edu.tongji.tjlms.service.feedback;

import edu.tongji.tjlms.dto.FeedBackDto;
import edu.tongji.tjlms.model.FeedbackEntity;
import edu.tongji.tjlms.model.ReplyEntity;
import org.springframework.data.domain.Page;

import java.util.List;

public interface FeedbackService {
    String feedback(FeedBackDto fbd);
    String reply(FeedBackDto fbd);
    List<FeedbackEntity> getFromFeedback(String from);
    List<FeedbackEntity> getToFeedback(String to);
    List<ReplyEntity> getFromReply(String from);
    List<ReplyEntity> getToReply(String to);
    Page<FeedbackEntity> getFromFeedbackPaged(String from, Integer pageNum, Integer pageSize);
    Page<FeedbackEntity> getToFeedbackPaged(String to, Integer pageNum, Integer pageSize);
    Page<ReplyEntity> getFromReplyPaged(String from, Integer pageNum, Integer pageSize);
    Page<ReplyEntity> getToReplyPaged(String to, Integer pageNum, Integer pageSize);
}
