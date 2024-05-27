package com.hany1.practice.entity.employee;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder(toBuilder = true)
@Table(name = "day_off")
public class DayOff {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_day_off")
    private Long idDayOff;

    @OneToOne
    @JoinColumn(name = "employee_id", referencedColumnName = "id_employee", nullable = false)
    private Employee employee;

    @Column(name = "total_day")
    private Integer totalDay;

    @Column(name = "remain_day", columnDefinition = "INT CHECK (remain_day >= 0)")
    private Integer remainDay;

    public DayOff(Employee employee) {
        this.employee = employee;
        this.totalDay = isNew(employee) ? 11 : 15;
        this.remainDay = totalDay;
    }

    private boolean isNew(Employee employee) {
        if (employee.getJoinDate().getYear() == LocalDate.now().getYear()) {
            return true;
        }
        return false;
    }
}
