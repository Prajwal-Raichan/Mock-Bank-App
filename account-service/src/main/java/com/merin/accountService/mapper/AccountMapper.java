package com.merin.accountService.mapper;

import com.merin.accountService.constants.BankConstants;
import com.merin.accountService.dto.*;
import com.merin.accountService.entity.*;
import com.merin.accountService.enums.TransactionMode;
import com.merin.accountService.enums.TransactionStatus;
import com.merin.accountService.enums.TransactionType;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
public class AccountMapper {

    public SavingsAccountResponse mapAccountEntityToSavingsAccountResponse(AccountEntity accountEntity) {

        SavingsAccountResponse savingsAccountResponse = new SavingsAccountResponse();
        savingsAccountResponse.setSavingsAccountId(accountEntity.getSavingsAccountEntity().getSavingsAccountId());
        savingsAccountResponse.setSavingsAccountInterestRate(accountEntity.getSavingsAccountEntity().getSavingsAccountInterestRate());
        savingsAccountResponse.setAccountNumber(accountEntity.getAccountNumber());
        savingsAccountResponse.setAccountBankName(accountEntity.getAccountBankName());
        savingsAccountResponse.setPanNumber(accountEntity.getPanNumber());
        savingsAccountResponse.setAccountBalance(accountEntity.getAccountBalance());
        savingsAccountResponse.setAccountType(accountEntity.getAccountType());
        savingsAccountResponse.setPanNumber(accountEntity.getPanNumber());
        savingsAccountResponse.setAccountCreationDate(LocalDateTime.now());
        savingsAccountResponse.setAccountStatus(BankConstants.ACCOUNT_STATUS_NEW);
        savingsAccountResponse.setAccountHolderName(accountEntity.getAccountHolderName());
        savingsAccountResponse.setAccountBranchName(accountEntity.getAccountBranchName());
        savingsAccountResponse.setAccountBranchCode(accountEntity.getAccountBranchCode());
        savingsAccountResponse.setIfscCode(accountEntity.getIfscCode());
        savingsAccountResponse.setAccountCreationDate(accountEntity.getAccountCreationDate());
        return savingsAccountResponse;
    }


    public CurrentAccountResponse mapAccountEntityToCurrentAccountResponse(AccountEntity accountEntity) {

        CurrentAccountResponse currentAccountResponse = new CurrentAccountResponse();
        currentAccountResponse.setCurrentAccountId(accountEntity.getCurrentAccountEntity().getCurrentAccountId());
        currentAccountResponse.setCurrentAccountInterestRate(accountEntity.getCurrentAccountEntity().getCurrentAccountInterestRate());
        currentAccountResponse.setOverdraftLimit(accountEntity.getCurrentAccountEntity().getOverdraftLimit());
        currentAccountResponse.setAccountNumber(accountEntity.getAccountNumber());
        currentAccountResponse.setAccountBankName(accountEntity.getAccountBankName());
        currentAccountResponse.setPanNumber(accountEntity.getPanNumber());
        currentAccountResponse.setAccountBalance(accountEntity.getAccountBalance());
        currentAccountResponse.setAccountType(accountEntity.getAccountType());
        currentAccountResponse.setPanNumber(accountEntity.getPanNumber());
        currentAccountResponse.setAccountCreationDate(accountEntity.getAccountCreationDate());
        currentAccountResponse.setAccountStatus(accountEntity.getAccountStatus());
        currentAccountResponse.setAccountHolderName(accountEntity.getAccountHolderName());
        currentAccountResponse.setAccountBranchName(accountEntity.getAccountBranchName());
        currentAccountResponse.setAccountBranchCode(accountEntity.getAccountBranchCode());
        currentAccountResponse.setIfscCode(accountEntity.getIfscCode());
        currentAccountResponse.setAccountCreationDate(accountEntity.getAccountCreationDate());

        return currentAccountResponse;
    }


