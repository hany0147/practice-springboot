package com.hany1.practice.entity.employee;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Table(name = "day_off_history")
public class DayOffHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_day_off_history")
    private Long idDayOffHistory;

    @ManyToOne
    @JoinColumn(name = "employee_id", referencedColumnName = "id_employee", nullable = false)
    private Employee employee;

    @Column(name = "request_date")
    private LocalDate requestDate;

    @Column(name = "leave_date")
    private LocalDate leaveDate;

    public DayOffHistory(Employee employee, LocalDate leaveDate) {
        this.employee = employee;
        this.requestDate = LocalDate.now();
        this.leaveDate = leaveDate;
    }
}
