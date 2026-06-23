-- ==========================================
-- WEEK 1: PL/SQL DEVELOPER
-- Exercise 3: Stored Procedures
-- ==========================================

-- Drop existing tables to ensure a clean setup on rerun
BEGIN
    EXECUTE IMMEDIATE 'DROP TABLE Employees CASCADE CONSTRAINTS';
EXCEPTION
    WHEN OTHERS THEN
        IF SQLCODE != -942 THEN
            RAISE;
        END IF;
END;
/

BEGIN
    EXECUTE IMMEDIATE 'DROP TABLE Accounts CASCADE CONSTRAINTS';
EXCEPTION
    WHEN OTHERS THEN
        IF SQLCODE != -942 THEN
            RAISE;
        END IF;
END;
/


-- ==========================================
-- Setup: Tables & Data
-- ==========================================

CREATE TABLE Accounts (
    AccountID     INT PRIMARY KEY,
    CustomerName  VARCHAR(100),
    AccountType   VARCHAR(20),
    Balance       DECIMAL(10,2)
);

CREATE TABLE Employees (
    EmployeeID    INT PRIMARY KEY,
    EmployeeName  VARCHAR(100),
    DepartmentID  INT,
    Salary        DECIMAL(10,2)
);

INSERT INTO Accounts VALUES (1, 'John Doe',    'SAVINGS', 10000.00);
INSERT INTO Accounts VALUES (2, 'Jane Smith',  'SAVINGS',  5000.00);
INSERT INTO Accounts VALUES (3, 'Robert Brown','CURRENT',  8000.00);
INSERT INTO Accounts VALUES (4, 'Emily Davis', 'SAVINGS', 15000.00);
INSERT INTO Accounts VALUES (5, 'Michael Lee', 'SAVINGS',  3000.00);

INSERT INTO Employees VALUES (1, 'Alice Johnson', 10, 50000.00);
INSERT INTO Employees VALUES (2, 'Bob Williams',  10, 45000.00);
INSERT INTO Employees VALUES (3, 'Carol Martinez',20, 60000.00);
INSERT INTO Employees VALUES (4, 'David Wilson',  10, 55000.00);
INSERT INTO Employees VALUES (5, 'Eve Thompson',  20, 48000.00);

COMMIT;


-- ==========================================
-- Scenario 1: ProcessMonthlyInterest
-- ==========================================
CREATE OR REPLACE PROCEDURE ProcessMonthlyInterest
AS
    v_interest      DECIMAL(10,2);
    v_new_balance   DECIMAL(10,2);
    v_count         INT := 0;
BEGIN
    DBMS_OUTPUT.PUT_LINE('===== Monthly Interest Processing =====');
    DBMS_OUTPUT.PUT_LINE('Date: ' || TO_CHAR(SYSDATE, 'DD-MON-YYYY'));
    DBMS_OUTPUT.PUT_LINE('Interest Rate Applied: 1%');
    DBMS_OUTPUT.PUT_LINE('---------------------------------------');

    FOR acc IN (
        SELECT AccountID, CustomerName, Balance
        FROM Accounts
        WHERE AccountType = 'SAVINGS'
        ORDER BY AccountID
    )
    LOOP
        v_interest    := ROUND(acc.Balance * 0.01, 2);
        v_new_balance := acc.Balance + v_interest;

        UPDATE Accounts
        SET Balance = v_new_balance
        WHERE AccountID = acc.AccountID;

        DBMS_OUTPUT.PUT_LINE(
            'Account ID: '     || acc.AccountID      ||
            ' | Customer: '    || acc.CustomerName   ||
            ' | Old Balance: $'|| acc.Balance        ||
            ' | Interest: $'   || v_interest         ||
            ' | New Balance: $'|| v_new_balance
        );

        v_count := v_count + 1;
    END LOOP;

    COMMIT;
    DBMS_OUTPUT.PUT_LINE('---------------------------------------');
    DBMS_OUTPUT.PUT_LINE('Accounts Processed: ' || v_count);
    DBMS_OUTPUT.PUT_LINE('===== Processing Complete =====');

EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('ERROR: ' || SQLERRM);
        DBMS_OUTPUT.PUT_LINE('All changes rolled back.');
END ProcessMonthlyInterest;
/

-- Execute Scenario 1
SET SERVEROUTPUT ON;

BEGIN
    ProcessMonthlyInterest;
END;
/


-- ==========================================
-- Scenario 2: UpdateEmployeeBonus
-- ==========================================
CREATE OR REPLACE PROCEDURE UpdateEmployeeBonus(
    p_department_id  IN INT,
    p_bonus_percent  IN DECIMAL
)
AS
    v_bonus_amount  DECIMAL(10,2);
    v_new_salary    DECIMAL(10,2);
    v_count         INT := 0;
