package com.hany1.practice.dto.employee;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class WorkTimeDetailResponse {
    private LocalDate date;
    private Integer workingMinutes;

    public WorkTimeDetailResponse(LocalDate date, Integer workingMinutes) {
        this.date = date;
        this.workingMinutes = workingMinutes;
    }


}
