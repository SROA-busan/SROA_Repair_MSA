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
    public boolean completeRepair(Schedule schedule) {
        if (schedule.getStatus() == 0 || schedule.getStatus() == 3) {
            scheduleRepository.updateEndDate(schedule.getScheduleNum(), Timestamp.valueOf(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))));
            scheduleRepository.updateStatus(schedule.getScheduleNum(), 1);
        } else {
            return false;
        }
        //고객한테 알림 요청, 평가쓰라고
        return true;
    }

    @Override
    public void receiveProduct(Schedule schedule) {
        scheduleRepository.updateStatus(schedule.getScheduleNum(), 2);
    }

    @Override
    public void repairOfReceive(Schedule schedule) {
        scheduleRepository.updateStatus(schedule.getScheduleNum(), 4);
        // 고객한테 알림 보내야함
    }
}
