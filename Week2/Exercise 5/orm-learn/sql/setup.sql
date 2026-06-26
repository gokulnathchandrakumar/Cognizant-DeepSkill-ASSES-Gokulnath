-- ============================================================
-- MySQL Setup for ORM Learn Exercise
-- Run these commands in MySQL Workbench or terminal
-- ============================================================

-- Step 1: Create database schema
CREATE SCHEMA ormlearn;

-- Step 2: Use the schema
USE ormlearn;

-- Step 3: Create country table
-- co_code = 2-char country code (primary key)
-- co_name = full country name
CREATE TABLE country (
    co_code VARCHAR(2)  PRIMARY KEY,
    co_name VARCHAR(50) NOT NULL
);

-- Step 4: Insert sample data
INSERT INTO country VALUES ('IN', 'India');
INSERT INTO country VALUES ('US', 'United States of America');
INSERT INTO country VALUES ('UK', 'United Kingdom');
INSERT INTO country VALUES ('AU', 'Australia');
INSERT INTO country VALUES ('CA', 'Canada');

-- Step 5: Verify
SELECT * FROM country;
