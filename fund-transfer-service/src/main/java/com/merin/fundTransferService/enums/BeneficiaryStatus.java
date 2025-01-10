package com.merin.fundTransferService.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum BeneficiaryStatus {

    PENDING, 
    APPROVED,
    REJECTED, UNAUTHORIZED, AUTHORIZED;

    @JsonCreator
    public static BeneficiaryStatus fromValue(String value) {
        return BeneficiaryStatus.valueOf(value.trim().toUpperCase());
    }


}
