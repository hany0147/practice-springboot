package com.hany1.practice.dto.employee;

import com.hany1.practice.entity.employee.Employee;
import lombok.Getter;

@Getter
public class OverWorkTimeResponse {
    private Long employeeId;
    private String employeeName;
    private Integer overtimeMinutes;


    public OverWorkTimeResponse(Employee employee) {
        this.employeeId = employee.getIdEmployee();
        this.employeeName = employee.getEmployeeName();
    }

    public void setOvertimeMinutes(WorkTimeResponse workTimeResponse, int stardardHours) {
        int overTime = workTimeResponse.getSum() - (stardardHours * 60);
        this.overtimeMinutes = Math.max(overTime, 0);
    }
}
