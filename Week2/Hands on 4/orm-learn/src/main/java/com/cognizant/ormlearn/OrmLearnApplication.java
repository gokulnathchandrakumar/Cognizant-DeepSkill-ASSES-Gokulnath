package com.cognizant.ormlearn;

import com.cognizant.ormlearn.model.Employee;
import com.cognizant.ormlearn.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.List;

/**
 * OrmLearnApplication — Main entry point
 *
 * Demonstrates Spring Data JPA approach vs Pure Hibernate
 * All operations use the clean Spring Data JPA way
 *
 * @SpringBootApplication is a shortcut for THREE annotations:
 * 1. @Configuration     — this class can define @Bean methods
 * 2. @EnableAutoConfiguration — Spring Boot auto-configures
 *                               DataSource, JPA, Hibernate etc.
 * 3. @ComponentScan     — scans com.cognizant.ormlearn package
 */
@SpringBootApplication
public class OrmLearnApplication {

    private static final Logger LOGGER =
            LoggerFactory.getLogger(OrmLearnApplication.class);

    private static EmployeeService employeeService;

    public static void main(String[] args) {

        ApplicationContext context =
                SpringApplication.run(OrmLearnApplication.class, args);

        employeeService = context.getBean(EmployeeService.class);

        LOGGER.info("Inside main");

        // Run all test methods
        testAddEmployees();
        testGetAllEmployees();
        testGetEmployeeById();
        testGetByDepartment();
        testGetHighEarners();
        testUpdateSalary();
        testDeleteEmployee();
        testFinalState();
    }

    // ============================================================
    // Test: Add Employees
    // ============================================================
    private static void testAddEmployees() {
        LOGGER.info("Start testAddEmployees");
        System.out.println("\n--- Test: Add Employees ---");

        employeeService.addEmployee(
                new Employee("John Doe", 75000.0, "IT"));
        System.out.println("Employee added: John Doe");

        employeeService.addEmployee(
                new Employee("Jane Smith", 85000.0, "Finance"));
        System.out.println("Employee added: Jane Smith");

        employeeService.addEmployee(
                new Employee("Bob Johnson", 65000.0, "IT"));
        System.out.println("Employee added: Bob Johnson");

        employeeService.addEmployee(
                new Employee("Alice Williams", 90000.0, "HR"));
        System.out.println("Employee added: Alice Williams");

        employeeService.addEmployee(
                new Employee("Charlie Brown", 72000.0, "Finance"));
        System.out.println("Employee added: Charlie Brown");

        LOGGER.info("End testAddEmployees");
    }

    // ============================================================
    // Test: Get All Employees
    // ============================================================
    private static void testGetAllEmployees() {
        LOGGER.info("Start testGetAllEmployees");
        System.out.println("\n--- Test: Get All Employees ---");

        List<Employee> employees = employeeService.getAllEmployees();
        employees.forEach(emp ->
            System.out.printf("  [%d] %-20s | %-10s | $%.2f%n",
                emp.getId(), emp.getName(),
                emp.getDepartment(), emp.getSalary())
        );
        System.out.println("  Total: " + employees.size() + " employees");

        LOGGER.info("End testGetAllEmployees");
    }

    // ============================================================
    // Test: Get Employee by ID
    // ============================================================
    private static void testGetEmployeeById() {
        LOGGER.info("Start testGetEmployeeById");
        System.out.println("\n--- Test: Get Employee by ID ---");

        Employee emp = employeeService.getEmployeeById(1);
        if (emp != null) {
            System.out.println("  Found: " + emp);
        } else {
            System.out.println("  Not found");
        }

        LOGGER.info("End testGetEmployeeById");
    }

    // ============================================================
    // Test: Get Employees by Department
    // ============================================================
    private static void testGetByDepartment() {
        LOGGER.info("Start testGetByDepartment");
        System.out.println("\n--- Test: Get By Department (IT) ---");

        List<Employee> itEmployees =
                employeeService.getEmployeesByDepartment("IT");
        itEmployees.forEach(emp ->
            System.out.println("  " + emp)
        );

        LOGGER.info("End testGetByDepartment");
    }

    // ============================================================
    // Test: Get High Earners
    // ============================================================
    private static void testGetHighEarners() {
        LOGGER.info("Start testGetHighEarners");
        System.out.println("\n--- Test: High Earners (salary > $70,000) ---");

        List<Employee> highEarners =
                employeeService.getHighEarners(70000.0);
        highEarners.forEach(emp ->
            System.out.printf("  %-20s | $%.2f%n",
                emp.getName(), emp.getSalary())
        );

        LOGGER.info("End testGetHighEarners");
    }

    // ============================================================
    // Test: Update Salary
    // ============================================================
    private static void testUpdateSalary() {
        LOGGER.info("Start testUpdateSalary");
        System.out.println("\n--- Test: Update Salary ---");

        employeeService.updateEmployeeSalary(1, 80000.0);
        Employee updated = employeeService.getEmployeeById(1);
        System.out.println("  Updated: " + updated);

        LOGGER.info("End testUpdateSalary");
    }

    // ============================================================
    // Test: Delete Employee
    // ============================================================
    private static void testDeleteEmployee() {
        LOGGER.info("Start testDeleteEmployee");
        System.out.println("\n--- Test: Delete Employee ---");

        employeeService.deleteEmployee(5);
        System.out.println("  Employee ID 5 deleted successfully");

        LOGGER.info("End testDeleteEmployee");
    }

    // ============================================================
    // Test: Final State
    // ============================================================
    private static void testFinalState() {
        LOGGER.info("Start testFinalState");
        System.out.println("\n--- Final State: All Employees ---");

        List<Employee> employees = employeeService.getAllEmployees();
        employees.forEach(emp ->
            System.out.printf("  [%d] %-20s | %-10s | $%.2f%n",
                emp.getId(), emp.getName(),
                emp.getDepartment(), emp.getSalary())
        );
        System.out.println("  Total: " + employees.size() + " employees");

        LOGGER.info("End testFinalState");
    }
}
