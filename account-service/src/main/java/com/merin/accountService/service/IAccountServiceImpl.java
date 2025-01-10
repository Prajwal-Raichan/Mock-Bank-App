package com.merin.accountService.service;

import com.merin.accountService.client.feignClient.CustomerFeignClient;
import com.merin.accountService.client.feignClient.IPaymentServiceFeignClient;
import com.merin.accountService.constants.AccountConstants;
import com.merin.accountService.constants.BankConstants;
import com.merin.accountService.dto.*;
import com.merin.accountService.entity.*;
import com.merin.accountService.mapper.AccountMapper;
import com.merin.accountService.repository.IAccountRepository;
import com.merin.accountService.repository.ITransactionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.Year;

@Slf4j
@Service
public class IAccountServiceImpl implements IAccountService {

    @Autowired
    private IAccountRepository accountRepository;

    @Autowired
    private ITransactionRepository transactionRepository;

    @Autowired
    private CustomerFeignClient customerFeignClient;

    @Autowired
    private AccountMapper accountMapper;

    @Autowired
    private IPaymentServiceFeignClient paymentServiceFeignClient;

    @Transactional
    @Override
    public SavingsAccountResponse addNewSavingAccount(AccountEntity accountEntityRequest, int customerId, double accountOpenBalance) {

        log.info(AccountConstants.START_SERVICE, "addNewSavingAccount()");
        AccountEntity accountEntity = new AccountEntity();
        SavingsAccountEntity savingsAccountEntity = new SavingsAccountEntity();
        String customerAccountNumber = null;
        SavingsAccountResponse savingsAccountResponse = new SavingsAccountResponse();
        CustomerDetailsResponse customerDetailsResponse = new CustomerDetailsResponse();

        accountEntityRequest.setCustomerId(customerId);
        accountEntityRequest.setAccountType(AccountConstants.SAVINGS_ACCOUNT);

        log.info("Making Customer Client Call for Customer Details");
        customerDetailsResponse = customerFeignClient.getCustomerDetailsForAccountService(customerId);
        log.info("Customer Client Call passed, successfully fetched customer information");
        customerAccountNumber = generateCustomerAccountNumber();
        accountEntityRequest.setAccountNumber(customerAccountNumber);
        accountEntityRequest = accountMapper.mapBankDetailsResponseToAccountEntity(accountEntityRequest, accountOpenBalance, savingsAccountEntity);
        accountEntity = accountRepository.save(accountEntityRequest);
        savingsAccountResponse = accountMapper.mapAccountEntityToSavingsAccountResponse(accountEntity);
        savingsAccountResponse.setCustomerDetails(customerDetailsResponse);
        log.info(AccountConstants.END_SERVICE, "addNewSavingAccount()");

        return savingsAccountResponse;
    }

    @Transactional
    @Override
    public CurrentAccountResponse addNewCurrentAccount(AccountEntity accountEntityRequest, int customerId, double accountOpenBalance) {

        log.info(AccountConstants.START_SERVICE, "addNewCurrentAccount()");
        AccountEntity accountEntity = new AccountEntity();
        CurrentAccountEntity currentAccountEntity = new CurrentAccountEntity();
        String customerAccountNumber = null;
        CurrentAccountResponse currentAccountResponse = new CurrentAccountResponse();
        CustomerDetailsResponse customerDetailsResponse = new CustomerDetailsResponse();

        accountEntityRequest.setCustomerId(customerId);
        accountEntityRequest.setAccountType(AccountConstants.CURRENT_ACCOUNT);

        customerDetailsResponse = customerFeignClient.getCustomerDetailsForAccountService(customerId);

        customerAccountNumber = generateCustomerAccountNumber();
        accountEntityRequest.setAccountNumber(customerAccountNumber);
        accountEntityRequest = accountMapper.mapBankDetailsResponseToAccountEntity(accountEntityRequest,accountOpenBalance, currentAccountEntity);
        accountEntity = accountRepository.save(accountEntityRequest);
        currentAccountResponse = accountMapper.mapAccountEntityToCurrentAccountResponse(accountEntity);
        currentAccountResponse.setCustomerDetails(customerDetailsResponse);
        log.info(AccountConstants.END_SERVICE, "addNewCurrentAccount()");

        return currentAccountResponse;
    }

