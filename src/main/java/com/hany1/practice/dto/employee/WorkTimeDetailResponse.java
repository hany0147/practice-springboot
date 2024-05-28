package com.hany1.practice.dto.employee;

import lombok.Getter;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
public class WorkTimeDetailResponse {
    private LocalDate date;
    private Integer workingMinutes;
    private Boolean usingDayOff;

    public WorkTimeDetailResponse(LocalDate date, LocalDateTime checkInTime, LocalDateTime checkOutTime, Boolean isDayOff) {
        this.date = date;
        this.workingMinutes = calculateWorkTime(checkInTime, checkOutTime, isDayOff);
        this.usingDayOff = isDayOff;
    }

    private Integer calculateWorkTime(LocalDateTime checkInTime, LocalDateTime checkOutTime, Boolean isDayOff) {
        if (isDayOff) {
            return 0;
        }

        if (checkInTime == null || checkOutTime == null) {
            throw new IllegalArgumentException("출퇴근시간이 비어있습니다. 확인 바랍니다.");
        }

        return (int) Duration.between(checkInTime, checkOutTime).toMinutes();
    }
}
