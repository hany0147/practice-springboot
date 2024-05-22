package com.hany1.practice.entity.employee;

import com.hany1.practice.entity.team.Team;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Entity
@Getter
@RequiredArgsConstructor
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_employee")
    private Long idEmployee;

    @ManyToOne
    @JoinColumn(name = "team_id", nullable = false, referencedColumnName = "id_team")
    private Team team;

    public Employee(Team team, String employeeName, Boolean isManager, LocalDate joinDate, LocalDate birthday) {
        this.team = team;
        this.employeeName = employeeName;
        this.isManager = isManager;
        this.joinDate = joinDate;
        this.birthday = birthday;
    }

    @Column(name = "employee_name", length = 20)
    private String employeeName;

    @Column(name = "is_manager")
    private Boolean isManager;

    @Column(name = "join_date")
    private LocalDate joinDate;

    @Column(name = "birthday")
    private LocalDate birthday;

}
