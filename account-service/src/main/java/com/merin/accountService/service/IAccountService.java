package com.merin.accountService.service;

import com.merin.accountService.dto.*;
import com.merin.accountService.entity.AccountEntity;

public interface IAccountService {

    SavingsAccountResponse addNewSavingAccount(AccountEntity accountEntityRequest, int customerId, double accountOpenBalance);

    CurrentAccountResponse addNewCurrentAccount(AccountEntity accountEntityRequest, int customerId, double accountOpenBalance);

    FixedDepositAccountResponse addNewFixedDepositAccount(AccountEntity accountEntityRequest, int customerId, double accountOpenBalance);

    TransactionResponse processCreditTransaction(int customerId, String accountNumber,String transactionType, String transactionMode,
                                                 int paymentId, double transactionAmount, String description);

    TransactionResponse processDebitTransaction(int customerId, String accountNumber, String transactionType, String transactionMode,
                                                int paymentId, double transactionAmount, String description);

    AccountResponse getAccountBalanceByCustomerId(Integer customerId, String accountType);

    AccountResponse getAccDetailsForundTransferService(int customerId, String accountNumber);
}
