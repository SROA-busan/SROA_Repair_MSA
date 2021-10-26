package com.project.sroa_repair_msa.controller;

import com.project.sroa_repair_msa.dto.ScheduleHandling;
import com.project.sroa_repair_msa.model.Schedule;
import com.project.sroa_repair_msa.service.RepairService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
public class RepairController {
    RepairService repairService;

    @Autowired
    public RepairController(RepairService repairService) {
        this.repairService = repairService;
    }


    // status : 0-> 예약완료 , 1 -> 처리 완료, 2 -> 수령, 3 -> 수리 완료(반납 전), 4 ->  평가 완료
    @PostMapping("repair/engineer/requestForCompletion")
    public boolean requestForCompletion(@RequestBody ScheduleHandling form) {
        Schedule schedule = repairService.searchSchedule(form.getScheduleNum());

        if (form.getStatus() == 1) {
            if (schedule.getStatus() == 2) {
                System.out.println("처리완료 처리 : 아직 입고된 장비가 수리 중이기 뗴문에 완료되지 않음");
                return false;
            }
            // 당일 종결, 만약 불출된 물품이라면 2가지 일정에 대해 종결 처리
            return repairService.completeRepair(schedule);
        } else if (form.getStatus() == 2) {
            repairService.receiveProduct(schedule);
        } else if (form.getStatus() == 3) {
            // 수리 완료되면 고객에게 알림, 반납 예약 일정 처리
            repairService.repairOfReceive(schedule);
        } else {
            // 예약완료, 수리 완료는 엔지니어에서 요청할 수 없는 상태
            return false;
        }


        return true;
    }
}
