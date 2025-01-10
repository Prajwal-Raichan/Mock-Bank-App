package com.merin.fundTransferService.client.feignClient;

import com.merin.fundTransferService.dto.AccountResponse;
import com.merin.fundTransferService.dto.TransactionResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("ACCOUNT-SERVICE")
public interface IAccountServiceFeignClient {

    @GetMapping("/account/getAccountDetails/fundTransferService")
    AccountResponse getAccDetailsForFundTransferService(@RequestParam("customerId") int customerId,
                                                       @RequestParam("accountNumber") String accountNumber);


    @PostMapping("/account//performTransaction")
    TransactionResponse performTransaction(@RequestParam("customerId") int customerId,
                                           @RequestParam("accountNumber") String accountNumber,
                                           @RequestParam("transactionType") String  transactionType,
                                           @RequestParam("transactionMode") String  transactionMode,
                                           @RequestParam("paymentId") int paymentId,
                                           @RequestParam("transactionAmount") double transactionAmount,
                                           @RequestParam(required = false,name = "description")String description);
}
