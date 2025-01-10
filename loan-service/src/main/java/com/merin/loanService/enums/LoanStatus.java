package com.merin.loanService.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum LoanStatus {

    APPROVED,
    UNAPPROVED,
    PENDING, 
    SUCCESS, 
    FAILED;

    @JsonCreator
    public static LoanStatus fromValue(String value) {
        return LoanStatus.valueOf(value.trim().toUpperCase());
    }


}
