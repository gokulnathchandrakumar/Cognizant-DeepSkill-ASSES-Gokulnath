-- ==========================================
-- WEEK 1: PL/SQL DEVELOPER
-- Exercise 1: Control Structures
-- ==========================================

-- Drop existing tables to ensure a clean setup on rerun
BEGIN
    EXECUTE IMMEDIATE 'DROP TABLE Loans CASCADE CONSTRAINTS';
EXCEPTION
    WHEN OTHERS THEN
        IF SQLCODE != -942 THEN
            RAISE;
        END IF;
END;
/

BEGIN
    EXECUTE IMMEDIATE 'DROP TABLE Customers CASCADE CONSTRAINTS';
EXCEPTION
    WHEN OTHERS THEN
        IF SQLCODE != -942 THEN
            RAISE;
        END IF;
END;
/

-- ==========================================
-- Setup: Sample Tables & Data
-- ==========================================

-- Customers table
CREATE TABLE Customers (
    CustomerID    INT PRIMARY KEY,
    CustomerName  VARCHAR(100),
    Age           INT,
    Balance       DECIMAL(10,2),
    IsVIP         CHAR(5) DEFAULT 'FALSE'
);

-- Loans table
CREATE TABLE Loans (
    LoanID        INT PRIMARY KEY,
    CustomerID    INT REFERENCES Customers(CustomerID),
    InterestRate  DECIMAL(5,2),
    DueDate       DATE
);

-- Sample customers
INSERT INTO Customers VALUES (1, 'John Doe',    65, 15000.00, 'FALSE');
INSERT INTO Customers VALUES (2, 'Jane Smith',  45,  8000.00, 'FALSE');
INSERT INTO Customers VALUES (3, 'Robert Brown',70,  5000.00, 'FALSE');
INSERT INTO Customers VALUES (4, 'Emily Davis', 30, 12000.00, 'FALSE');
INSERT INTO Customers VALUES (5, 'Michael Lee', 62,  3000.00, 'FALSE');

-- Sample loans
INSERT INTO Loans VALUES (101, 1, 8.50, SYSDATE + 10);
INSERT INTO Loans VALUES (102, 2, 9.00, SYSDATE + 45);
INSERT INTO Loans VALUES (103, 3, 7.75, SYSDATE + 20);
INSERT INTO Loans VALUES (104, 4, 8.00, SYSDATE + 5);
INSERT INTO Loans VALUES (105, 5, 9.50, SYSDATE + 28);

COMMIT;


-- ==========================================
-- Scenario 1: Apply 1% Discount to Customers Above 60
-- ==========================================
SET SERVEROUTPUT ON;

DECLARE
    v_new_rate DECIMAL(5,2);
BEGIN
    DBMS_OUTPUT.PUT_LINE('===== Loan Discount Processing =====');

    -- Loop through every customer
    FOR cust IN (SELECT CustomerID, CustomerName, Age FROM Customers)
    LOOP
        -- Check if customer is above 60
        IF cust.Age > 60 THEN

            -- Apply 1% discount to their loan interest rate
            UPDATE Loans
            SET InterestRate = InterestRate - 1
            WHERE CustomerID = cust.CustomerID;

            -- Fetch the new rate for display
            SELECT InterestRate
            INTO v_new_rate
            FROM Loans
            WHERE CustomerID = cust.CustomerID;

            DBMS_OUTPUT.PUT_LINE(
                'Customer: ' || cust.CustomerName ||
                ' | Age: '   || cust.Age ||
                ' | New Interest Rate: ' || v_new_rate || '%'
            );

        ELSE
            DBMS_OUTPUT.PUT_LINE(
                'Customer: ' || cust.CustomerName ||
                ' | Age: '   || cust.Age ||
                ' | No discount applied.'
            );
        END IF;
    END LOOP;

    COMMIT;
    DBMS_OUTPUT.PUT_LINE('===== Processing Complete =====');

EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('Error in Scenario 1: ' || SQLERRM);
END;
/


-- ==========================================
-- Scenario 2: Set IsVIP Flag for Balance Over $10,000
-- ==========================================
SET SERVEROUTPUT ON;

