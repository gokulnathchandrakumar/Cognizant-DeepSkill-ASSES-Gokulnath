package com.cognizant.ormlearn.hibernate;

import com.cognizant.ormlearn.model.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import java.util.List;

/**
 * EmployeeDAOHibernate
 * Pure Hibernate approach — WITHOUT Spring Data JPA
 *
 * Notice how much boilerplate code is needed:
 * - Manually open session
 * - Manually begin transaction
 * - Manually commit
 * - Manually handle exceptions and rollback
 * - Manually close session
 * ALL of this repeats for EVERY single database operation
 */
public class EmployeeDAOHibernate {

    private static SessionFactory factory;

    static {
        // Build SessionFactory from hibernate.cfg.xml
        // This is the Hibernate-specific configuration
        factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Employee.class)
                .buildSessionFactory();
    }

    // ============================================================
    // CREATE — Add new employee
    // ============================================================
    public Integer addEmployee(Employee employee) {
        Session session     = factory.openSession();
        Transaction tx      = null;
        Integer employeeID  = null;

        try {
            tx = session.beginTransaction();            // manual begin
            employeeID = (Integer) session.save(employee); // save
            tx.commit();                               // manual commit
            System.out.println("Employee saved with ID: " + employeeID);

        } catch (Exception e) {
            if (tx != null) tx.rollback();             // manual rollback
            e.printStackTrace();
        } finally {
            session.close();                           // manual close
        }
        return employeeID;
    }

    // ============================================================
    // READ — Get employee by ID
    // ============================================================
    public Employee getEmployeeById(Integer id) {
        Session session = factory.openSession();
        Transaction tx  = null;
        Employee employee = null;

        try {
            tx = session.beginTransaction();
            // HQL (Hibernate Query Language) — object-oriented SQL
            employee = session.get(Employee.class, id);
            tx.commit();

        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return employee;
    }

    // ============================================================
    // READ ALL — Get all employees
    // ============================================================
    public List<Employee> getAllEmployees() {
        Session session = factory.openSession();
        Transaction tx  = null;
        List<Employee> employees = null;

        try {
            tx = session.beginTransaction();
            // HQL query — "FROM Employee" maps to SELECT * FROM employee
            employees = session.createQuery(
                    "FROM Employee", Employee.class).list();
            tx.commit();

        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return employees;
    }

    // ============================================================
    // UPDATE — Update employee salary
    // ============================================================
    public void updateEmployee(Integer id, Double newSalary) {
        Session session = factory.openSession();
        Transaction tx  = null;

        try {
            tx = session.beginTransaction();
            Employee employee = session.get(Employee.class, id);
            if (employee != null) {
                employee.setSalary(newSalary);
                session.update(employee);
            }
            tx.commit();

        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    // ============================================================
    // DELETE — Delete employee by ID
    // ============================================================
    public void deleteEmployee(Integer id) {
        Session session = factory.openSession();
        Transaction tx  = null;

        try {
            tx = session.beginTransaction();
            Employee employee = session.get(Employee.class, id);
            if (employee != null) {
                session.delete(employee);
            }
            tx.commit();

        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}
