package com.project.sroa_repair_msa.controller;

import com.project.sroa_repair_msa.dto.ScheduleHandling;
import com.project.sroa_repair_msa.model.Schedule;
import com.project.sroa_repair_msa.service.RepairService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@ResponseBody
public class RepairController {
    // status : 0-> 예약완료 , 1 -> 처리 완료, 2 -> 수령, 3 -> 수리 완료, 4 -> 반납예약완료, 5-> 평가 완료
    RepairService repairService;

    @Autowired
    public RepairController(RepairService repairService) {
        this.repairService = repairService;
    }


    @GetMapping("/repair/healthCheck")
    public boolean healthCheck(){
        return true;
    }
    // 입고 처리
    @GetMapping("/repair/engineer/requestWarehousing/{scheduleNum}")
    public boolean requestWarehousing(@PathVariable("scheduleNum") Long scheduleNum) {
        Schedule schedule = repairService.searchSchedule(scheduleNum);

        if(schedule.getStatus()!=0)
            return false;
        repairService.updateState(scheduleNum, 2);


        return true;
    }
    // 고객 대면, 처리 완료
    @GetMapping("/repair/engineer/requestComplete/{scheduleNum}")
    public boolean requestComplete(@PathVariable("scheduleNum") Long scheduleNum) {
        Schedule schedule = repairService.searchSchedule(scheduleNum);
        if(schedule.getStatus()!=0&&schedule.getStatus()!=4)
            return false;
        repairService.updateState(scheduleNum, 1);

        return true;
    }
    // 입고중인 장비 수리 완료
    @GetMapping("/repair/engineer/requestRepair/{scheduleNum}")
    public boolean requestRepair(@PathVariable("scheduleNum") Long scheduleNum) {
        Schedule schedule = repairService.searchSchedule(scheduleNum);
        if(schedule.getStatus()!=2)
            return false;
        repairService.updateState(scheduleNum, 3);
        return true;
    }


}
