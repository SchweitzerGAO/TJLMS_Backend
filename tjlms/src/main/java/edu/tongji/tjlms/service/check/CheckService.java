package edu.tongji.tjlms.service.check;

import edu.tongji.tjlms.dto.PostCheckDto;
import edu.tongji.tjlms.model.CheckEntity;

import java.util.List;

public interface CheckService {
    String postCheck(PostCheckDto pcd);
    List<CheckEntity> getAllCheckByStuId(String stuId);

    String submitCheck(String stuId,Integer checkId);

    Double calculateAttendance(String stuId);
}
