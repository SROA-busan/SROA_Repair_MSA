package com.project.sroa_repair_msa.service;

import com.project.sroa_repair_msa.model.Schedule;


public interface RepairService {

    Schedule searchSchedule(Long scheduleNum);


    void updateState(Long scheduleNum, Integer i);
}
