package com.hany1.practice.entity.team;

import com.hany1.practice.entity.employee.Employee;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@RequiredArgsConstructor
@Getter
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_team")
    private Long idTeam;

    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL)
    private Set<Employee> employees = new HashSet<>();

    @Column(name = "team_name", length = 45)
    private String teamName;

    public Team(String teamName) {
        if (teamName == null || teamName.isBlank()) {
            throw new IllegalArgumentException("팀명을 입력하시기바랍니다.");
        }
        this.teamName = teamName;
    }

    // 수세기
    public int countMember() {
        if (employees.size() == 0) {
            return 0;
        }
        return employees.size();
    }

    public List<String> getManagerName() {
        if (employees.size() != 0) {
            List<String> managerNames = this.employees.stream()
                    .filter(Employee::getIsManager)
                    .map(Employee::getEmployeeName)
                    .toList();
            return managerNames;
        }
        return null;
    }
}
