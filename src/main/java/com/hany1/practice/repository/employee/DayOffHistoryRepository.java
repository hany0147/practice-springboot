package com.hany1.practice.repository.employee;

import com.hany1.practice.entity.employee.DayOffHistory;
import com.hany1.practice.entity.employee.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface DayOffHistoryRepository extends JpaRepository<DayOffHistory, Long> {

    boolean existsByEmployeeAndLeaveDate(Employee employee, LocalDate leaveDate);
}
