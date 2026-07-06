package com.phone.phonemanage.controller;

import com.phone.phonemanage.entity.PhoneConfiscateRecord;
import com.phone.phonemanage.entity.SysSchedule;
import com.phone.phonemanage.service.ConfiscateRecordService;
import com.phone.phonemanage.mapper.SysScheduleMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/data")
public class DataInitController {

    private final ConfiscateRecordService recordService;
    private final SysScheduleMapper scheduleMapper;

    public DataInitController(ConfiscateRecordService recordService, SysScheduleMapper scheduleMapper) {
        this.recordService = recordService;
        this.scheduleMapper = scheduleMapper;
    }

    @GetMapping("/init")
    public String initData() {
        // 先插入课表数据
        List<SysSchedule> schedules = createSchedules();
        int scheduleCount = 0;
        for (SysSchedule s : schedules) {
            try {
                scheduleMapper.insert(s);
                scheduleCount++;
            } catch (Exception e) {
                // 忽略重复数据
            }
        }

        // 再插入没收记录
        List<PhoneConfiscateRecord> records = createRecords();
        int recordCount = 0;
        for (PhoneConfiscateRecord r : records) {
            try {
                recordService.save(r);
                recordCount++;
            } catch (Exception e) {
                // 忽略重复数据
            }
        }

        return "数据导入成功！课表: " + scheduleCount + " 条，没收记录: " + recordCount + " 条";
    }

    private List<SysSchedule> createSchedules() {
        List<SysSchedule> list = new ArrayList<>();

        // 第15周
        list.add(createSchedule(1L, 1L, 1L, 15, 1, LocalDate.of(2026, 6, 29), "08:00", "09:40"));
        list.add(createSchedule(1L, 1L, 1L, 15, 2, LocalDate.of(2026, 6, 29), "14:00", "15:40"));
        list.add(createSchedule(2L, 2L, 2L, 15, 1, LocalDate.of(2026, 6, 29), "08:00", "09:40"));
        list.add(createSchedule(2L, 2L, 2L, 15, 2, LocalDate.of(2026, 6, 30), "14:00", "15:40"));
        list.add(createSchedule(3L, 3L, 4L, 15, 1, LocalDate.of(2026, 6, 30), "08:00", "09:40"));
        list.add(createSchedule(3L, 3L, 4L, 15, 2, LocalDate.of(2026, 7, 1), "14:00", "15:40"));
        list.add(createSchedule(4L, 4L, 5L, 15, 1, LocalDate.of(2026, 7, 1), "08:00", "09:40"));
        list.add(createSchedule(4L, 4L, 5L, 15, 3, LocalDate.of(2026, 7, 1), "19:00", "20:40"));
        list.add(createSchedule(5L, 5L, 3L, 15, 1, LocalDate.of(2026, 7, 2), "08:00", "09:40"));
        list.add(createSchedule(5L, 5L, 3L, 15, 2, LocalDate.of(2026, 7, 3), "14:00", "15:40"));
        list.add(createSchedule(1L, 1L, 6L, 15, 1, LocalDate.of(2026, 7, 3), "08:00", "09:40"));
        list.add(createSchedule(2L, 2L, 6L, 15, 1, LocalDate.of(2026, 7, 3), "08:00", "09:40"));

        // 第16周
        list.add(createSchedule(1L, 1L, 1L, 16, 1, LocalDate.of(2026, 7, 6), "08:00", "09:40"));
        list.add(createSchedule(1L, 1L, 1L, 16, 2, LocalDate.of(2026, 7, 6), "14:00", "15:40"));
        list.add(createSchedule(2L, 2L, 2L, 16, 1, LocalDate.of(2026, 7, 6), "08:00", "09:40"));
        list.add(createSchedule(2L, 2L, 2L, 16, 2, LocalDate.of(2026, 7, 7), "14:00", "15:40"));
        list.add(createSchedule(3L, 3L, 4L, 16, 1, LocalDate.of(2026, 7, 7), "08:00", "09:40"));
        list.add(createSchedule(3L, 3L, 4L, 16, 2, LocalDate.of(2026, 7, 8), "14:00", "15:40"));
        list.add(createSchedule(4L, 4L, 5L, 16, 1, LocalDate.of(2026, 7, 8), "08:00", "09:40"));
        list.add(createSchedule(4L, 4L, 5L, 16, 3, LocalDate.of(2026, 7, 8), "19:00", "20:40"));
        list.add(createSchedule(5L, 5L, 3L, 16, 1, LocalDate.of(2026, 7, 9), "08:00", "09:40"));
        list.add(createSchedule(5L, 5L, 3L, 16, 2, LocalDate.of(2026, 7, 10), "14:00", "15:40"));
        list.add(createSchedule(1L, 1L, 6L, 16, 1, LocalDate.of(2026, 7, 10), "08:00", "09:40"));
        list.add(createSchedule(3L, 3L, 6L, 16, 1, LocalDate.of(2026, 7, 10), "08:00", "09:40"));

        // 第17周
        list.add(createSchedule(1L, 1L, 1L, 17, 1, LocalDate.of(2026, 7, 13), "08:00", "09:40"));
        list.add(createSchedule(2L, 2L, 2L, 17, 1, LocalDate.of(2026, 7, 13), "08:00", "09:40"));
        list.add(createSchedule(3L, 3L, 4L, 17, 1, LocalDate.of(2026, 7, 14), "08:00", "09:40"));
        list.add(createSchedule(4L, 4L, 5L, 17, 1, LocalDate.of(2026, 7, 15), "08:00", "09:40"));
        list.add(createSchedule(5L, 5L, 3L, 17, 1, LocalDate.of(2026, 7, 16), "08:00", "09:40"));

        return list;
    }

