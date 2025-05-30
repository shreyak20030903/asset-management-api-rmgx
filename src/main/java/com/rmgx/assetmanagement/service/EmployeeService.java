	package com.rmgx.assetmanagement.service;

import com.rmgx.assetmanagement.entity.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    Employee addEmployee(Employee employee);
    List<Employee> getAllEmployees();
    Optional<Employee> getEmployeeById(Long id);
    Employee updateEmployee(Employee employee);
    void deleteEmployee(Long id) throws Exception;
}
