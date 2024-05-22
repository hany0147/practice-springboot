package com.hany1.practice.vo.employee;

import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class EmployeeAddRequest {
    private String employeeName;

    private String teamName;

    private Boolean isManager;

    private LocalDate joinDate;

    private LocalDate birthday;

}