    @Transactional
    @Override
    public FixedDepositAccountResponse addNewFixedDepositAccount(AccountEntity accountEntityRequest, int customerId, double accountOpenBalance) {

        log.info(AccountConstants.START_SERVICE, "addNewFixedAccount()");
        FixedDepositAccountResponse fixedDepositAccountResponse = new FixedDepositAccountResponse();
        CustomerDetailsResponse customerDetailsResponse = new CustomerDetailsResponse();
        AccountEntity accountEntity = new AccountEntity();
        FixedDepositAccountEntity fixedDepositAccountEntity = new FixedDepositAccountEntity();

        fixedDepositAccountEntity.setFixedDepositAmount(accountEntityRequest.getFixedDepositAccountEntity().getFixedDepositAmount());
        fixedDepositAccountEntity.setFdTenure(accountEntityRequest.getFixedDepositAccountEntity().getFdTenure());
        fixedDepositAccountEntity.setFdInterestFrequency(accountEntityRequest.getFixedDepositAccountEntity().getFdInterestFrequency());
        String customerAccountNumber = null;

        accountEntityRequest.setCustomerId(customerId);
        accountEntityRequest.setAccountType(AccountConstants.FIXED_DEPOSIT);


        customerDetailsResponse = customerFeignClient.getCustomerDetailsForAccountService(customerId);

        customerAccountNumber = generateCustomerAccountNumber();
        accountEntityRequest.setAccountNumber(customerAccountNumber);
        accountEntityRequest = accountMapper.mapBankDetailsResponseToAccountEntity(accountEntityRequest, accountOpenBalance, fixedDepositAccountEntity);
        accountEntityRequest = generateFdMaturityAmount(accountEntityRequest);
        accountEntity = accountRepository.save(accountEntityRequest);
        fixedDepositAccountResponse = accountMapper.mapAccountEntityToFixedDepositAccountResponse(accountEntity);
        fixedDepositAccountResponse.setCustomerDetails(customerDetailsResponse);
        log.info(AccountConstants.END_SERVICE, "addNewFixedAccount()");

        return fixedDepositAccountResponse;
    }

    @Override
    public TransactionResponse processCreditTransaction(int customerId, String accountNumber,
                                                        String transactionTypeParam, String transactionModeParam,
                                                        int paymentId, double transactionAmount, String description) {

        TransactionEntity transactionEntity = new TransactionEntity();
        TransactionResponse transactionResponse = new TransactionResponse();
        CustomerDetailsResponse customerDetailsResponse = new CustomerDetailsResponse();
        AccountEntity accountEntity = new AccountEntity();

        accountEntity = accountRepository.findByAccountNumber(accountNumber);
        if (accountEntity == null) {
            return createErrorResponse(transactionResponse, "Account Not Valid");
        }

        transactionEntity = accountMapper.mapTransactionParamsToTransactionEntity(customerId, accountEntity, transactionTypeParam, transactionModeParam, transactionAmount, description);
        AccountEntity updatedAccountEntity = transactionEntity.getAccountEntity();

        // Validating payment status
        boolean isPaymentValid = paymentServiceFeignClient.validatePaymentStatusFromPaymentService(paymentId, transactionAmount);
        if (!isPaymentValid) {
            transactionResponse.setResponseMessage("Payment failed.");
            return transactionResponse;
        }

        transactionEntity = performCreditDebitTransaction(accountEntity, transactionEntity, transactionTypeParam);
        transactionEntity.setTransactionNumber(generateTransactionCode());

        // Saving the transaction
        TransactionEntity savedTransaction = transactionRepository.save(transactionEntity);

        // Fetching customer details
        customerDetailsResponse = customerFeignClient.getCustomerDetailsForAccountService(customerId);

        // Maping response
        transactionResponse = accountMapper.mapTransactionEntityToTransactionResponse(savedTransaction);
        transactionResponse.setCustomerDetailsResponse(customerDetailsResponse);
        transactionResponse.setResponseMessage("Transaction processed successfully.");

        return transactionResponse;
    }