BEGIN
    DBMS_OUTPUT.PUT_LINE('===== Employee Bonus Processing =====');
    DBMS_OUTPUT.PUT_LINE('Department  : ' || p_department_id);
    DBMS_OUTPUT.PUT_LINE('Bonus       : ' || p_bonus_percent || '%');
    DBMS_OUTPUT.PUT_LINE('-------------------------------------');

    -- Input validation
    IF p_bonus_percent <= 0 THEN
        DBMS_OUTPUT.PUT_LINE('ERROR: Bonus % must be greater than 0.');
        RETURN;
    END IF;

    FOR emp IN (
        SELECT EmployeeID, EmployeeName, Salary
        FROM Employees
        WHERE DepartmentID = p_department_id
        ORDER BY EmployeeID
    )
    LOOP
        v_bonus_amount := ROUND(emp.Salary * (p_bonus_percent / 100), 2);
        v_new_salary   := emp.Salary + v_bonus_amount;

        UPDATE Employees
        SET Salary = v_new_salary
        WHERE EmployeeID = emp.EmployeeID;

        DBMS_OUTPUT.PUT_LINE(
            'Employee: '        || emp.EmployeeName  ||
            ' | Old Salary: $'  || emp.Salary        ||
            ' | Bonus: $'       || v_bonus_amount    ||
            ' | New Salary: $'  || v_new_salary
        );

        v_count := v_count + 1;
    END LOOP;

    IF v_count = 0 THEN
        DBMS_OUTPUT.PUT_LINE(
            'WARNING: No employees found in Department ' 
            || p_department_id
        );
        RETURN;
    END IF;

    COMMIT;
    DBMS_OUTPUT.PUT_LINE('-------------------------------------');
    DBMS_OUTPUT.PUT_LINE('Employees Updated: ' || v_count);
    DBMS_OUTPUT.PUT_LINE('===== Bonus Processing Complete =====');

EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('ERROR: ' || SQLERRM);
        DBMS_OUTPUT.PUT_LINE('All changes rolled back.');
END UpdateEmployeeBonus;
/

-- Execute Scenario 2 Tests
SET SERVEROUTPUT ON;

-- Apply 10% bonus to Department 10
BEGIN
    UpdateEmployeeBonus(10, 10);
END;
/

-- Apply 15% bonus to Department 20
BEGIN
    UpdateEmployeeBonus(20, 15);
END;
/

-- Test: Invalid bonus
BEGIN
    UpdateEmployeeBonus(10, -5);
END;
/

-- Test: Non-existent department
BEGIN
    UpdateEmployeeBonus(99, 10);
END;
/


-- ==========================================
-- Scenario 3: TransferFunds
-- ==========================================
CREATE OR REPLACE PROCEDURE TransferFunds(
    p_from_account  IN INT,
    p_to_account    IN INT,
    p_amount        IN DECIMAL
)
AS
    v_from_balance  DECIMAL(10,2);
    v_to_balance    DECIMAL(10,2);
    v_from_name     VARCHAR(100);
    v_to_name       VARCHAR(100);

    e_insufficient_funds  EXCEPTION;
    e_invalid_account     EXCEPTION;
    e_invalid_amount      EXCEPTION;
BEGIN
    DBMS_OUTPUT.PUT_LINE('===== Fund Transfer Processing =====');
    DBMS_OUTPUT.PUT_LINE('From Account : ' || p_from_account);
    DBMS_OUTPUT.PUT_LINE('To Account   : ' || p_to_account);
    DBMS_OUTPUT.PUT_LINE('Amount       : $' || p_amount);
    DBMS_OUTPUT.PUT_LINE('------------------------------------');

    -- Validation 1: Amount must be positive
    IF p_amount <= 0 THEN
        RAISE e_invalid_amount;
    END IF;

    -- Validation 2: Source account must exist
    BEGIN
        SELECT Balance, CustomerName
        INTO v_from_balance, v_from_name
        FROM Accounts
        WHERE AccountID = p_from_account;
    EXCEPTION
        WHEN NO_DATA_FOUND THEN
            RAISE e_invalid_account;
    END;

    -- Validation 3: Destination account must exist
    BEGIN
        SELECT Balance, CustomerName
        INTO v_to_balance, v_to_name
        FROM Accounts
        WHERE AccountID = p_to_account;
    EXCEPTION
        WHEN NO_DATA_FOUND THEN
            RAISE e_invalid_account;
    END;

    -- Validation 4: Sufficient balance check
    IF v_from_balance < p_amount THEN
        RAISE e_insufficient_funds;
    END IF;

    -- Deduct from source
    UPDATE Accounts
    SET Balance = Balance - p_amount
    WHERE AccountID = p_from_account;

    -- Credit to destination
    UPDATE Accounts
    SET Balance = Balance + p_amount
    WHERE AccountID = p_to_account;

    COMMIT;

    DBMS_OUTPUT.PUT_LINE('*** Transfer Successful! ***');
    DBMS_OUTPUT.PUT_LINE(
        v_from_name || ' (Acc: ' || p_from_account || ')' ||
        ' | Old: $' || v_from_balance ||
        ' | New: $' || (v_from_balance - p_amount)
    );
    DBMS_OUTPUT.PUT_LINE(
        v_to_name || ' (Acc: ' || p_to_account || ')' ||
        ' | Old: $' || v_to_balance ||
        ' | New: $' || (v_to_balance + p_amount)
    );
    DBMS_OUTPUT.PUT_LINE('===== Transfer Complete =====');

EXCEPTION
    WHEN e_insufficient_funds THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE(
            'FAILED: Insufficient funds.' ||
            ' Available: $' || v_from_balance ||
            ' | Required: $' || p_amount
        );
    WHEN e_invalid_account THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE(
            'FAILED: Invalid account ID provided.'
        );
    WHEN e_invalid_amount THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE(
            'FAILED: Amount must be greater than $0.'
        );
    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('UNEXPECTED ERROR: ' || SQLERRM);
END TransferFunds;
/

-- Execute Scenario 3 Tests
SET SERVEROUTPUT ON;

-- Test 1: Valid transfer
BEGIN
    TransferFunds(1, 2, 2000);
END;
/

-- Test 2: Insufficient balance
BEGIN
    TransferFunds(5, 1, 9999);
END;
/

-- Test 3: Invalid account
BEGIN
    TransferFunds(1, 99, 500);
END;
/

-- Test 4: Invalid amount
BEGIN
    TransferFunds(1, 2, -100);
END;
/
