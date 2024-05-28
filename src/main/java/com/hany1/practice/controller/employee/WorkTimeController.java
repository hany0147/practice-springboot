package com.hany1.practice.controller.employee;

import com.hany1.practice.dto.employee.OverWorkTimeResponse;
import com.hany1.practice.dto.employee.WorkTimeResponse;
import com.hany1.practice.service.employee.WorkTimeService;
import com.hany1.practice.vo.employee.EmployeeWorkTimeRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class WorkTimeController {
    private final WorkTimeService workTimeService;
    @GetMapping("/work")
    public WorkTimeResponse getWorkTimeDetail(@RequestParam Long employeeId,
                                              @RequestParam int year,
                                              @RequestParam int month) {
        return workTimeService.getWorkTimeDetail(employeeId, year, month);

    }

    @PostMapping("/work/in")
    public void checkWorkIn(@RequestBody EmployeeWorkTimeRequest request) {
        workTimeService.checkWorkIn(request);
    }

    @PostMapping("/work/out")
    public void checkWorkOut(@RequestBody EmployeeWorkTimeRequest request) {
        workTimeService.checkWorkOut(request);
    }


    @GetMapping("/work/overtime")
    public List<OverWorkTimeResponse> getAllOverWorkTime(@RequestParam int year,
                                                         @RequestParam int month) {
        return workTimeService.getAllOverWorkTime(year, month);
    }
}