    @Override
    public TransactionResponse processDebitTransaction(int customerId, String accountNumber,
                                                       String transactionTypeParam, String transactionModeParam,
                                                       int paymentId, double transactionAmount, String description) {

        TransactionEntity transactionEntity = new TransactionEntity();
        TransactionResponse transactionResponse = new TransactionResponse();
        CustomerDetailsResponse customerDetailsResponse = new CustomerDetailsResponse();
        AccountEntity accountEntity = new AccountEntity();

        accountEntity = accountRepository.findByAccountNumber(accountNumber);
        if (accountEntity == null) {
            transactionResponse.setResponseMessage("Account not found.");
            return transactionResponse;
        }

        transactionEntity = accountMapper.mapTransactionParamsToTransactionEntity(customerId, accountEntity, transactionTypeParam, transactionModeParam, transactionAmount, description);
        AccountEntity updatedAccountEntity = transactionEntity.getAccountEntity();

        boolean paymentStatus = paymentServiceFeignClient.validatePaymentStatusFromPaymentService(paymentId, transactionAmount);
        if(!paymentStatus) {
            transactionResponse.setResponseMessage("Payment Failed");
        }
        else {
            transactionEntity = performCreditDebitTransaction(updatedAccountEntity, transactionEntity, transactionTypeParam);
            transactionEntity.setTransactionNumber(generateTransactionCode());

            transactionEntity = transactionRepository.save(transactionEntity);

            customerDetailsResponse = customerFeignClient.getCustomerDetailsForAccountService(customerId);
            transactionResponse = accountMapper.mapTransactionEntityToTransactionResponse(transactionEntity);
            transactionResponse.setCustomerDetailsResponse(customerDetailsResponse);
        }
        return transactionResponse;
    }

    @Override
    public AccountResponse getAccountBalanceByCustomerId(Integer customerId, String accountType) {

        log.info(AccountConstants.START_SERVICE, "getAccountBalanceByCustomerId()");

        AccountResponse accountResponse = new AccountResponse();
        AccountEntity accountEntity = new AccountEntity();
        AccountProjection accountProjection = null;
        double latestAccountBalance = 0.0;


        accountType = mapAccountTypeByFormat(accountType);
        accountProjection = accountRepository.getAccountByCustomerId(customerId, accountType);
        accountResponse = accountMapper.mapAccountProjectionToAccountResponse(accountProjection);
        latestAccountBalance = accountProjection.getAccountBalance();
        accountResponse.setResponseMessage( latestAccountBalance > 0
                ? AccountConstants.ACCOUNT_BALANCE_SUCCESS_STATUS
                : AccountConstants.ACCOUNT_BALANCE_FAILURE_STATUS);
        log.info(AccountConstants.ACCOUNT_BALANCE, latestAccountBalance);
        log.info(AccountConstants.END_SERVICE, "getAccountBalanceByCustomerId()");

        return accountResponse;
    }

    @Override
    public AccountResponse getAccDetailsForundTransferService(int customerId, String accountNumber) {

        AccountResponse accountResponse = new AccountResponse();
        AccountProjection accountProjection = null;
        accountProjection = accountRepository.getAccountByCustomerIdAndAccountNumber(customerId, accountNumber);
        accountResponse = accountMapper.mapAccountProjectionToAccountResponse(accountProjection);
        return accountResponse;
    }

    private TransactionResponse createErrorResponse(TransactionResponse response, String errorMessage) {
        response.setResponseMessage(errorMessage);
        return response;
    }

    private String mapAccountTypeByFormat(String accountTypeFormat) {

        String accountType = null;
        switch (accountTypeFormat.toLowerCase()) {
            case "savings" :
                accountType = BankConstants.SAVINGS_ACCOUNT;
                break;
            case "current" :
                accountType = BankConstants.CURRENT_ACCOUNT;
                break;
            case "fixed":
                accountType = BankConstants.FIXED_DEPOSIT;
                break;
            default:
                accountType = AccountConstants.INVALID_ACCOUNT_TYPE;
                break;
        }

        return accountType;
    }

    private TransactionEntity performCreditDebitTransaction(AccountEntity accountEntity, TransactionEntity transactionEntity, String transactionTypeParam) {

        double updatedAccountBalance = accountEntity.getAccountBalance();
        double transactionAmount = transactionEntity.getTransactionAmount();

        switch (transactionTypeParam.toLowerCase()) {

            case "credit":
                updatedAccountBalance += transactionAmount;
                break;
            case "debit":
                if (transactionAmount > updatedAccountBalance)
                    throw new IllegalArgumentException("Insufficient balance for debit transaction.");
                updatedAccountBalance -= transactionAmount;
                break;
            default:
                break;
        }
        transactionEntity.setTotalAccountBalance(updatedAccountBalance);
        accountEntity.setAccountBalance(updatedAccountBalance);
        transactionEntity.setAccountEntity(accountEntity);

        return transactionEntity;
    }

