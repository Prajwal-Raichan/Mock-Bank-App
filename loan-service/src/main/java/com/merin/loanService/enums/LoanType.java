package com.merin.loanService.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum LoanType {

    HOME_LOAN,
    CAR_LOAN,
    BIKE_LOAN,
    EDUCATIONAL_LOAN,
    PERSONAL_LOAN;

    @JsonCreator
    public static LoanType fromValue(String value) {
        return LoanType.valueOf(value.trim().toUpperCase());
    }
}
