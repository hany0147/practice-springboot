package com.hany1.practice.dto.employee;

import lombok.Getter;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
public class WorkTimeDetailResponse {
    private LocalDate date;
    private Integer workingMinutes;

    public WorkTimeDetailResponse(LocalDate date, LocalDateTime checkInTime, LocalDateTime checkOutTime) {
        this.date = date;
        this.workingMinutes = calculateWorkTime(checkInTime, checkOutTime);
    }

    private Integer calculateWorkTime(LocalDateTime checkInTime, LocalDateTime checkOutTime) {
        if (checkInTime == null || checkOutTime == null) {
            throw new IllegalArgumentException("출퇴근시간이 비어있습니다. 확인 바랍니다.");
        }
        System.out.println("checkInTime = " + checkInTime);
        System.out.println("checkOutTime = " + checkOutTime);
        return (int) Duration.between(checkInTime, checkOutTime).toMinutes();
    }
}
