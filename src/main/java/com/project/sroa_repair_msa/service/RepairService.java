package com.project.sroa_repair_msa.service;

import com.project.sroa_repair_msa.model.Schedule;


public interface RepairService {

    Schedule searchSchedule(Long scheduleNum);

    boolean completeRepair(Schedule schedule);

    void receiveProduct(Schedule schedule);

    void repairOfReceive(Schedule schedule);
}