    private SysSchedule createSchedule(Long classId, Long teacherId, Long roomId, int week, int section, LocalDate date, String start, String end) {
        SysSchedule s = new SysSchedule();
        s.setTeachingClassId(classId);
        s.setTeacherId(teacherId);
        s.setClassroomId(roomId);
        s.setWeekNum(week);
        s.setSectionNum(section);
        s.setScheduleDate(date);
        s.setStartTime(LocalTime.parse(start));
        s.setEndTime(LocalTime.parse(end));
        return s;
    }

    private List<PhoneConfiscateRecord> createRecords() {
        List<PhoneConfiscateRecord> list = new ArrayList<>();

        // 第15周 (10条)
        list.add(createRecord(1L, 1L, 1L, 15, 1, 2, LocalDateTime.of(2026, 6, 29, 8, 25), 1, "讲台左侧"));
        list.add(createRecord(2L, 1L, 1L, 15, 2, 1, LocalDateTime.of(2026, 6, 29, 14, 15), 1, "讲台左侧"));
        list.add(createRecord(3L, 2L, 2L, 15, 1, 3, LocalDateTime.of(2026, 6, 29, 8, 40), 1, "讲台右侧"));
        list.add(createRecord(4L, 2L, 2L, 15, 2, 1, LocalDateTime.of(2026, 6, 30, 14, 20), 0, "讲台右侧"));
        list.add(createRecord(5L, 3L, 3L, 15, 1, 2, LocalDateTime.of(2026, 6, 30, 8, 30), 1, "门口左侧"));
        list.add(createRecord(7L, 4L, 4L, 15, 1, 1, LocalDateTime.of(2026, 7, 1, 8, 35), 1, "讲台右侧"));
        list.add(createRecord(8L, 4L, 4L, 15, 3, 4, LocalDateTime.of(2026, 7, 1, 19, 20), 0, "讲台右侧"));
        list.add(createRecord(9L, 5L, 5L, 15, 1, 2, LocalDateTime.of(2026, 7, 2, 8, 20), 1, "教室后方"));
        list.add(createRecord(10L, 5L, 5L, 15, 2, 1, LocalDateTime.of(2026, 7, 3, 14, 10), 0, "教室后方"));
        list.add(createRecord(11L, 1L, 1L, 15, 1, 1, LocalDateTime.of(2026, 7, 3, 8, 15), 1, "实验柜"));

        // 第16周 (12条)
        list.add(createRecord(13L, 1L, 1L, 16, 1, 2, LocalDateTime.of(2026, 7, 6, 8, 30), 0, "讲台左侧"));
        list.add(createRecord(14L, 1L, 1L, 16, 2, 1, LocalDateTime.of(2026, 7, 6, 14, 25), 0, "讲台左侧"));
        list.add(createRecord(15L, 2L, 2L, 16, 1, 3, LocalDateTime.of(2026, 7, 6, 8, 45), 0, "讲台右侧"));
        list.add(createRecord(16L, 2L, 2L, 16, 2, 2, LocalDateTime.of(2026, 7, 7, 14, 30), 1, "讲台右侧"));
        list.add(createRecord(17L, 3L, 3L, 16, 1, 1, LocalDateTime.of(2026, 7, 7, 8, 20), 1, "门口左侧"));
        list.add(createRecord(18L, 3L, 3L, 16, 2, 2, LocalDateTime.of(2026, 7, 8, 14, 15), 0, "门口左侧"));
        list.add(createRecord(19L, 4L, 4L, 16, 1, 1, LocalDateTime.of(2026, 7, 8, 8, 35), 1, "讲台右侧"));
        list.add(createRecord(20L, 4L, 4L, 16, 3, 3, LocalDateTime.of(2026, 7, 8, 19, 25), 0, "讲台右侧"));
        list.add(createRecord(21L, 5L, 5L, 16, 1, 2, LocalDateTime.of(2026, 7, 9, 8, 25), 0, "教室后方"));
        list.add(createRecord(22L, 5L, 5L, 16, 2, 1, LocalDateTime.of(2026, 7, 10, 14, 20), 1, "教室后方"));
        list.add(createRecord(23L, 1L, 1L, 16, 1, 1, LocalDateTime.of(2026, 7, 10, 8, 15), 0, "实验柜"));
        list.add(createRecord(24L, 3L, 3L, 16, 1, 2, LocalDateTime.of(2026, 7, 10, 8, 40), 1, "实验柜"));

        // 第17周 (5条)
        list.add(createRecord(25L, 1L, 1L, 17, 1, 1, LocalDateTime.of(2026, 7, 13, 8, 30), 0, "讲台左侧"));
        list.add(createRecord(26L, 2L, 2L, 17, 1, 2, LocalDateTime.of(2026, 7, 13, 8, 35), 0, "讲台右侧"));
        list.add(createRecord(27L, 3L, 3L, 17, 1, 1, LocalDateTime.of(2026, 7, 14, 8, 20), 0, "门口左侧"));
        list.add(createRecord(28L, 4L, 4L, 17, 1, 2, LocalDateTime.of(2026, 7, 15, 8, 25), 0, "讲台右侧"));
        list.add(createRecord(29L, 5L, 5L, 17, 1, 1, LocalDateTime.of(2026, 7, 16, 8, 30), 0, "教室后方"));

        return list;
    }

    private PhoneConfiscateRecord createRecord(Long scheduleId, Long classId, Long teacherId, int week, int section, int count, LocalDateTime time, int status, String location) {
        PhoneConfiscateRecord r = new PhoneConfiscateRecord();
        r.setScheduleId(scheduleId);
        r.setTeachingClassId(classId);
        r.setTeacherId(teacherId);
        r.setWeekNum(week);
        r.setSectionNum(section);
        r.setConfiscateCount(count);
        r.setConfiscateTime(time);
        r.setHandoverStatus(status);
        r.setCabinetLocation(location);
        r.setCreateTime(LocalDateTime.now());
        return r;
    }
}
