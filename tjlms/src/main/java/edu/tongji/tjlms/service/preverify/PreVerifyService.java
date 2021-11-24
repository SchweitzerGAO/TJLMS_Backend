package edu.tongji.tjlms.service.preverify;

import edu.tongji.tjlms.dto.PreVerifyDto;

public interface PreVerifyService {
    boolean exists(PreVerifyDto pvd);
}
