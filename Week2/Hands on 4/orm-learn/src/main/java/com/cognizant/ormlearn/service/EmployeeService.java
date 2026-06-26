package com.cognizant.ormlearn.service;

import com.cognizant.ormlearn.model.Employee;
import com.cognizant.ormlearn.repository.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * EmployeeService — Spring Data JPA approach
 *
 * Compare this to EmployeeDAOHibernate:
 * - No SessionFactory
 * - No openSession()
 * - No beginTransaction()
 * - No commit() / rollback()
 * - No session.close()
 *
 * @Transactional handles ALL of that automatically
 */
@Service
public class EmployeeService {

    private static final Logger LOGGER =
            LoggerFactory.getLogger(EmployeeService.class);

    // Spring injects EmployeeRepository automatically
    @Autowired
    private EmployeeRepository employeeRepository;

    // ============================================================
    // CREATE
    // ============================================================
    @Transactional
    public void addEmployee(Employee employee) {
        LOGGER.debug("Start addEmployee, employee={}", employee);
        // One line vs 20 lines in pure Hibernate
        employeeRepository.save(employee);
        LOGGER.debug("Employee saved successfully");
    }

    // ============================================================
    // READ — Get by ID
    // ============================================================
    @Transactional
    public Employee getEmployeeById(Integer id) {
        LOGGER.debug("Start getEmployeeById, id={}", id);
        Optional<Employee> employee = employeeRepository.findById(id);
        return employee.orElse(null);
    }

    // ============================================================
    // READ ALL
    // ============================================================
    @Transactional
    public List<Employee> getAllEmployees() {
        LOGGER.debug("Start getAllEmployees");
        List<Employee> employees = employeeRepository.findAll();
        LOGGER.debug("getAllEmployees result={}", employees);
        return employees;
    }

    // ============================================================
    // UPDATE
    // ============================================================
    @Transactional
    public void updateEmployeeSalary(Integer id, Double newSalary) {
        LOGGER.debug("Start updateEmployeeSalary, id={}", id);
        Optional<Employee> existing = employeeRepository.findById(id);
        existing.ifPresent(emp -> {
            emp.setSalary(newSalary);
            employeeRepository.save(emp); // save() does UPDATE if ID exists
            LOGGER.debug("Updated salary for: {}", emp);
        });
    }

    // ============================================================
    // DELETE
    // ============================================================
    @Transactional
    public void deleteEmployee(Integer id) {
        LOGGER.debug("Start deleteEmployee, id={}", id);
        employeeRepository.deleteById(id);
        LOGGER.debug("Employee deleted, id={}", id);
    }

    // ============================================================
    // CUSTOM QUERIES
    // ============================================================
    @Transactional
    public List<Employee> getEmployeesByDepartment(String dept) {
        LOGGER.debug("Start getEmployeesByDepartment, dept={}", dept);
        return employeeRepository.findByDepartment(dept);
    }

    @Transactional
    public List<Employee> getHighEarners(Double threshold) {
        LOGGER.debug("Start getHighEarners, threshold={}", threshold);
        return employeeRepository.findBySalaryGreaterThan(threshold);
    }
}
