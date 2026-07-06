-- ================================================
-- 课堂手机管理系统 - 数据库创建脚本
-- ================================================

-- 创建数据库
CREATE DATABASE IF NOT EXISTS class_phone_manage DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE class_phone_manage;

-- ================================================
-- 1. 学期表
-- ================================================
CREATE TABLE IF NOT EXISTS sys_semester (
    id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '主键ID',
    semester_name VARCHAR(50) NOT NULL COMMENT '学期名称，如：2025-2026学年第一学期',
    start_date DATE NOT NULL COMMENT '学期开始日期',
    end_date DATE NOT NULL COMMENT '学期结束日期',
    status TINYINT DEFAULT 1 COMMENT '状态：0-禁用，1-启用',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间'
) COMMENT '学期表';

-- ================================================
-- 2. 班级表
-- ================================================
CREATE TABLE IF NOT EXISTS sys_teaching_class (
    id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '主键ID',
    class_name VARCHAR(100) NOT NULL COMMENT '班级名称',
    grade VARCHAR(20) COMMENT '年级',
    major VARCHAR(50) COMMENT '专业',
    student_count INT DEFAULT 0 COMMENT '学生人数',
    semester_id BIGINT COMMENT '所属学期ID',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    FOREIGN KEY (semester_id) REFERENCES sys_semester(id)
) COMMENT '班级表';

-- ================================================
-- 3. 教室表
-- ================================================
CREATE TABLE IF NOT EXISTS sys_classroom (
    id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '主键ID',
    classroom_name VARCHAR(50) NOT NULL COMMENT '教室名称',
    building VARCHAR(50) COMMENT '教学楼',
    floor INT COMMENT '楼层',
    capacity INT COMMENT '容量',
    cabinet_location VARCHAR(100) COMMENT '手机柜位置',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间'
) COMMENT '教室表';

-- ================================================
-- 4. 教师表
-- ================================================
CREATE TABLE IF NOT EXISTS sys_teacher (
    id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '主键ID',
    teacher_name VARCHAR(50) NOT NULL COMMENT '教师姓名',
    teacher_no VARCHAR(20) UNIQUE COMMENT '教师工号',
    phone VARCHAR(20) COMMENT '联系电话',
    email VARCHAR(50) COMMENT '邮箱',
    department VARCHAR(50) COMMENT '所属院系',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间'
) COMMENT '教师表';

-- ================================================
-- 5. 课表（授课安排）
-- ================================================
CREATE TABLE IF NOT EXISTS sys_schedule (
    id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '主键ID',
    teaching_class_id BIGINT NOT NULL COMMENT '教学班级ID',
    teacher_id BIGINT NOT NULL COMMENT '教师ID',
    classroom_id BIGINT COMMENT '教室ID',
    course_name VARCHAR(100) COMMENT '课程名称',
    week_num INT NOT NULL COMMENT '周次',
    section_num INT NOT NULL COMMENT '节次（1-上午，2-下午，3-晚上）',
    schedule_date DATE NOT NULL COMMENT '授课日期',
    start_time TIME COMMENT '开始时间',
    end_time TIME COMMENT '结束时间',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    FOREIGN KEY (teaching_class_id) REFERENCES sys_teaching_class(id),
    FOREIGN KEY (teacher_id) REFERENCES sys_teacher(id),
    FOREIGN KEY (classroom_id) REFERENCES sys_classroom(id)
) COMMENT '课表';

-- ================================================
-- 6. 手机没收记录表
-- ================================================
CREATE TABLE IF NOT EXISTS phone_confiscate_record (
    id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '主键ID',
    schedule_id BIGINT NOT NULL COMMENT '课表ID',
    teaching_class_id BIGINT COMMENT '教学班级ID',
    teacher_id BIGINT NOT NULL COMMENT '教师ID',
    week_num INT COMMENT '周次',
    section_num INT COMMENT '节次',
    confiscate_count INT DEFAULT 0 COMMENT '没收手机数量',
    confiscate_photo VARCHAR(255) COMMENT '没收照片',
    confiscate_time DATETIME NOT NULL COMMENT '没收时间',
    handover_status TINYINT DEFAULT 0 COMMENT '移交状态：0-未移交，1-已移交',
    handover_confirm_time DATETIME COMMENT '移交确认时间',
    cabinet_location VARCHAR(100) COMMENT '手机柜位置',
    remark VARCHAR(500) COMMENT '备注',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    FOREIGN KEY (schedule_id) REFERENCES sys_schedule(id),
    FOREIGN KEY (teaching_class_id) REFERENCES sys_teaching_class(id),
    FOREIGN KEY (teacher_id) REFERENCES sys_teacher(id)
) COMMENT '手机没收记录表';

