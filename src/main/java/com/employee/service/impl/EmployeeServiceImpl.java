package com.employee.service.impl;

import com.employee.entity.Employee;
import com.employee.repository.EmployeeRepository;
import com.employee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.List;
import java.util.Optional;


@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public Employee createEmployee(Employee employee) throws IOException {
        return employeeRepository.saveEmployee(employee);
    }

    @Override
    public List<Employee> createEmployees(List<Employee> employees) throws IOException {
        return employees.stream()
                .map(employee -> {
                    try {
                        return employeeRepository.saveEmployee(employee);
                    } catch (IOException e) {
                        throw new RuntimeException("Failed to save employee", e);
                    }
                })
                .toList();
    }
    public Optional<Employee>  getEmployeeById(Long id) throws IOException {
        return employeeRepository.getEmployeeById(id);
    }

    @Override
    public List<Employee> getEmployees(String name, Double fromSalary, Double toSalary) throws IOException {
        return employeeRepository.findEmployees(name, fromSalary, toSalary);
    }
}
