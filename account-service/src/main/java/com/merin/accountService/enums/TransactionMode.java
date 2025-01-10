package com.merin.accountService.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum TransactionMode {
    ONLINE,
    OFFLINE,
    ATM,
    CDM,
    CHEQUE,
    UPI,
    NET_BANKING,
    DD,
    NEFT,
    RTM;

    @JsonCreator
    public static TransactionMode fromValue(String value) {
        return TransactionMode.valueOf(value.trim().toUpperCase());
    }
}