-- ================================================
-- 7. 手机移交记录表
-- ================================================
CREATE TABLE IF NOT EXISTS phone_handover_record (
    id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '主键ID',
    confiscate_record_id BIGINT NOT NULL COMMENT '没收记录ID',
    handover_time DATETIME NOT NULL COMMENT '移交时间',
    handover_person VARCHAR(50) COMMENT '移交人',
    receiver_person VARCHAR(50) COMMENT '接收人',
    handover_status TINYINT DEFAULT 1 COMMENT '移交状态：1-已移交，2-已领取',
    remark VARCHAR(500) COMMENT '备注',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    FOREIGN KEY (confiscate_record_id) REFERENCES phone_confiscate_record(id)
) COMMENT '手机移交记录表';

-- ================================================
-- 初始化数据
-- ================================================

-- 插入学期数据
INSERT INTO sys_semester (semester_name, start_date, end_date, status) VALUES
('2025-2026学年第一学期', '2025-09-01', '2026-01-15', 1),
('2025-2026学年第二学期', '2026-02-24', '2026-07-10', 1);

-- 插入班级数据
INSERT INTO sys_teaching_class (class_name, grade, major, student_count, semester_id) VALUES
('计算机2401班', '2024级', '计算机科学与技术', 45, 2),
('计算机2402班', '2024级', '计算机科学与技术', 43, 2),
('软件2401班', '2024级', '软件工程', 48, 2),
('软件2402班', '2024级', '软件工程', 46, 2),
('信管2401班', '2024级', '信息管理与信息系统', 42, 2);

-- 插入教室数据
INSERT INTO sys_classroom (classroom_name, building, floor, capacity, cabinet_location) VALUES
('A101', '第一教学楼', 1, 60, '讲台左侧'),
('A201', '第一教学楼', 2, 60, '讲台右侧'),
('A301', '第一教学楼', 3, 60, '门口左侧'),
('B101', '第二教学楼', 1, 50, '讲台左侧'),
('B201', '第二教学楼', 2, 50, '讲台右侧'),
('C301', '实验楼', 3, 40, '教室后方');

-- 插入教师数据
INSERT INTO sys_teacher (teacher_name, teacher_no, phone, department) VALUES
('张老师', 'T001', '13800138001', '计算机学院'),
('李老师', 'T002', '13800138002', '计算机学院'),
('王老师', 'T003', '13800138003', '软件学院'),
('刘老师', 'T004', '13800138004', '软件学院'),
('陈老师', 'T005', '13800138005', '信息学院');

-- 插入课表数据（模拟2周的授课安排）
INSERT INTO sys_schedule (teaching_class_id, teacher_id, classroom_id, course_name, week_num, section_num, schedule_date, start_time, end_time) VALUES
-- 第15周
(1, 1, 1, 'Java程序设计', 15, 1, '2026-07-06', '08:00', '09:40'),
(1, 1, 1, 'Java程序设计', 15, 2, '2026-07-06', '14:00', '15:40'),
(2, 2, 2, '数据结构', 15, 1, '2026-07-06', '08:00', '09:40'),
(3, 3, 4, '软件工程', 15, 1, '2026-07-07', '08:00', '09:40'),
(3, 3, 4, '软件工程', 15, 2, '2026-07-07', '14:00', '15:40'),
(4, 4, 5, '数据库原理', 15, 1, '2026-07-07', '08:00', '09:40'),
(5, 5, 3, '信息系统分析', 15, 1, '2026-07-08', '08:00', '09:40'),
-- 第16周
(1, 1, 1, 'Java程序设计', 16, 1, '2026-07-13', '08:00', '09:40'),
(1, 1, 1, 'Java程序设计', 16, 2, '2026-07-13', '14:00', '15:40'),
(2, 2, 2, '数据结构', 16, 1, '2026-07-13', '08:00', '09:40'),
(3, 3, 4, '软件工程', 16, 1, '2026-07-14', '08:00', '09:40'),
(4, 4, 5, '数据库原理', 16, 1, '2026-07-14', '08:00', '09:40'),
(5, 5, 3, '信息系统分析', 16, 1, '2026-07-15', '08:00', '09:40');

-- 插入没收记录数据
INSERT INTO phone_confiscate_record (schedule_id, teaching_class_id, teacher_id, week_num, section_num, confiscate_count, confiscate_time, handover_status, cabinet_location, remark) VALUES
(1, 1, 1, 15, 1, 2, '2026-07-06 08:30:00', 1, '讲台左侧', '上课玩手机'),
(2, 1, 1, 15, 2, 1, '2026-07-06 14:20:00', 0, '讲台左侧', '课堂睡觉'),
(3, 2, 2, 15, 1, 3, '2026-07-06 08:45:00', 1, '讲台右侧', '多人同时玩手机'),
(5, 3, 3, 15, 2, 1, '2026-07-07 14:15:00', 1, '门口左侧', '玩游戏'),
(7, 5, 5, 15, 1, 2, '2026-07-08 08:20:00', 0, '教室后方', '看视频');
