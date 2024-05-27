package com.hany1.practice.controller.employee;

import com.hany1.practice.dto.employee.DayOffDetailResponse;
import com.hany1.practice.service.employee.DayOffService;
import com.hany1.practice.vo.employee.DayOffAddRequest;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class DayOffController {
    private final DayOffService dayOffService;

    @GetMapping("/dayoff")
    public DayOffDetailResponse getDayOffInfo(@RequestParam Long employeeId) {
        return dayOffService.getDayOffDetail(employeeId);
    }
    @PostMapping("/dayoff")
    public void registerDayOff(@RequestBody DayOffAddRequest request) {
        dayOffService.registerDayOff(request);
    }
}
