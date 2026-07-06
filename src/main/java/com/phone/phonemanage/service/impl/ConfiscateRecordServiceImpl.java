package com.phone.phonemanage.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.phone.phonemanage.entity.PhoneConfiscateRecord;
import com.phone.phonemanage.mapper.PhoneConfiscateRecordMapper;
import com.phone.phonemanage.service.ConfiscateRecordService;
import org.springframework.stereotype.Service;

@Service
public class ConfiscateRecordServiceImpl extends ServiceImpl<PhoneConfiscateRecordMapper, PhoneConfiscateRecord>
        implements ConfiscateRecordService {
}