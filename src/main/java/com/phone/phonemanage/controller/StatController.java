package com.phone.phonemanage.controller;

import com.phone.phonemanage.entity.StatReport;
import com.phone.phonemanage.service.StatReportService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/stat")
public class StatController {

    @Resource
    private StatReportService statReportService;

    @GetMapping("")
    public String statPage(Model model) {
        // 按班级统计
        List<StatReport> byClass = statReportService.statByClass();
        model.addAttribute("statByClass", byClass);

        // 按教师统计
        List<StatReport> byTeacher = statReportService.statByTeacher();
        model.addAttribute("statByTeacher", byTeacher);

        // 按课程统计
        List<StatReport> byCourse = statReportService.statByCourse();
        model.addAttribute("statByCourse", byCourse);

        // 按周次统计
        List<StatReport> byWeek = statReportService.statByWeek();
        model.addAttribute("statByWeek", byWeek);

        // 按节次统计
        List<StatReport> bySection = statReportService.statBySection();
        model.addAttribute("statBySection", bySection);

        // 按移交状态统计
        List<StatReport> byHandover = statReportService.statByHandoverStatus();
        model.addAttribute("statByHandover", byHandover);

        // 时间范围统计
        List<StatReport> byTimeRange = statReportService.statByTimeRange();
        model.addAttribute("statByTimeRange", byTimeRange);

        // 总数
        Integer totalCount = statReportService.getTotalCount();
        model.addAttribute("totalCount", totalCount);

        return "stat/report";
    }
}
