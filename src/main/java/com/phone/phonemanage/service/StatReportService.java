package com.phone.phonemanage.service;

import com.phone.phonemanage.entity.StatReport;
import com.phone.phonemanage.mapper.StatReportMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatReportService {

    @Resource
    private StatReportMapper statReportMapper;

    public List<StatReport> statByClass() {
        return statReportMapper.statByClass();
    }

    public List<StatReport> statByTeacher() {
        return statReportMapper.statByTeacher();
    }

    public List<StatReport> statByCourse() {
        return statReportMapper.statByCourse();
    }

    public List<StatReport> statByWeek() {
        return statReportMapper.statByWeek();
    }

    public List<StatReport> statBySection() {
        return statReportMapper.statBySection();
    }

    public List<StatReport> statByHandoverStatus() {
        return statReportMapper.statByHandoverStatus();
    }

    public Integer getTotalCount() {
        return statReportMapper.getTotalCount();
    }

    public List<StatReport> statByTimeRange() {
        return statReportMapper.statByTimeRange();
    }
}
