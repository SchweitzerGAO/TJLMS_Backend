package edu.tongji.tjlms.service.feedback;

import edu.tongji.tjlms.dto.FeedBackDto;
import edu.tongji.tjlms.model.FeedbackEntity;
import edu.tongji.tjlms.model.ReplyEntity;
import edu.tongji.tjlms.repository.FeedbackRepository;
import edu.tongji.tjlms.repository.ReplyRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class FeedbackServiceImpl implements FeedbackService {
    @Resource
    FeedbackRepository feedbackRepository;

    @Resource
    ReplyRepository replyRepository;
    @Override
    public String feedback(FeedBackDto fbd) {
        FeedbackEntity feedback = new FeedbackEntity();
        feedback.setFrom(fbd.getFrom());
        feedback.setContent(fbd.getContent());
        feedback.setTitle(fbd.getTitle());
        feedback.setTo(fbd.getTo());
        feedback.setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        feedbackRepository.save(feedback);
        return "反馈提交成功";
    }

    @Override
    public String reply(FeedBackDto fbd) {
        ReplyEntity reply = new ReplyEntity();
        reply.setFrom(fbd.getFrom());
        reply.setContent(fbd.getContent());
        reply.setTitle(fbd.getTitle());
        reply.setTo(fbd.getTo());
        reply.setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        replyRepository.save(reply);
        return null;
    }

    @Override
    public List<FeedbackEntity> getFromFeedback(String from) {
        return feedbackRepository.findAllByFrom(from);
    }

    @Override
    public List<FeedbackEntity> getToFeedback(String to) {
        return feedbackRepository.findAllByTo(to);
    }

    @Override
    public List<ReplyEntity> getFromReply(String from) {
        return replyRepository.findAllByFrom(from);
    }

    @Override
    public List<ReplyEntity> getToReply(String to) {
        return replyRepository.findAllByTo(to);
    }
}
