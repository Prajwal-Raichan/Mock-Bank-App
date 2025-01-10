package com.merin.loanService.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum TransactionStatus {

    PENDING, 
    SUCCESS, 
    FAILED;

    @JsonCreator
    public static TransactionStatus fromValue(String value) {
        return TransactionStatus.valueOf(value.trim().toUpperCase());
    }


}
