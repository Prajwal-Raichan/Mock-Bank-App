package com.merin.fundTransferService.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum TransferMode {
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
    public static TransferMode fromValue(String value) {
        return TransferMode.valueOf(value.trim().toUpperCase());
    }
}
