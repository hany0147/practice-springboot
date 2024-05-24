package com.hany1.practice.service.employee;

import com.hany1.practice.dto.employee.EmployeesResponse;
import com.hany1.practice.dto.employee.WorkTimeDetailResponse;
import com.hany1.practice.dto.employee.WorkTimeResponse;
import com.hany1.practice.entity.employee.Employee;
import com.hany1.practice.entity.employee.WorkHistory;
import com.hany1.practice.entity.team.Team;
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
    private final WorkHistoryRepository workHistoryRepository;

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
    }

    public List<EmployeesResponse> getEmployees() {
        List<EmployeesResponse> employees = employeeRepository.findEmployeeListResponses();
        return employees;
    }

    @Transactional
    public void checkWorkIn(EmployeeWorkTimeRequest request) {
        // 등록된 직원인지?
        Employee employee = employeeRepository.findById(request.getEmployeeId())
                .orElseThrow(() -> new NoSuchElementException("해당직원의 아이디가 존재하지 않습니다."));
        // 또다시 출근한건 아닌지?
        if (workHistoryRepository.existsByCheckInTimeAndEmployee(LocalDate.now(), employee)) {
            throw new IllegalStateException("이미 출근하셨습니다.");
        }
        // 생성
        WorkHistory workHistory = WorkHistory.checkIn(employee);
        workHistoryRepository.save(workHistory);

    }

    @Transactional
    public void checkWorkOut(EmployeeWorkTimeRequest request) {
        Employee employee = employeeRepository.findById(request.getEmployeeId())
                .orElseThrow(() -> new NoSuchElementException("해당직원의 아이디가 존재하지 않습니다."));
        // 출근이 존재하지 않는다면?
        if (!workHistoryRepository.existsByCheckInTimeAndEmployee(LocalDate.now(), employee)) {
            throw new IllegalStateException("출근시간이 존재하지 않습니다. 관리자에게 문의하세요.");
        }

        // 퇴근이 이미 존재한다면?
        if (workHistoryRepository.existsByCheckOutTimeAndEmployee(LocalDate.now(), employee)) {
            throw new IllegalStateException("이미 퇴근 상태입니다.");
        }

        // 생성
        WorkHistory oldWorkHistory = workHistoryRepository.findByDateAndEmployee(LocalDate.now(), employee)
                .orElseThrow(() -> new NoSuchElementException("해당 날짜에 출퇴근 기록이 존재하지 않습니다."));
        WorkHistory workHistory = WorkHistory.checkOut(oldWorkHistory);
        workHistoryRepository.save(workHistory);
    }

    public WorkTimeResponse getWorkTimeDetail(Long employeeId, int year, int month) {

        List<WorkTimeDetailResponse> workTimeDetailResponses = workHistoryRepository.findWorkTimeDetailResponsesByYearAndMonthAndEmployee(year, month, employeeId);

        WorkTimeResponse workTimeResponse = new WorkTimeResponse(workTimeDetailResponses);
        return workTimeResponse;
    }
}