    private String generateTransactionCode() {

        log.info(AccountConstants.START_SERVICE, "generateTransactionCode()");

        String transactionCode = null;
        String transactionCodePrefix = AccountConstants.TRANSACTION_NUMBER_PREFIX;
        int currentYear = Year.now().getValue();
        String latestTransactionCode = transactionRepository.findLatestTransactionCode(transactionCodePrefix);

        int nextSequence = ((latestTransactionCode != null) && latestTransactionCode.startsWith(transactionCodePrefix + currentYear))
                ? Integer.parseInt(latestTransactionCode.substring((transactionCodePrefix + currentYear).length())) + 1
                : 1;

        String transactionSequenceNumber = String.format("%07d", nextSequence);
        transactionCode = transactionCodePrefix + currentYear + transactionSequenceNumber;

        log.info(AccountConstants.TRANSACTION_NUMBER, transactionCode);
        log.info(AccountConstants.END_SERVICE, "generateTransactionCode()");

        return transactionCode;
    }



    private String generateCustomerAccountNumber() {

        log.info(AccountConstants.START_SERVICE, "generateCustomerAccountNumber()");

        String customerAccountNumber = null;
        String accountNumberPrefix = AccountConstants.ACCOUNT_NUMBER_PREFIX;
        int currentYear = Year.now().getValue();
        String latestAccountNumber = accountRepository.findLatestAccountNumber(accountNumberPrefix);

        int nextSequence = ((latestAccountNumber != null) && latestAccountNumber.startsWith(accountNumberPrefix + currentYear))
                ? Integer.parseInt(latestAccountNumber.substring((accountNumberPrefix + currentYear).length())) + 1
                : 1;

        String accSequenceNumber = String.format("%05d", nextSequence);
        customerAccountNumber = accountNumberPrefix + currentYear + accSequenceNumber;

        log.info(AccountConstants.CUSTOMER_ACCOUNT_NUMBER, customerAccountNumber);
        log.info(AccountConstants.END_SERVICE, "generateCustomerAccountNumber()");

        return customerAccountNumber;
    }

    private AccountEntity generateFdMaturityAmount(AccountEntity accountEntity) {

        if (accountEntity.getFixedDepositAccountEntity() == null) {
            throw new IllegalArgumentException("Fixed Deposit Account details are missing.");
        }

        FixedDepositAccountEntity fixedDepositAccountEntity = accountEntity.getFixedDepositAccountEntity();

        if (fixedDepositAccountEntity.getFixedDepositAmount() <= 0
                || fixedDepositAccountEntity.getFixedDepositInterestRate() <= 0
                || Double.parseDouble(fixedDepositAccountEntity.getFdTenure()) <= 0) {

            throw new IllegalArgumentException("Principal amount, interest rate, or tenure is invalid.");
        }
        double tenureInYears = Double.parseDouble(fixedDepositAccountEntity.getFdTenure());
        double maturityAmount = calculateMaturityAmount( fixedDepositAccountEntity.getFixedDepositAmount(),
                                                         fixedDepositAccountEntity.getFixedDepositInterestRate(),
                                                         tenureInYears,
                                                         fixedDepositAccountEntity.getFdInterestFrequency());

        // Account creation date + tenure in months (converted from years)
        LocalDateTime accountCreationDate = accountEntity.getFixedDepositAccountEntity().getFdCreationDate();
        LocalDateTime maturityDate = accountCreationDate.plusMonths((long) (tenureInYears * 12));
        fixedDepositAccountEntity.setFdMaturityDate(maturityDate);
        fixedDepositAccountEntity.setFdMaturityAmount(maturityAmount);

        return accountEntity;
    }

    private double calculateMaturityAmount(double fixedDepositAmount, double fixedDepositInterestRate,
                                           double tenureInYears, int fdInterestFrequency) {

        double principalAmount = fixedDepositAmount;
        double rate = fixedDepositInterestRate / 100; // Convert percentage to decimal
        double tenure = tenureInYears;
        int compoundingFrequency = fdInterestFrequency;

        // Formula: A = P * (1 + r/n)^(n*t)
        double maturityAmount = principalAmount * Math.pow(1 + rate / compoundingFrequency, compoundingFrequency * tenure);
        maturityAmount = roundOffMaturityAmount(maturityAmount);

        return maturityAmount;
    }

    private double roundOffMaturityAmount(double maturityAmount) {

        String roundOffAmount = String.format("%.2f", maturityAmount);
        return Double.parseDouble(roundOffAmount);
    }


}
