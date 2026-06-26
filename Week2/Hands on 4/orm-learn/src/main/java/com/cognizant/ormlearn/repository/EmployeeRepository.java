package com.cognizant.ormlearn.repository;

import com.cognizant.ormlearn.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * EmployeeRepository — Spring Data JPA approach
 *
 * By extending JpaRepository<Employee, Integer>:
 * Employee = Entity type
 * Integer  = Primary key type
 *
 * Spring Data JPA AUTO-GENERATES all these methods:
 * save(employee)          → INSERT or UPDATE
 * findById(id)            → SELECT WHERE emp_id = ?
 * findAll()               → SELECT * FROM employee
 * deleteById(id)          → DELETE WHERE emp_id = ?
 * count()                 → SELECT COUNT(*)
 * existsById(id)          → SELECT 1 WHERE emp_id = ?
 *
 * You write ZERO SQL for basic CRUD — Spring generates it all
 */
@Repository
public interface EmployeeRepository
        extends JpaRepository<Employee, Integer> {

    // Spring Data JPA generates SQL from method names automatically:

    // SELECT * FROM employee WHERE emp_department = ?
    List<Employee> findByDepartment(String department);

    // SELECT * FROM employee WHERE emp_name LIKE '%name%'
    List<Employee> findByNameContaining(String name);

    // SELECT * FROM employee WHERE emp_salary > ?
    List<Employee> findBySalaryGreaterThan(Double salary);

    // SELECT * FROM employee WHERE emp_department = ?
    // AND emp_salary > ?
    List<Employee> findByDepartmentAndSalaryGreaterThan(
            String department, Double salary);

    // Custom JPQL query using @Query annotation
    // JPQL uses class names and field names, not table/column names
    @Query("SELECT e FROM Employee e WHERE e.salary = " +
           "(SELECT MAX(e2.salary) FROM Employee e2 " +
           "WHERE e2.department = :dept)")
    Employee findHighestPaidInDepartment(
            @Param("dept") String department);
}
