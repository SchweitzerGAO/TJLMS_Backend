package edu.tongji.tjlms.service.lab;

import edu.tongji.tjlms.dto.LabDto;
import edu.tongji.tjlms.dto.ScheduleDto;
import edu.tongji.tjlms.model.LabEntity;
import edu.tongji.tjlms.model.TeacherEntity;
import edu.tongji.tjlms.repository.LabRepository;
import edu.tongji.tjlms.repository.TeacherRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class LabServiceImpl implements LabService{
    @Resource
    LabRepository labRepository;
    @Resource
    TeacherRepository teacherRepository;
    @Override
    public List<LabEntity> getAll() {
        return labRepository.findAll();
    }

    @Override
    public String releaseLab(LabDto lab) {
        LabEntity labEntity = new LabEntity();
        labEntity.setName(lab.getName());
        labEntity.setDeadline(lab.getDeadline());
        labEntity.setReleaseTeacher(lab.getReleaseTeacher());
        labEntity.setReleaseDate(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        labRepository.save(labEntity);
        return "发布成功";
    }

    @Override
    public List<ScheduleDto> getSchedule() {
        List<LabEntity> labs = getAll();
        List<ScheduleDto> ret = new ArrayList<>();
        for(LabEntity lab:labs)
        {
            ret.add(new ScheduleDto(lab.getId(),lab.getName(),lab.getDeadline()));
        }
        return ret;
    }


}
