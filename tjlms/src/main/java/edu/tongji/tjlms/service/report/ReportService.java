package edu.tongji.tjlms.service.report;

import edu.tongji.tjlms.dto.ReportDto;
import edu.tongji.tjlms.model.ReportEntityPK;

public interface ReportService {
    String saveReport(ReportDto report);
    String submitReport(ReportEntityPK reportEntityPK);
}
