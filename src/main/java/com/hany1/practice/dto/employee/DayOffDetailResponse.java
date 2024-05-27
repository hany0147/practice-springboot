package com.hany1.practice.dto.employee;

import com.hany1.practice.entity.employee.DayOff;
import com.hany1.practice.entity.employee.Employee;
import lombok.Getter;

@Getter
public class DayOffDetailResponse {
    private Long employeeId;
    private String employeeName;
    private Integer remainDay;

    public DayOffDetailResponse(DayOff d) {
        this.employeeId = d.getEmployee().getIdEmployee();
        this.employeeName = d.getEmployee().getEmployeeName();
        this.remainDay = d.getRemainDay();
    }
}
