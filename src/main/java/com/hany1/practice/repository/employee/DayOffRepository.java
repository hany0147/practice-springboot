package com.hany1.practice.repository.employee;

import com.hany1.practice.dto.employee.DayOffDetailResponse;
import com.hany1.practice.entity.employee.DayOff;
import com.hany1.practice.entity.employee.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface DayOffRepository extends JpaRepository<DayOff, Long> {

    @Query("SELECT new com.hany1.practice.dto.employee.DayOffDetailResponse(d) " +
            "FROM DayOff d " +
            "WHERE d.employee.idEmployee = :employeeId")
    DayOffDetailResponse findDayOffDetailResponseByEmployeeId(Long employeeId);

    Optional<DayOff> findByEmployee(Employee employee);
}
