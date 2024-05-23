package com.hany1.practice.dto.employee;

import com.hany1.practice.entity.employee.Employee;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class EmployeesResponse {
    private String employeeName;
    private String teamName;
    private String role;
    private LocalDate birthday;
    private LocalDate joinDate;

    public EmployeesResponse(Employee e) {
        this.employeeName = e.getEmployeeName();
        this.teamName = e.getTeam().getTeamName();
        this.role = e.getIsManager() ? "MANAGER" : "MEMBER";
        this.birthday = e.getBirthday();
        this.joinDate = e.getJoinDate();
    }


}
