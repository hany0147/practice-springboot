package com.hany1.practice.service.employee;

import com.hany1.practice.dto.employee.OverWorkTimeResponse;
import com.hany1.practice.dto.employee.WorkTimeDetailResponse;
import com.hany1.practice.dto.employee.WorkTimeResponse;
import com.hany1.practice.entity.employee.Employee;
import com.hany1.practice.entity.employee.WorkHistory;
import com.hany1.practice.repository.employee.EmployeeRepository;
import com.hany1.practice.repository.employee.WorkHistoryRepository;
import com.hany1.practice.utils.HolidayChecker;
import com.hany1.practice.vo.employee.EmployeeWorkTimeRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class WorkTimeService {
    private final EmployeeRepository employeeRepository;
    private final WorkHistoryRepository workHistoryRepository;

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

    public List<OverWorkTimeResponse> getAllOverWorkTime(int year, int month) {

        int standardHours = makeStardardHours(year, month);

        List<OverWorkTimeResponse> overWorkTimeResponses = employeeRepository.findOverWorkTimeResponses()
                .stream()
                .map(overWorkTimeResponse -> {
                    WorkTimeResponse workTimeResponse = getWorkTimeDetail(overWorkTimeResponse.getEmployeeId(), year, month);
                    overWorkTimeResponse.setOvertimeMinutes(workTimeResponse, standardHours);
                    return overWorkTimeResponse;
                }).toList();

        return overWorkTimeResponses;
    }

    public int makeStardardHours(int year, int month) {
        HolidayChecker holidayChecker = new HolidayChecker();
        List<LocalDate> holidays = holidayChecker.getHolidayList();
        System.out.println("holidays = " + holidays);
        LocalDate startDate = LocalDate.of(year, month, 1);
        LocalDate endDate = startDate.plusMonths(1);

        int standardDays = 0;
        while (startDate.isBefore(endDate)) {
            if (startDate.getDayOfWeek() != DayOfWeek.SUNDAY && startDate.getDayOfWeek() != DayOfWeek.SATURDAY && !holidays.contains(startDate)) {
                standardDays++;
            }
            startDate = startDate.plusDays(1);
        }
        System.out.println("standardDays = " + standardDays);
        return standardDays * 8;
    }
}
