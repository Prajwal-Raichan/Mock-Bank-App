package com.merin.fundTransferService.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum TransferStatus {
    PENDING,
    COMPLETED,
    FAILED,
    CANCELED;

    @JsonCreator
    public static BeneficiaryStatus fromValue(String value) {
        return BeneficiaryStatus.valueOf(value.trim().toUpperCase());
    }
}
