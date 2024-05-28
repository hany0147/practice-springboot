package com.hany1.practice.service.employee;

import com.hany1.practice.dto.employee.EmployeesResponse;
import com.hany1.practice.dto.employee.WorkTimeDetailResponse;
import com.hany1.practice.dto.employee.WorkTimeResponse;
import com.hany1.practice.entity.employee.DayOff;
import com.hany1.practice.entity.employee.Employee;
import com.hany1.practice.entity.employee.WorkHistory;
import com.hany1.practice.entity.team.Team;
import com.hany1.practice.repository.employee.DayOffRepository;
import com.hany1.practice.repository.employee.EmployeeRepository;
import com.hany1.practice.repository.employee.WorkHistoryRepository;
import com.hany1.practice.repository.team.TeamRepository;
import com.hany1.practice.vo.employee.EmployeeAddRequest;
import com.hany1.practice.vo.employee.EmployeeWorkTimeRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final TeamRepository teamRepository;
    private final DayOffRepository dayOffRepository;

    @Transactional
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

        DayOff dayOff = new DayOff(employee);
        dayOffRepository.save(dayOff);
    }

    public List<EmployeesResponse> getEmployees() {
        List<EmployeesResponse> employees = employeeRepository.findEmployeeListResponses();
        return employees;
    }


}
