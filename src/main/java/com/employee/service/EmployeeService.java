package com.employee.service;

import com.employee.entity.Employee;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface EmployeeService {

    List<Employee> createEmployees(List<Employee> employees) throws IOException;
    Optional<Employee> getEmployeeById(Long id) throws IOException;
    List<Employee> getEmployees(String name, Double fromSalary, Double toSalary) throws IOException;
    Employee createEmployee(Employee employee) throws IOException;
}
