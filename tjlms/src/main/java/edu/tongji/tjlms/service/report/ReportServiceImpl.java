package edu.tongji.tjlms.service.report;

import edu.tongji.tjlms.dto.ReportContentDto;
import edu.tongji.tjlms.dto.ReportInfoDto;
import edu.tongji.tjlms.dto.SubmitReportDto;
import edu.tongji.tjlms.model.QueryReportEntity;
import edu.tongji.tjlms.model.ReportEntity;
import edu.tongji.tjlms.model.ReportEntityPK;
import edu.tongji.tjlms.repository.QueryReportRepository;
import edu.tongji.tjlms.repository.ReportRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ReportServiceImpl implements ReportService{
    @Resource
    ReportRepository reportRepository;

    @Resource
    QueryReportRepository queryReportRepository;

    @Override
    public String saveReport(SubmitReportDto report) {
        ReportEntity reportEntity = new ReportEntity();
        reportEntity.setClassId(report.getClassId());
        reportEntity.setAim(report.getAim());
        reportEntity.setIsChecked(false);
        reportEntity.setResult(report.getResult());
        reportEntity.setMutable(true);
        reportEntity.setStep(report.getStep());
        reportEntity.setPrinciple(report.getPrinciple());
        reportEntity.setUpdateDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        reportRepository.save(reportEntity);
        return "暂存成功";
    }

    @Override
    public String submitReport(ReportEntityPK reportEntityPK) {
        ReportEntity report = reportRepository.findByStuIdAndLabId(reportEntityPK.getStuId(),reportEntityPK.getLabId());
        if(report != null)
        {
            report.setMutable(false);
            report.setUpdateDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            return "提交成功";
        }
        return "提交失败";

    }

    @Override
    public List<ReportInfoDto> getInfo(String id) {
        List<QueryReportEntity> reportList = queryReportRepository.findAllByStuId(id);
        List<ReportInfoDto> reportInfo = new ArrayList<>();
        for(QueryReportEntity report: reportList)
        {
            ReportInfoDto temp = new ReportInfoDto();
            temp.setLabName(report.getLabName());
            temp.setUpdateDate(report.getUpdateDate());
            temp.setMutable(report.getMutable());
            temp.setChecked(report.getChecked());
            reportInfo.add(temp);
        }
        return reportInfo;
    }

    @Override
    public List<ReportContentDto> getContent(ReportEntityPK reportEntityPK) {
        List<QueryReportEntity> reportList = queryReportRepository.findByStuIdAndLabId(reportEntityPK.getStuId(),
                reportEntityPK.getLabId());
        List<ReportContentDto> reportContent = new ArrayList<>();
        for(QueryReportEntity report: reportList)
        {
            ReportContentDto temp = new ReportContentDto();
            temp.setAim(report.getAim());
            temp.setPrinciple(report.getPrinciple());
            temp.setResult(report.getResult());
            temp.setStep(report.getStep());
            temp.setLabName(report.getLabName());
            reportContent.add(temp);
        }
        return reportContent;
    }
}
