package com.phone.phonemanage.controller;

import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.phone.phonemanage.entity.PhoneConfiscateRecord;
import com.phone.phonemanage.entity.SysSchedule;
import com.phone.phonemanage.mapper.SysScheduleMapper;
import com.phone.phonemanage.service.ConfiscateRecordService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/teacher")
public class TeacherController {
    @Resource
    private ConfiscateRecordService recordService;
    @Resource
    private SysScheduleMapper scheduleMapper;

    private final Long loginTeacherId = 1L;

    @GetMapping("/schedule")
    public String getSchedule(Model model) {
        LocalDate today = LocalDate.now();
        LocalDate twoDayAgo = today.minusDays(2);
        LambdaQueryWrapper<SysSchedule> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysSchedule::getTeacherId, loginTeacherId)
                .between(SysSchedule::getScheduleDate, twoDayAgo, today);
        List<SysSchedule> scheduleList = scheduleMapper.selectList(wrapper);
        model.addAttribute("scheduleList", scheduleList);
        return "teacher/schedule";
    }

    @GetMapping("/toAdd")
    public String toAdd(@RequestParam Long scheduleId,
                        @RequestParam Integer weekNum,
                        @RequestParam Integer sectionNum,
                        Model model) {
        model.addAttribute("scheduleId", scheduleId);
        model.addAttribute("weekNum", weekNum);
        model.addAttribute("sectionNum", sectionNum);
        return "teacher/toAdd";
    }

    @PostMapping("/addRecord")
    public String addRecord(PhoneConfiscateRecord record) {
        SysSchedule schedule = scheduleMapper.selectById(record.getScheduleId());
        if (schedule != null) {
            record.setTeachingClassId(schedule.getTeachingClassId());
        }
        record.setTeacherId(loginTeacherId);
        record.setConfiscateTime(LocalDateTime.now());
        record.setHandoverStatus(0);
        record.setCreateTime(LocalDateTime.now());
        recordService.save(record);
        return "redirect:/teacher/recordList";
    }

    @GetMapping("/recordList")
    public String recordList(Model model) {
        LambdaQueryWrapper<PhoneConfiscateRecord> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(PhoneConfiscateRecord::getTeacherId, loginTeacherId)
                .orderByDesc(PhoneConfiscateRecord::getConfiscateTime);
        List<PhoneConfiscateRecord> list = recordService.list(wrapper);
        model.addAttribute("recordList", list);
        return "teacher/recordList";
    }

    @GetMapping("/toEdit")
    public String toEdit(@RequestParam Long id, Model model) {
        PhoneConfiscateRecord record = recordService.getById(id);
        model.addAttribute("record", record);
        return "teacher/toEdit";
    }

    @PostMapping("/updateRecord")
    public String updateRecord(PhoneConfiscateRecord record) {
        record.setUpdateTime(LocalDateTime.now());
        recordService.updateById(record);
        return "redirect:/teacher/recordList";
    }

    @PostMapping("/deleteRecord")
    public String deleteRecord(@RequestParam Long id) {
        recordService.removeById(id);
        return "redirect:/teacher/recordList";
    }
}
