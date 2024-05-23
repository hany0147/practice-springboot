package com.hany1.practice.repository.employee;

import com.hany1.practice.entity.employee.Employee;
import com.hany1.practice.entity.employee.WorkHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface WorkHistoryRepository extends JpaRepository<WorkHistory, Long> {

    @Query("SELECT count(wh) > 0 " +
            "FROM WorkHistory wh " +
            "WHERE wh.employee = :employee " +
            "AND wh.isWorking = :isWorking " +
            "AND DATE(wh.checkTime) = :checkTime ")
    Boolean existsByCheckTimeAndEmployeeAndIsWorking(LocalDate checkTime, Employee employee, Boolean isWorking);
}
