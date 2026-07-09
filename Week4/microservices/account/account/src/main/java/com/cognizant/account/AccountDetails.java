package com.cognizant.account;


public class AccountDetails {

    // Account number — String to preserve leading zeros
    private String number;

    // Account type: savings, current, salary etc.
    private String type;

    // Current account balance
    private long balance;

  
    public AccountDetails() {
    }

 
    public AccountDetails(String number, String type, long balance) {
        this.number  = number;
        this.type    = type;
        this.balance = balance;
    }

    // Getters — required by Jackson for JSON serialization
    public String getNumber() {
        return number;
    }

    public String getType() {
        return type;
    }

    public long getBalance() {
        return balance;
    }

    // Setters
    public void setNumber(String number) {
        this.number = number;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "AccountDetails{" +
               "number='" + number + '\'' +
               ", type='" + type + '\'' +
               ", balance=" + balance +
               '}';
    }
}