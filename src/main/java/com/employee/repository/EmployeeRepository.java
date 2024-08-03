package com.employee.repository;

import com.employee.entity.Employee;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class EmployeeRepository {
    private static final String FILE_PATH = "employees.json";
    private final ObjectMapper objectMapper;

    @Autowired
    public EmployeeRepository(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public List<Employee> getAllEmployees() throws IOException {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            return new ArrayList<>();
        }
        return objectMapper.readValue(file, objectMapper.getTypeFactory().constructCollectionType(List.class, Employee.class));
    }

    public Employee saveEmployee(Employee employee) throws IOException {
        List<Employee> employees = getAllEmployees();
        long nextId = employees.isEmpty() ? 1 : employees.get(employees.size() - 1).getId() + 1;
        employee.setId(nextId);
        employees.add(employee);
        objectMapper.writeValue(new File(FILE_PATH), employees);
        return employee;
    }

    public Optional<Employee> getEmployeeById(Long id) throws IOException {
        return getAllEmployees().stream()
                .filter(employee -> employee.getId().equals(id))
                .findFirst();
    }

    public List<Employee> findEmployees(String name, Double fromSalary, Double toSalary) throws IOException {
        List<Employee> employees = getAllEmployees();
        return employees.stream()
                .filter(emp -> (name == null || emp.getFirstName().contains(name) || emp.getLastName().contains(name)) &&
                        (fromSalary == null || emp.getSalary() >= fromSalary) &&
                        (toSalary == null || emp.getSalary() <= toSalary))
                .collect(Collectors.toList());
    }
}
