package com.hany1.practice.vo.employee;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class DayOffAddRequest {
    private Long employeeId;
    private LocalDate leaveDate;
}
