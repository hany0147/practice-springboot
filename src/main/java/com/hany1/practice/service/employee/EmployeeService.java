package com.hany1.practice.service.employee;

import com.hany1.practice.dto.employee.GetEmployeesResponse;
import com.hany1.practice.entity.employee.Employee;
import com.hany1.practice.entity.team.Team;
import com.hany1.practice.repository.employee.EmployeeRepository;
import com.hany1.practice.repository.team.TeamRepository;
import com.hany1.practice.vo.employee.EmployeeAddRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final TeamRepository teamRepository;

    public void addEmployee(EmployeeAddRequest request) {
        // 기존에 존재하는 직원인가?

        // 존재하는 직원이라면, throw 에러

        // 존재하는 팀인가?
        Team team = teamRepository.findByTeamName(request.getTeamName())
                .orElseThrow(IllegalArgumentException::new);

        // 존재하지 않는 직원이면 새로 생성
        Employee employee = new Employee(
                team,
                request.getEmployeeName(),
                request.getIsManager(),
                request.getJoinDate(),
                request.getBirthday()
                );
        employeeRepository.save(employee);
    }

    public List<GetEmployeesResponse> getEmployees() {
        List<GetEmployeesResponse> employees = employeeRepository.findEmployeeListResponses();
        return employees;
    }
}
