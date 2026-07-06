package com.phone.phonemanage.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@TableName("sys_schedule")
public class SysSchedule {
    @TableId(type = IdType.AUTO)
    private Long id;
    private Long teachingClassId;
    private Long teacherId;
    private Long classroomId;
    private Integer weekNum;
    private Integer sectionNum;
    private LocalDate scheduleDate;
    private LocalTime startTime;
    private LocalTime endTime;
}