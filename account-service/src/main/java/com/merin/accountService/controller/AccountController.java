package com.merin.accountService.controller;


import com.merin.accountService.constants.AccountConstants;
import com.merin.accountService.dto.AccountResponse;
import com.merin.accountService.dto.TransactionResponse;
import com.merin.accountService.entity.AccountEntity;
import com.merin.accountService.service.IAccountService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/account")
@Tag(name = " Account-Service Controller",description = " Account-Service Management Portal")
public class AccountController {

    @Autowired
    private IAccountService accountService;


    @PostMapping("/createAccount")
    public <T>  ResponseEntity<T>addNewAccount(@RequestBody AccountEntity accountEntityRequest,
                                               @RequestParam("customerId") int customerId,
                                               @RequestParam(name = "accountType",defaultValue = "savings") String accountType,
                                               @RequestParam("accountOpenBalance") double accountOpenBalance) {

        log.info(AccountConstants.INSIDE_CONTROLLER, "addNewAccount()");
        T accountResponse = null;

        switch (accountType.toLowerCase()) {
            case "savings":
                accountResponse = (T) accountService.addNewSavingAccount(accountEntityRequest, customerId, accountOpenBalance);
                break;
            case "current":
                accountResponse = (T) accountService.addNewCurrentAccount(accountEntityRequest, customerId, accountOpenBalance);
                break;
            case "fixed":
                accountResponse = (T) accountService.addNewFixedDepositAccount(accountEntityRequest, customerId, accountOpenBalance);
                break;
            default:
                accountResponse = (T) AccountConstants.INVALID_ACCOUNT_TYPE;
        }
        return new ResponseEntity<>(accountResponse, HttpStatus.OK);
    }


    @PostMapping("/performTransaction")
    public ResponseEntity<TransactionResponse> performTransaction(@RequestParam("customerId") int customerId,
                                                                  @RequestParam("accountNumber") String accountNumber,
                                                                  @RequestParam("transactionType") String  transactionType,
                                                                  @RequestParam("transactionMode") String  transactionMode,
                                                                  @RequestParam("paymentId") int paymentId,
                                                                  @RequestParam("transactionAmount") double transactionAmount,
                                                                  @RequestParam(required = false,name = "description")String description){

        log.info(AccountConstants.INSIDE_CONTROLLER, "performTransaction()");
        TransactionResponse transactionResponse = new TransactionResponse();
        switch (transactionType.toLowerCase()) {
            case "credit":
                transactionResponse = accountService.processCreditTransaction(customerId, accountNumber, transactionType, transactionMode, paymentId, transactionAmount, description);
                break;
            case "debit":
                transactionResponse = accountService.processDebitTransaction(customerId, accountNumber, transactionType, transactionMode, paymentId, transactionAmount, description);
                break;
            default:
                transactionResponse = null;

        }
        return new ResponseEntity<>(transactionResponse, HttpStatus.OK);

    }

    @GetMapping("/getAccBalanceByCustomerId")
    public ResponseEntity<AccountResponse> getAccountBalanceByCustomerId(@RequestParam("customerId") Integer customerId,
                                                                         @RequestParam("accountType") String accountType) {

        AccountResponse accountResponse = new AccountResponse();
        accountResponse = accountService.getAccountBalanceByCustomerId(customerId, accountType);

        return new ResponseEntity<>(accountResponse, HttpStatus.OK);
    }

    @GetMapping("/getAccountDetails/fundTransferService")
    public ResponseEntity<AccountResponse> getAccDetailsForFundTransferService(@RequestParam("customerId") int customerId,
                                                                               @RequestParam("accountNumber") String accountNumber) {

        AccountResponse accountResponse = new AccountResponse();
        accountResponse = accountService.getAccDetailsForundTransferService(customerId, accountNumber);

        return new ResponseEntity<>(accountResponse, HttpStatus.OK);
    }

    @GetMapping("/getAccountDetails/loanService")
    public ResponseEntity<AccountResponse> getAccDetailsForLoanService(@RequestParam("customerId") int customerId,
                                                                       @RequestParam("accountNumber") String accountNumber) {

        AccountResponse accountResponse = new AccountResponse();
        accountResponse = accountService.getAccDetailsForundTransferService(customerId, accountNumber);

        return new ResponseEntity<>(accountResponse, HttpStatus.OK);
    }





}
