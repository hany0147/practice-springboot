package com.hany1.practice.repository.employee;

import com.hany1.practice.dto.employee.EmployeesResponse;
import com.hany1.practice.dto.employee.OverWorkTimeResponse;
import com.hany1.practice.entity.employee.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query("SELECT e FROM Employee e")
    List<EmployeesResponse> findEmployeeListResponses();

    @Query("SELECT new com.hany1.practice.dto.employee.OverWorkTimeResponse(e) " +
            "FROM Employee e")
    List<OverWorkTimeResponse> findOverWorkTimeResponses();
}