    public FixedDepositAccountResponse mapAccountEntityToFixedDepositAccountResponse(AccountEntity accountEntity) {

        FixedDepositAccountResponse fixedDepositAccountResponse = new FixedDepositAccountResponse();

        fixedDepositAccountResponse.setAccountNumber(accountEntity.getAccountNumber());
        fixedDepositAccountResponse.setAccountBankName(accountEntity.getAccountBankName());
        fixedDepositAccountResponse.setPanNumber(accountEntity.getPanNumber());
        fixedDepositAccountResponse.setAccountBalance(accountEntity.getAccountBalance());
        fixedDepositAccountResponse.setAccountType(accountEntity.getAccountType());
        fixedDepositAccountResponse.setPanNumber(accountEntity.getPanNumber());

        fixedDepositAccountResponse.setAccountCreationDate(accountEntity.getAccountCreationDate());
        fixedDepositAccountResponse.setAccountStatus(accountEntity.getAccountStatus());
        fixedDepositAccountResponse.setAccountHolderName(accountEntity.getAccountHolderName());
        fixedDepositAccountResponse.setAccountBranchName(accountEntity.getAccountBranchName());
        fixedDepositAccountResponse.setAccountBranchCode(accountEntity.getAccountBranchCode());
        fixedDepositAccountResponse.setIfscCode(accountEntity.getIfscCode());
        fixedDepositAccountResponse.setAccountCreationDate(accountEntity.getAccountCreationDate());

        fixedDepositAccountResponse.setFixedDepositAccountId(accountEntity.getFixedDepositAccountEntity().getFixedDepositAccountId());
        fixedDepositAccountResponse.setFixedDepositAmount(accountEntity.getFixedDepositAccountEntity().getFixedDepositAmount());
        fixedDepositAccountResponse.setFdMaturityAmount(accountEntity.getFixedDepositAccountEntity().getFdMaturityAmount());
        fixedDepositAccountResponse.setFdMaturityDate(accountEntity.getFixedDepositAccountEntity().getFdMaturityDate());
        fixedDepositAccountResponse.setFixedDepositInterestRate(accountEntity.getFixedDepositAccountEntity().getFixedDepositInterestRate());
        fixedDepositAccountResponse.setFdTenure(accountEntity.getFixedDepositAccountEntity().getFdTenure());
        fixedDepositAccountResponse.setFdInterestFrequency(accountEntity.getFixedDepositAccountEntity().getFdInterestFrequency());
        fixedDepositAccountResponse.setFdCreationDate(accountEntity.getFixedDepositAccountEntity().getFdCreationDate());
        fixedDepositAccountResponse.setAccountStatus(accountEntity.getFixedDepositAccountEntity().getFdAccountStatus());
        fixedDepositAccountResponse.setFdAccountStatus(accountEntity.getFixedDepositAccountEntity().getFdAccountStatus());
        fixedDepositAccountResponse.setFdLastUpdatedDate(accountEntity.getFixedDepositAccountEntity().getFdLastUpdatedDate());


        return fixedDepositAccountResponse;
    }

    public <T> AccountEntity mapBankDetailsResponseToAccountEntity(AccountEntity updateAccountEntity, double accountOpenBalance, T accountType ) {

        updateAccountEntity.setAccountBalance(accountOpenBalance);
        updateAccountEntity.setAccountBankName(BankConstants.ACCOUNT_BANK_NAME);
        updateAccountEntity.setAccountBranchName(BankConstants.ACCOUNT_BRANCH_NAME);
        updateAccountEntity.setAccountBranchCode(BankConstants.ACCOUNT_BRANCH_CODE);
        updateAccountEntity.setIfscCode(BankConstants.IFSC_CODE);
        updateAccountEntity.setAccountCreationDate(LocalDateTime.now());
        updateAccountEntity.setAccountStatus(BankConstants.ACCOUNT_STATUS_NEW);

        if (accountType instanceof SavingsAccountEntity) {
            SavingsAccountEntity savingsAccountEntity = (SavingsAccountEntity) accountType;
            savingsAccountEntity.setSavingsAccountInterestRate(BankConstants.SAVINGS_ACCOUNT_INTEREST_RATE);
            savingsAccountEntity.setAccountEntity(updateAccountEntity);
            updateAccountEntity.setSavingsAccountEntity(savingsAccountEntity);
        } else if (accountType instanceof CurrentAccountEntity) {
            CurrentAccountEntity currentAccountEntity = (CurrentAccountEntity) accountType;
            currentAccountEntity.setCurrentAccountInterestRate(BankConstants.CURRENT_ACCOUNT_INTEREST_RATE);
            currentAccountEntity.setOverdraftLimit(BankConstants.OVER_DRAFT_LIMIT);
            currentAccountEntity.setAccountEntity(updateAccountEntity);
            updateAccountEntity.setCurrentAccountEntity(currentAccountEntity);
        } else if (accountType instanceof FixedDepositAccountEntity) {
            FixedDepositAccountEntity fixedDepositAccountEntity = (FixedDepositAccountEntity) accountType;
            fixedDepositAccountEntity.setFixedDepositInterestRate(BankConstants.FIXED_DEPOSIT_INTEREST_RATE);
            fixedDepositAccountEntity.setFdAccountStatus(BankConstants.ACCOUNT_STATUS_ACTIVE);
            fixedDepositAccountEntity.setFdCreationDate(LocalDateTime.now());
            fixedDepositAccountEntity.setFdLastUpdatedDate(LocalDate.now());
            fixedDepositAccountEntity.setAccountEntity(updateAccountEntity);
            updateAccountEntity.setFixedDepositAccountEntity(fixedDepositAccountEntity);
        } else {
            throw new IllegalArgumentException("Unsupported account type: " + accountType.getClass().getName());
        }

        return updateAccountEntity;
    }

