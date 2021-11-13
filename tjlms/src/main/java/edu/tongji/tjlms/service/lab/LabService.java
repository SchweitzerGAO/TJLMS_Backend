package edu.tongji.tjlms.service.lab;

import edu.tongji.tjlms.dto.LabDto;
import edu.tongji.tjlms.model.LabEntity;

import java.util.List;

public interface LabService {
    List<LabEntity> getAll();
    String releaseLab(LabDto lab);
}
