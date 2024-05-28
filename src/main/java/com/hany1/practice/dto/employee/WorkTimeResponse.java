package com.hany1.practice.dto.employee;

import lombok.Getter;

import java.util.List;

@Getter
public class WorkTimeResponse {
    private List<WorkTimeDetailResponse> workTimeDetailResponses;
    private Integer sum;


    public WorkTimeResponse(List<WorkTimeDetailResponse> workTimeDetailResponses) {
        this.workTimeDetailResponses = workTimeDetailResponses;
        this.sum = sumTotalWorkTime(workTimeDetailResponses);
    }

    private Integer sumTotalWorkTime(List<WorkTimeDetailResponse> workTimeDetailResponses) {
        if (workTimeDetailResponses.isEmpty()) { return 0; }
        return workTimeDetailResponses.stream()
                .mapToInt(WorkTimeDetailResponse::getWorkingMinutes)
                .sum();
    }
}
