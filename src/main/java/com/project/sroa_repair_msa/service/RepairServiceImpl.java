package com.project.sroa_repair_msa.service;


import com.project.sroa_repair_msa.model.Schedule;
import com.project.sroa_repair_msa.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class RepairServiceImpl implements RepairService {
    ScheduleRepository scheduleRepository;

    @Autowired
    public RepairServiceImpl(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    @Override
    public Schedule searchSchedule(Long scheduleNum) {
        return scheduleRepository.findByScheduleNum(scheduleNum);
    }

    @Override
    public void updateState(Long scheduleNum, Integer i) {
        scheduleRepository.updateStatus(scheduleNum, i);
    }
}
