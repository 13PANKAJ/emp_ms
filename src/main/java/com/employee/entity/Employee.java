package com.employee.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public class Employee {
    private Long id;
    private String firstName;
    private String lastName;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;
    private double salary;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate joinDate;
    private String department;

    public Employee() {

    }

    public Employee(Long id, String firstName, String lastName, LocalDate dateOfBirth, double salary, LocalDate joinDate, String department) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.salary = salary;
        this.joinDate = joinDate;
        this.department = department;
    }

    // Getter and Setter for id
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    // Getter and Setter for firstName
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    // Getter and Setter for lastName
    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    // Getter and Setter for dateOfBirth
    public LocalDate getDateOfBirth() { return dateOfBirth; }
    public void setDateOfBirth(LocalDate dateOfBirth) { this.dateOfBirth = dateOfBirth; }

    // Getter and Setter for salary
    public double getSalary() { return salary; }
    public void setSalary(double salary) { this.salary = salary; }

    // Getter and Setter for joinDate
    public LocalDate getJoinDate() { return joinDate; }
    public void setJoinDate(LocalDate joinDate) { this.joinDate = joinDate; }

    // Getter and Setter for department
    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }
}