    public TransactionEntity mapTransactionParamsToTransactionEntity(int customerId, AccountEntity accountEntity, String transactionTypeParam, String transactionModeParam,
                                                                     double transactionAmount, String description) {

        TransactionEntity transactionEntityRequest = new TransactionEntity();
        TransactionType transactionType = TransactionType.fromValue(transactionTypeParam);
        TransactionMode transactionMode = TransactionMode.fromValue(transactionModeParam);

        transactionEntityRequest.setCustomerId(customerId);
        transactionEntityRequest.setAccountEntity(accountEntity);
        transactionEntityRequest.setTransactionType(transactionType);
        transactionEntityRequest.setTransactionMode(transactionMode);
        transactionEntityRequest.setTransactionAmount(transactionAmount);
        transactionEntityRequest.setTransactionDescription(description);
        transactionEntityRequest.setTransactionDate(LocalDateTime.now());
        transactionEntityRequest.setTotalAccountBalance(accountEntity.getAccountBalance());
        transactionEntityRequest.setTransactionStatus(TransactionStatus.SUCCESS);

        return transactionEntityRequest;

    }

    public TransactionResponse mapTransactionEntityToTransactionResponse(TransactionEntity transactionEntity) {

        TransactionResponse transactionResponse = new TransactionResponse();

        transactionResponse.setAccountEntity(transactionEntity.getAccountEntity());
        transactionResponse.setTransactionType(transactionEntity.getTransactionType());
        transactionResponse.setTransactionMode(transactionEntity.getTransactionMode());
        transactionResponse.setTransactionAmount(transactionEntity.getTransactionAmount());
        transactionResponse.setTransactionDescription(transactionEntity.getTransactionDescription());
        transactionResponse.setTransactionDate(LocalDateTime.now());
        transactionResponse.setTransactionStatus(transactionEntity.getTransactionStatus());
        transactionResponse.setTransactionNumber(transactionEntity.getTransactionNumber());
        transactionResponse.setTotalAccountBalance(transactionEntity.getTotalAccountBalance());

        return transactionResponse;

    }

    public AccountResponse mapAccountEntityToAccountResponse(AccountEntity accountEntity) {

        if (accountEntity == null) {
            throw new IllegalArgumentException("Account must not be null.");
        }

        return AccountResponse.builder()
                .accountNumber(accountEntity.getAccountNumber())
                .accountHolderName(accountEntity.getAccountHolderName())
                .accountBalance(accountEntity.getAccountBalance())
                .accountType(accountEntity.getAccountType())
                .build();
    }

    public AccountResponse mapAccountProjectionToAccountResponse(AccountProjection accountProjection) {

        if (accountProjection == null) {
            throw new IllegalArgumentException("Account Projection must not be null.");
        }

        return AccountResponse.builder()
                .accountNumber(accountProjection.getAccountNumber())
                .accountHolderName(accountProjection.getAccountHolderName())
                .accountBalance(accountProjection.getAccountBalance())
                .accountStatus(accountProjection.getAccountStatus())
                .accountType(accountProjection.getAccountType())
                .panNumber(accountProjection.getPanNumber())
                .build();
    }
}
