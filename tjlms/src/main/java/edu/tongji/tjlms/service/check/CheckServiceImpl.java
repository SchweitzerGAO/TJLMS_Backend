package edu.tongji.tjlms.service.check;

import edu.tongji.tjlms.dto.PostCheckDto;
import edu.tongji.tjlms.model.CheckEntity;
import edu.tongji.tjlms.model.StuCheckEntity;
import edu.tongji.tjlms.repository.CheckRepository;
import edu.tongji.tjlms.repository.StuCheckRepository;
import edu.tongji.tjlms.repository.TakesRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class CheckServiceImpl implements CheckService{

    @Resource
    CheckRepository checkRepository;

    @Resource
    TakesRepository takesRepository;

    @Resource
    StuCheckRepository stuCheckRepository;
    @Override
    public String postCheck(PostCheckDto pcd) {
        CheckEntity check = new CheckEntity();
        check.setClassId(pcd.getClassId());
        check.setStartTime(pcd.getStart());
        check.setEndTime(pcd.getEnd());
        checkRepository.save(check);
        return "考勤发布成功";
    }

    @Override
    public List<CheckEntity> getAllCheckByStuId(String stuId) {
        List<CheckEntity> checks = checkRepository.findAllByClassId(takesRepository.findByStuId(stuId).getClassId());
        if(checks.isEmpty())
        {
            return null;
        }
        return checks;
    }

    @Override
    public String submitCheck(String stuId,Integer checkId) {
        StuCheckEntity stuCheck = new StuCheckEntity();
        stuCheck.setCheckId(checkId);
        stuCheck.setStuId(stuId);
        stuCheck.setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        stuCheckRepository.save(stuCheck);
        return "签到成功";
    }

    @Override
    public Double calculateAttendance(String stuId) {
        double ret = 0.;
        String classId = takesRepository.findByStuId(stuId).getClassId();
        List<CheckEntity> checks = checkRepository.findAllByClassId(classId);
        List<StuCheckEntity> attendances = stuCheckRepository.findAllByStuId(stuId);
        if(!checks.isEmpty())
        {
            ret = (double) attendances.size()/checks.size();
        }
        return ret;
    }
}
