package edu.tongji.tjlms.service.lab;

import edu.tongji.tjlms.model.LabEntity;
import edu.tongji.tjlms.repository.LabRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class LabServiceImpl implements LabService{
    @Resource
    LabRepository labRepository;
    @Override
    public List<LabEntity> getAllLabs() {
        return labRepository.findAll();
    }
}
