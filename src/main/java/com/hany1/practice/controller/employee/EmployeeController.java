package com.hany1.practice.controller.employee;

import com.hany1.practice.dto.employee.EmployeesResponse;
import com.hany1.practice.dto.employee.WorkTimeResponse;
import com.hany1.practice.service.employee.EmployeeService;
import com.hany1.practice.vo.employee.EmployeeAddRequest;
import com.hany1.practice.vo.employee.EmployeeWorkTimeRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;

    @PostMapping("/employee")
    public void addEmployee(@RequestBody EmployeeAddRequest request) {
        employeeService.addEmployee(request);
    }

    @GetMapping("/employee/all")
    public List<EmployeesResponse> getEmployees() {
        return employeeService.getEmployees();
    }




}
