package com.merin.loanService.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum TransactionType {

    CREDIT,
    DEBIT;

    @JsonCreator
    public static TransactionType fromValue(String value) {
        return TransactionType.valueOf(value.trim().toUpperCase());
    }
}