package com.phone.phonemanage.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.phone.phonemanage.entity.StatReport;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface StatReportMapper extends BaseMapper<StatReport> {

    // 按班级统计没收数量
    @Select("SELECT c.id, c.class_name as name, COALESCE(SUM(r.confiscate_count), 0) as count " +
            "FROM sys_teaching_class c " +
            "LEFT JOIN phone_confiscate_record r ON c.id = r.teaching_class_id " +
            "GROUP BY c.id, c.class_name ORDER BY count DESC")
    List<StatReport> statByClass();

    // 按教师统计没收数量
    @Select("SELECT t.id, t.teacher_name as name, COALESCE(SUM(r.confiscate_count), 0) as count " +
            "FROM sys_teacher t " +
            "LEFT JOIN phone_confiscate_record r ON t.id = r.teacher_id " +
            "GROUP BY t.id, t.teacher_name ORDER BY count DESC")
    List<StatReport> statByTeacher();

    // 按课程统计没收数量
    @Select("SELECT s.id, s.course_name as name, COALESCE(SUM(r.confiscate_count), 0) as count " +
            "FROM sys_schedule s " +
            "LEFT JOIN phone_confiscate_record r ON s.id = r.schedule_id " +
            "WHERE s.course_name IS NOT NULL " +
            "GROUP BY s.id, s.course_name ORDER BY count DESC")
    List<StatReport> statByCourse();

    // 按周次统计没收数量
    @Select("SELECT week_num as id, CONCAT('第', week_num, '周') as name, " +
            "COALESCE(SUM(confiscate_count), 0) as count " +
            "FROM phone_confiscate_record " +
            "GROUP BY week_num ORDER BY week_num")
    List<StatReport> statByWeek();

    // 按节次统计没收数量
    @Select("SELECT section_num as id, " +
            "CASE section_num WHEN 1 THEN '上午' WHEN 2 THEN '下午' WHEN 3 THEN '晚上' END as name, " +
            "COALESCE(SUM(confiscate_count), 0) as count " +
            "FROM phone_confiscate_record " +
            "GROUP BY section_num ORDER BY section_num")
    List<StatReport> statBySection();

    // 按学期统计没收数量
    @Select("SELECT sem.id, sem.semester_name as name, COALESCE(SUM(r.confiscate_count), 0) as count " +
            "FROM sys_semester sem " +
            "LEFT JOIN sys_teaching_class c ON sem.id = c.semester_id " +
            "LEFT JOIN phone_confiscate_record r ON c.id = r.teaching_class_id " +
            "GROUP BY sem.id, sem.semester_name ORDER BY sem.start_date DESC")
    List<StatReport> statBySemester();

    // 按移交状态统计
    @Select("SELECT handover_status as id, " +
            "CASE handover_status WHEN 0 THEN '未移交' WHEN 1 THEN '已移交' END as name, " +
            "COUNT(*) as count " +
            "FROM phone_confiscate_record " +
            "GROUP BY handover_status")
    List<StatReport> statByHandoverStatus();

    // 获取总数
    @Select("SELECT COALESCE(SUM(confiscate_count), 0) FROM phone_confiscate_record")
    Integer getTotalCount();

    // 按时间段统计
    @Select("SELECT id, name, count FROM (" +
            "SELECT 1 as id, '最近7天' as name, COALESCE(SUM(confiscate_count), 0) as count " +
            "FROM phone_confiscate_record WHERE confiscate_time >= DATE_SUB(NOW(), INTERVAL 7 DAY) " +
            "UNION ALL " +
            "SELECT 2 as id, '最近30天' as name, COALESCE(SUM(confiscate_count), 0) as count " +
            "FROM phone_confiscate_record WHERE confiscate_time >= DATE_SUB(NOW(), INTERVAL 30 DAY) " +
            "UNION ALL " +
            "SELECT 3 as id, '本学期' as name, COALESCE(SUM(confiscate_count), 0) as count " +
            "FROM phone_confiscate_record WHERE week_num >= 15) t")
    List<StatReport> statByTimeRange();
}
