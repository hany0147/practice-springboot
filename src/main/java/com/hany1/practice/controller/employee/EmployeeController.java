package com.hany1.practice.controller.employee;

import com.hany1.practice.dto.employee.GetEmployeesResponse;
import com.hany1.practice.service.employee.EmployeeService;
import com.hany1.practice.vo.employee.EmployeeAddRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
    public List<GetEmployeesResponse> getEmployees() {
        return employeeService.getEmployees();
    }
}
