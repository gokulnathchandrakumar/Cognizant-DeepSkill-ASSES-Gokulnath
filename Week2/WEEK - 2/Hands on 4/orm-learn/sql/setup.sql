-- ============================================================
-- MySQL Setup for Hands-on 4: JPA vs Hibernate vs Spring Data JPA
-- Run these commands in MySQL Workbench or terminal
-- ============================================================

-- Step 1: Create database schema (if not already created)
CREATE SCHEMA IF NOT EXISTS ormlearn;

-- Step 2: Use the schema
USE ormlearn;

-- Step 3: Create employee table
CREATE TABLE IF NOT EXISTS employee (
    emp_id         INT AUTO_INCREMENT PRIMARY KEY,
    emp_name       VARCHAR(100) NOT NULL,
    emp_salary     DOUBLE NOT NULL,
    emp_department VARCHAR(50) NOT NULL
);

-- Step 4: Insert sample data
INSERT INTO employee (emp_name, emp_salary, emp_department) VALUES ('John Doe', 75000.0, 'IT');
INSERT INTO employee (emp_name, emp_salary, emp_department) VALUES ('Jane Smith', 85000.0, 'Finance');
INSERT INTO employee (emp_name, emp_salary, emp_department) VALUES ('Bob Johnson', 65000.0, 'IT');
INSERT INTO employee (emp_name, emp_salary, emp_department) VALUES ('Alice Williams', 90000.0, 'HR');
INSERT INTO employee (emp_name, emp_salary, emp_department) VALUES ('Charlie Brown', 72000.0, 'Finance');

-- Step 5: Verify
SELECT * FROM employee;
