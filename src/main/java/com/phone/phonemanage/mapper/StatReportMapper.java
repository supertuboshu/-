package com.phone.phonemanage.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.phone.phonemanage.entity.StatReport;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface StatReportMapper extends BaseMapper<StatReport> {

    @Select("SELECT teaching_class_id as id, CONCAT('班级', teaching_class_id) as name, " +
            "COALESCE(SUM(confiscate_count), 0) as count " +
            "FROM phone_confiscate_record GROUP BY teaching_class_id ORDER BY count DESC")
    List<StatReport> statByClass();

    @Select("SELECT teacher_id as id, CONCAT('教师', teacher_id) as name, " +
            "COALESCE(SUM(confiscate_count), 0) as count " +
            "FROM phone_confiscate_record GROUP BY teacher_id ORDER BY count DESC")
    List<StatReport> statByTeacher();

    @Select("SELECT schedule_id as id, CONCAT('课堂', schedule_id) as name, " +
            "COALESCE(SUM(confiscate_count), 0) as count " +
            "FROM phone_confiscate_record GROUP BY schedule_id ORDER BY count DESC")
    List<StatReport> statByCourse();

    @Select("SELECT week_num as id, CONCAT('第', week_num, '周') as name, " +
            "COALESCE(SUM(confiscate_count), 0) as count " +
            "FROM phone_confiscate_record GROUP BY week_num ORDER BY week_num")
    List<StatReport> statByWeek();

    @Select("SELECT section_num as id, " +
            "CASE section_num WHEN 1 THEN '上午' WHEN 2 THEN '下午' WHEN 3 THEN '晚上' ELSE CONCAT('第', section_num, '节') END as name, " +
            "COALESCE(SUM(confiscate_count), 0) as count " +
            "FROM phone_confiscate_record GROUP BY section_num ORDER BY section_num")
    List<StatReport> statBySection();

    @Select("SELECT handover_status as id, " +
            "CASE handover_status WHEN 0 THEN '未移交' WHEN 1 THEN '已移交' ELSE '未知' END as name, " +
            "COUNT(*) as count " +
            "FROM phone_confiscate_record GROUP BY handover_status")
    List<StatReport> statByHandoverStatus();

    @Select("SELECT COALESCE(SUM(confiscate_count), 0) FROM phone_confiscate_record")
    Integer getTotalCount();

    @Select("SELECT 1 as id, '最近7天' as name, COALESCE(SUM(confiscate_count), 0) as count " +
            "FROM phone_confiscate_record WHERE confiscate_time >= DATE_SUB(NOW(), INTERVAL 7 DAY) " +
            "UNION ALL " +
            "SELECT 2, '最近30天', COALESCE(SUM(confiscate_count), 0) " +
            "FROM phone_confiscate_record WHERE confiscate_time >= DATE_SUB(NOW(), INTERVAL 30 DAY) " +
            "UNION ALL " +
            "SELECT 3, '本学期', COALESCE(SUM(confiscate_count), 0) " +
            "FROM phone_confiscate_record WHERE week_num >= 1")
    List<StatReport> statByTimeRange();
}
