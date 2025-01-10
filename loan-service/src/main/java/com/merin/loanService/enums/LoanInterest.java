package com.merin.loanService.enums;

public enum LoanInterest {

    HOME_LOAN(8.50),
    CAR_LOAN(10.00),
    BIKE_LOAN(12.00),
    EDUCATIONAL_LOAN(9.50),
    PERSONAL_LOAN(15.00);

    private final double interestRate;


    LoanInterest(double interestRate) {
        this.interestRate = interestRate;
    }

    public double getInterestRate() {
        return interestRate;
    }

}
