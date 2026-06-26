package com.cognizant.ormlearn.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

/**
 * Employee — JPA Entity class
 *
 * JPA annotations — part of javax.persistence package
 * These are JPA's API — Hibernate reads and implements them
 *
 * @Entity tells JPA/Hibernate: "This class maps to a database table"
 * @Table specifies WHICH table it maps to
 */
@Entity
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "emp_id")
    private Integer id;

    @Column(name = "emp_name")
    private String name;

    @Column(name = "emp_salary")
    private Double salary;

    @Column(name = "emp_department")
    private String department;

    // Default constructor — required by JPA
    public Employee() {}

    public Employee(String name, Double salary, String department) {
        this.name       = name;
        this.salary     = salary;
        this.department = department;
    }

    // Getters and Setters
    public Integer getId()           { return id; }
    public void setId(Integer id)    { this.id = id; }

    public String getName()          { return name; }
    public void setName(String name) { this.name = name; }

    public Double getSalary()              { return salary; }
    public void setSalary(Double salary)   { this.salary = salary; }

    public String getDepartment()                  { return department; }
    public void setDepartment(String department)   { this.department = department; }

    @Override
    public String toString() {
        return "Employee{" +
               "id=" + id +
               ", name='" + name + '\'' +
               ", salary=" + salary +
               ", department='" + department + '\'' +
               '}';
    }
}