BEGIN
    DBMS_OUTPUT.PUT_LINE('===== VIP Status Processing =====');

    FOR cust IN (SELECT CustomerID, CustomerName, Balance FROM Customers)
    LOOP
        IF cust.Balance > 10000 THEN

            -- Promote to VIP
            UPDATE Customers
            SET IsVIP = 'TRUE'
            WHERE CustomerID = cust.CustomerID;

            DBMS_OUTPUT.PUT_LINE(
                'Customer: ' || cust.CustomerName ||
                ' | Balance: $' || cust.Balance ||
                ' | Status: *** VIP PROMOTED ***'
            );

        ELSE
            -- Ensure non-VIP is explicitly marked
            UPDATE Customers
            SET IsVIP = 'FALSE'
            WHERE CustomerID = cust.CustomerID;

            DBMS_OUTPUT.PUT_LINE(
                'Customer: ' || cust.CustomerName ||
                ' | Balance: $' || cust.Balance ||
                ' | Status: Not eligible for VIP'
            );

        END IF;
    END LOOP;

    COMMIT;
    DBMS_OUTPUT.PUT_LINE('===== VIP Processing Complete =====');

EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('Error in Scenario 2: ' || SQLERRM);
END;
/


-- ==========================================
-- Scenario 3: Send Reminders for Loans Due Within 30 Days
-- ==========================================
SET SERVEROUTPUT ON;

DECLARE
    v_days_remaining INT;
BEGIN
    DBMS_OUTPUT.PUT_LINE('===== Loan Due Date Reminder System =====');
    DBMS_OUTPUT.PUT_LINE('Processing date: ' || TO_CHAR(SYSDATE, 'DD-MON-YYYY'));
    DBMS_OUTPUT.PUT_LINE('=========================================');

    -- Cursor fetches loans due within the next 30 days
    FOR loan_rec IN (
        SELECT
            l.LoanID,
            l.DueDate,
            l.InterestRate,
            c.CustomerName,
            c.CustomerID,
            (l.DueDate - SYSDATE) AS DaysRemaining
        FROM Loans l
        JOIN Customers c ON l.CustomerID = c.CustomerID
        WHERE l.DueDate BETWEEN SYSDATE AND SYSDATE + 30
        ORDER BY l.DueDate ASC
    )
    LOOP
        v_days_remaining := ROUND(loan_rec.DaysRemaining);

        -- Urgency-based messaging using IF/ELSIF
        IF v_days_remaining <= 7 THEN
            DBMS_OUTPUT.PUT_LINE(
                '*** URGENT REMINDER ***' ||
                ' Customer: ' || loan_rec.CustomerName ||
                ' | Loan ID: ' || loan_rec.LoanID ||
                ' | Due: ' || TO_CHAR(loan_rec.DueDate, 'DD-MON-YYYY') ||
                ' | Days Left: ' || v_days_remaining ||
                ' | Rate: ' || loan_rec.InterestRate || '%'
            );

        ELSIF v_days_remaining <= 15 THEN
            DBMS_OUTPUT.PUT_LINE(
                '** REMINDER **' ||
                ' Customer: ' || loan_rec.CustomerName ||
                ' | Loan ID: ' || loan_rec.LoanID ||
                ' | Due: ' || TO_CHAR(loan_rec.DueDate, 'DD-MON-YYYY') ||
                ' | Days Left: ' || v_days_remaining ||
                ' | Rate: ' || loan_rec.InterestRate || '%'
            );

        ELSE
            DBMS_OUTPUT.PUT_LINE(
                '* UPCOMING REMINDER *' ||
                ' Customer: ' || loan_rec.CustomerName ||
                ' | Loan ID: ' || loan_rec.LoanID ||
                ' | Due: ' || TO_CHAR(loan_rec.DueDate, 'DD-MON-YYYY') ||
                ' | Days Left: ' || v_days_remaining ||
                ' | Rate: ' || loan_rec.InterestRate || '%'
            );
        END IF;

    END LOOP;

    DBMS_OUTPUT.PUT_LINE('=========================================');
    DBMS_OUTPUT.PUT_LINE('===== Reminder Processing Complete =====');

EXCEPTION
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Error in Scenario 3: ' || SQLERRM);
END;
/
