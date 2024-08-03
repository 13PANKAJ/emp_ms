package com.employee.controller;

import com.employee.entity.Employee;
import com.employee.exception.EmployeeNotFoundException;
import com.employee.exception.FileOperationException;
import com.employee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<List<Long>> createEmployee(@RequestBody List<Employee> employees) throws IOException {
        try {

            // Create employee and retrieve the created employee
            List<Employee> createdEmployees = employeeService.createEmployees(employees);
            List<Long> employeeIds = createdEmployees.stream()
                    .map(Employee::getId)
                    .toList();
            return new ResponseEntity<>(employeeIds, HttpStatus.CREATED);
        } catch (IOException e) {
            throw new FileOperationException("Error saving employee: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) throws IOException {
        try {
            Optional<Employee> employeeOptional = employeeService.getEmployeeById(id);

            return employeeOptional.map(employee -> new ResponseEntity<>(employee, HttpStatus.OK))
                    .orElseThrow(() -> new EmployeeNotFoundException("Employee not found with id: " + id));
        } catch (IOException e) {
            throw new FileOperationException("Error retrieving employee: " + e.getMessage());
        }
    }


    @GetMapping
    public ResponseEntity<List<Employee>> getEmployees(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Double fromSalary,
            @RequestParam(required = false) Double toSalary) throws IOException {
        try {
        List<Employee> employees = employeeService.getEmployees(name, fromSalary, toSalary);
        return new ResponseEntity<>(employees, HttpStatus.OK);
        } catch (IOException e) {
            throw new FileOperationException("Error retrieving employees: " + e.getMessage());
        }
    }
}
