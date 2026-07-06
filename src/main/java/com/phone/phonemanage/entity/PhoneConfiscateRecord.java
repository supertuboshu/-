package com.phone.phonemanage.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@TableName("phone_confiscate_record")
public class PhoneConfiscateRecord {
    @TableId(type = IdType.AUTO)
    private Long id;
    private Long scheduleId;
    private Long teachingClassId;
    private Long teacherId;
    private Integer weekNum;
    private Integer sectionNum;
    private Integer confiscateCount;
    private String confiscatePhoto;
    private LocalDateTime confiscateTime;
    private Integer handoverStatus;
    private LocalDateTime handoverConfirmTime;
    private String cabinetLocation;
    private String remark;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}