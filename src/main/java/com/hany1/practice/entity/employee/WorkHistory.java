package com.hany1.practice.entity.employee;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Entity
@RequiredArgsConstructor
@Getter
@Table(name = "work_history")
public class WorkHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_work_history")
    private Long idWorkHistory;

    @ManyToOne
    @JoinColumn(name="employee_id", referencedColumnName = "id_employee", nullable = false)
    private Employee employee;

    @Column(name = "is_working")
    private Boolean isWorking;

    @Column(name = "check_time")
    private LocalDateTime checkTime;

    public WorkHistory(Employee employee, Boolean isWorking) {
        this.employee = employee;
        this.isWorking = isWorking;
        this.checkTime = LocalDateTime.now();
    }


}
