package com.cognizant.loan;


public class LoanDetails {

    // Loan account number
    private String number;

    // Type of loan: car, home, personal etc.
    private String type;

    // Total loan amount sanctioned
    private long loan;

    // Monthly EMI (Equated Monthly Installment)
    private long emi;

    // Remaining tenure in months
    private int tenure;

   
    public LoanDetails() {
    }

    public LoanDetails(String number, String type,
                       long loan, long emi, int tenure) {
        this.number  = number;
        this.type    = type;
        this.loan    = loan;
        this.emi     = emi;
        this.tenure  = tenure;
    }

    // Getters — required for Jackson JSON serialization
    public String getNumber() { return number; }
    public String getType()   { return type; }
    public long getLoan()     { return loan; }
    public long getEmi()      { return emi; }
    public int getTenure()    { return tenure; }

    // Setters
    public void setNumber(String number)  { this.number = number; }
    public void setType(String type)      { this.type = type; }
    public void setLoan(long loan)        { this.loan = loan; }
    public void setEmi(long emi)          { this.emi = emi; }
    public void setTenure(int tenure)     { this.tenure = tenure; }

    @Override
    public String toString() {
        return "LoanDetails{" +
               "number='" + number + '\'' +
               ", type='" + type + '\'' +
               ", loan=" + loan +
               ", emi=" + emi +
               ", tenure=" + tenure +
               '}';
    }
}