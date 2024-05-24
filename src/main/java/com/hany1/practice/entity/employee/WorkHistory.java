package com.hany1.practice.entity.employee;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Table(name = "work_history")
@Builder(toBuilder = true)
public class WorkHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_work_history")
    private Long idWorkHistory;

    @ManyToOne
    @JoinColumn(name = "employee_id", referencedColumnName = "id_employee", nullable = false)
    private Employee employee;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "check_in_time")
    private LocalDateTime checkInTime;

    @Column(name = "check_out_time")
    private LocalDateTime checkOutTime;


    public static WorkHistory checkIn(Employee employee) {
        WorkHistory workHistory = WorkHistory.builder()
                .employee(employee)
                .date(LocalDate.now())
                .checkInTime(LocalDateTime.now())
                .build();
        return workHistory;
    }

    public static WorkHistory checkOut(WorkHistory oldWorkHistory) {
        WorkHistory workHistory = oldWorkHistory.toBuilder()
                .checkOutTime(LocalDateTime.now())
                .build();
        return workHistory;
    }



}
