package com.hany1.practice.repository.employee;

import com.hany1.practice.dto.employee.WorkTimeDetailResponse;
import com.hany1.practice.entity.employee.Employee;
import com.hany1.practice.entity.employee.WorkHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.List;
import java.util.Optional;

public interface WorkHistoryRepository extends JpaRepository<WorkHistory, Long> {

    @Query("SELECT count(wh) > 0 " +
            "FROM WorkHistory wh " +
            "WHERE wh.employee = :employee " +
            "AND DATE(wh.checkInTime) = :checkInTime ")
    Boolean existsByCheckInTimeAndEmployee(LocalDate checkInTime, Employee employee);

    @Query("SELECT count(wh) > 0 " +
            "FROM WorkHistory wh " +
            "WHERE wh.employee = :employee " +
            "AND DATE(wh.checkOutTime) = :checkOutTime ")
    Boolean existsByCheckOutTimeAndEmployee(LocalDate checkOutTime, Employee employee);

    Optional<WorkHistory> findByDateAndEmployee(LocalDate date, Employee employee);


    @Query("SELECT new com.hany1.practice.dto.employee.WorkTimeDetailResponse(wh.date," +
            "wh.checkInTime," +
            "wh.checkOutTime) " +
            "FROM WorkHistory wh " +
            "WHERE wh.employee.idEmployee = :employeeId " +
            "AND FUNCTION('YEAR', wh.date) = :year " +
            "AND FUNCTION('MONTH', wh.date) = :month")
    List<WorkTimeDetailResponse> findWorkTimeDetailResponsesByYearAndMonthAndEmployee(int year, int month, Long employeeId);
}
