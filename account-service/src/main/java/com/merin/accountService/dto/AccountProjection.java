package com.merin.accountService.dto;


public interface AccountProjection {

    String getAccountHolderName();

    String getAccountNumber();

    double getAccountBalance();

    String getAccountType();

    String getAccountStatus();

    String getPanNumber();

}
