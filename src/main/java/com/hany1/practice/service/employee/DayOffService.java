package com.hany1.practice.service.employee;

import com.hany1.practice.dto.employee.DayOffDetailResponse;
import com.hany1.practice.entity.employee.DayOff;
import com.hany1.practice.entity.employee.DayOffHistory;
import com.hany1.practice.entity.employee.Employee;
import com.hany1.practice.entity.employee.WorkHistory;
import com.hany1.practice.repository.employee.DayOffHistoryRepository;
import com.hany1.practice.repository.employee.DayOffRepository;
import com.hany1.practice.repository.employee.EmployeeRepository;
import com.hany1.practice.repository.employee.WorkHistoryRepository;
import com.hany1.practice.vo.employee.DayOffAddRequest;
import com.sun.jdi.request.DuplicateRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class DayOffService {
    private final EmployeeRepository employeeRepository;
    private final DayOffRepository dayOffRepository;
    private final DayOffHistoryRepository dayOffHistoryRepository;
    private final WorkHistoryRepository workHistoryRepository;


    @Transactional
    public void registerDayOff(DayOffAddRequest request) {
        // 우선 해당 직원을 조회하고,
        Employee employee = employeeRepository.findById(request.getEmployeeId())
                .orElseThrow(() -> new NoSuchElementException("해당직원의 아이디가 존재하지 않습니다."));
        // 해당 직원에게 연차가 남아 있는지 확인하고,
        DayOff dayOff = dayOffRepository.findByEmployee(employee)
                .orElseThrow(() -> new NoSuchElementException("해당직원의 연차정보가 존재하지 않습니다."));
        // 남아 있지 않다면,
        if (dayOff.getRemainDay() == 0) {
            throw new IllegalStateException("연차 사용횟수가 0이므로, 더이상 연차를 사용하실 수 없습니다.");
        }
        // 신청날짜가 team에서 정한 날짜 기준에 맞는지 확인하고,
        // 기간을 넘어섰다면,
        if (LocalDate.now().plusDays(employee.getTeam().getAdvanceNoticePeriod()).isAfter(request.getLeaveDate())) {
            throw new IllegalStateException("연차 사전 신청허용기간을 지났으므로, 연차를 신청 할 수 없습니다.");
        }

        // 이미 해당 날짜에 연차를 신청했다면,
        if (dayOffHistoryRepository.existsByEmployeeAndLeaveDate(employee, request.getLeaveDate())) {
            throw new DuplicateRequestException("해당 일자는 이미 연차 신청이 되어 있습니다.");
        }

        // workHistory, dayoffHistory에  기록 남기고,
        WorkHistory workHistory = WorkHistory.registerDayOff(employee, request.getLeaveDate());
        workHistoryRepository.save(workHistory);

        DayOffHistory dayOffHistory = new DayOffHistory(employee, request.getLeaveDate());
        dayOffHistoryRepository.save(dayOffHistory);

        // dayOff를 업데이트한다.
        DayOff dayoff = dayOff.toBuilder()
                .remainDay(dayOff.getRemainDay() - 1)
                .build();
        dayOffRepository.save(dayoff);
    }

    public DayOffDetailResponse getDayOffDetail(Long employeeId) {
        return dayOffRepository.findDayOffDetailResponseByEmployeeId(employeeId);
    }
}
