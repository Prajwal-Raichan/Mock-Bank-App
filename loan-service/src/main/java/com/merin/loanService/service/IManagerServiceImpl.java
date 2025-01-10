package com.merin.loanService.service;

import com.merin.loanService.client.feignClient.IAccountServiceFeignClient;
import com.merin.loanService.client.feignClient.ICustomerServiceFeignClient;
import com.merin.loanService.constant.LoanConstants;
import com.merin.loanService.dto.AccountResponse;
import com.merin.loanService.dto.CustomerDetailsResponse;
import com.merin.loanService.dto.LoanResponse;
import com.merin.loanService.entity.LoanEntity;
import com.merin.loanService.enums.LoanStatus;
import com.merin.loanService.mapper.LoanMapper;
import com.merin.loanService.repository.ILoanRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class IManagerServiceImpl implements IManagerService {


    @Autowired
    private ILoanRepository loanRepository;

    @Autowired
    private LoanMapper loanMapper;

    @Autowired
    private ICustomerServiceFeignClient customerServiceFeignClient;

    @Autowired
    private IAccountServiceFeignClient accountServiceFeignClient;


    @Override
    public List<LoanResponse> getAllUnApprovedLoanRequests() {

        log.info(LoanConstants.START_SERVICE + "getAllUnApprovedLoanRequests()");
        List<LoanResponse> loanResponseList = new ArrayList<>();
        List<LoanEntity> loanEntityList = loanRepository.findAll();

        loanEntityList = loanEntityList.stream()
                .filter(loan -> loan.getLoanStatus() != LoanStatus.APPROVED)
                //.map(this::convertToLoanResponse)  // Convert each LoanEntity to LoanResponse
                .collect(Collectors.toList());


        loanResponseList = mapCustomerAndAccountDetails(loanEntityList);

        return loanResponseList;
    }


    private List<LoanResponse> mapCustomerAndAccountDetails(List<LoanEntity> loanEntityList) {

        return  loanEntityList.stream().map(loan -> {

                    CustomerDetailsResponse customerDetailsResponse = fetchCustomerDetails(loan.getCustomerId());
                    AccountResponse accountResponse = fetchAccountDetails(loan.getCustomerId(), loan.getAccountNumber());

                    LoanResponse loanResponse = loanMapper.mapLoanEntityToLoanResponse(loan);

                    loanResponse.setCustomerDetailsResponse(customerDetailsResponse);
                    loanResponse.setAccountResponse(accountResponse);

                    return loanResponse;
                })
                .collect(Collectors.toList());
    }


    private LoanResponse convertToLoanResponse(LoanEntity loanEntity) {

        return loanMapper.mapLoanEntityToLoanResponse(loanEntity);
    }

    @Override
    public LoanResponse authorizeLoanRequest(int loanId, int customerId) {

        log.info(LoanConstants.START_SERVICE + "authorizeLoanRequest()" );

        LoanResponse loanResponse = new LoanResponse();
        LoanEntity loanEntity = new LoanEntity();
        CustomerDetailsResponse customerDetailsResponse = new CustomerDetailsResponse();
        AccountResponse accountResponse = new AccountResponse();

        loanEntity = loanRepository.findById(loanId).get();
        customerDetailsResponse = fetchCustomerDetails(customerId);
        accountResponse = fetchAccountDetails(customerId,loanEntity.getAccountNumber());

        loanEntity = authorizeLoanRequest(loanEntity);

        loanEntity = loanRepository.save(loanEntity);
        loanResponse = loanMapper.mapLoanEntityToLoanResponse(loanEntity);
        loanResponse.setCustomerDetailsResponse(customerDetailsResponse);
        loanResponse.setAccountResponse(accountResponse);

        log.info(LoanConstants.END_SERVICE + "authorizeLoanRequest()");

        return loanResponse;
    }

    private LoanEntity authorizeLoanRequest(LoanEntity loanEntity) {

        log.info("Inside authorizeLoanRequest()");

        LocalDate loanStartDate = LocalDate.now();

        int loanTermInYears = loanEntity.getLoanTerm();
        int loanTermInMonths = loanTermInYears * 12; // Converted years to months
        LocalDate loanEndDate = loanStartDate.plusMonths(loanTermInMonths);

        double totalOutstandingAmount = getTotalOutstandingAmount(loanEntity, loanTermInMonths);

        loanEntity.setStartDate(loanStartDate);
        loanEntity.setEndDate(loanEndDate);
        loanEntity.setOutstandingAmount(totalOutstandingAmount);

        if(loanEntity.getCibilScore() >= 770)
            loanEntity.setLoanStatus(LoanStatus.APPROVED);
        else
            loanEntity.setLoanStatus(LoanStatus.UNAPPROVED);

        return loanEntity;
    }

    private static double getTotalOutstandingAmount(LoanEntity loanEntity, int loanTermInMonths) {

        log.info("Inside getTotalOutstandingAmount()");

        double principalAmount = loanEntity.getLoanAmount();
        double annualInterestRate = loanEntity.getInterestRate() / 100.0; // Convert percentage to decimal
        double monthlyInterestRate = annualInterestRate / 12.0;

        // Formula for EMI (Equated Monthly Installment)
        double emi = (principalAmount * monthlyInterestRate * Math.pow(1 + monthlyInterestRate, loanTermInMonths))
                / (Math.pow(1 + monthlyInterestRate, loanTermInMonths) - 1);

        double totalOutstandingAmount = emi * loanTermInMonths;
        return totalOutstandingAmount;
    }


    @Override
    public LoanResponse getLoanRequestById(int loanId) {

        LoanResponse loanResponse =  new LoanResponse();
        LoanEntity loanEntity = new LoanEntity();
        CustomerDetailsResponse customerDetailsResponse = new CustomerDetailsResponse();
        AccountResponse accountResponse = new AccountResponse();

        loanEntity = loanRepository.findById(loanId).get();
        customerDetailsResponse = fetchCustomerDetails(loanEntity.getCustomerId());
        accountResponse = fetchAccountDetails(loanEntity.getCustomerId(), loanEntity.getAccountNumber());
        loanResponse = loanMapper.mapLoanEntityToLoanResponse(loanEntity);
        loanResponse.setAccountResponse(accountResponse);
        loanResponse.setCustomerDetailsResponse(customerDetailsResponse);

        return loanResponse;
    }

    private AccountResponse fetchAccountDetails(int customerId, String accountNumber) {
        return accountServiceFeignClient.getAccDetailsForLoanService(customerId, accountNumber);
    }

    private CustomerDetailsResponse fetchCustomerDetails(int customerId) {
        return customerServiceFeignClient.CusgetCustomerDetailsForLoanService(customerId);
    }
}
