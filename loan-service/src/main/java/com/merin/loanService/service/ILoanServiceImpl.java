package com.merin.loanService.service;

import com.merin.loanService.client.feignClient.IAccountServiceFeignClient;
import com.merin.loanService.client.feignClient.ICibilScoreFeignClient;
import com.merin.loanService.client.feignClient.ICustomerServiceFeignClient;
import com.merin.loanService.constant.LoanConstants;
import com.merin.loanService.dto.AccountResponse;
import com.merin.loanService.dto.CustomerDetailsResponse;
import com.merin.loanService.dto.LoanResponse;
import com.merin.loanService.entity.LoanEntity;
import com.merin.loanService.enums.LoanInterest;
import com.merin.loanService.enums.LoanStatus;
import com.merin.loanService.enums.LoanType;
import com.merin.loanService.mapper.LoanMapper;
import com.merin.loanService.repository.ILoanRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Slf4j
@Service
public class ILoanServiceImpl implements ILoanService {

    @Autowired
    private ILoanRepository loanRepository;

    @Autowired
    private IAccountServiceFeignClient accountServiceFeignClient;

    @Autowired
    private ICustomerServiceFeignClient customerServiceFeignClient;

    @Autowired
    private ICibilScoreFeignClient cibilScoreFeignClient;

    @Autowired
    private LoanMapper loanMapper;

    private Integer globalCibilscore = null;

    //@KafkaListener(topics = LoanConstants.CIBIL_SCORE, groupId = "user-G2")
    public boolean updateCibilScoreViaKafka(String cibilScore) {

        try {
            globalCibilscore = Integer.parseInt(cibilScore);  // Parsing the CIBIL score
            log.info("Cibil Score Fetched From Kafka: {}", globalCibilscore);
            return true;
        } catch (NumberFormatException e) {
            log.error("Invalid CIBIL score received: {}", cibilScore, e);
        }
        return false;

    }


    @Override
    public LoanResponse createNewLoanRequest(LoanEntity loanRequest, int customerId, String accountNumber) {


        LoanResponse loanResponse =  new LoanResponse();
        LoanEntity loanEntity = new LoanEntity();
        CustomerDetailsResponse customerDetailsResponse = new CustomerDetailsResponse();
        AccountResponse accountResponse = new AccountResponse();

        String cScore = null;

        double loanInterestRate = 0.0;

        loanInterestRate = getLoanInterestRate(loanRequest);

        accountResponse = fetchAccountDetails(customerId, accountNumber);

        customerDetailsResponse = fetchCustomerDetails(customerId);

        String panNumber = accountResponse.getPanNumber();
        //cibilScoreFeignClient.getCibilScoreForExternalServices(panNumber);
       // Integer cibilScore = globalCibilscore;
        Integer cibilScore = cibilScoreFeignClient.getCibilScoreForExternalServices(panNumber);

        log.info("CIBIL Score Fetched: {}",cibilScore);
        loanRequest.setCustomerId(customerId);
        loanRequest.setAccountNumber(accountNumber);
        loanRequest.setCibilScore(cibilScore);
        loanRequest.setLoanStatus(LoanStatus.PENDING);
        loanRequest.setLoanCreationDate(LocalDate.now());

        loanRequest.setInterestRate(loanInterestRate);
        loanEntity = loanRepository.save(loanRequest);

        loanResponse = loanMapper.mapLoanEntityToLoanResponse(loanEntity);
        loanResponse.setAccountResponse(accountResponse);
        loanResponse.setCustomerDetailsResponse(customerDetailsResponse);

      //  globalCibilscore = 0;

        return loanResponse;
    }

    @Override
    public LoanResponse revokeLoanRequest(int loanId, int customerId) {

        LoanResponse loanResponse =  new LoanResponse();
        LoanEntity loanEntity = new LoanEntity();
        CustomerDetailsResponse customerDetailsResponse = new CustomerDetailsResponse();

        customerDetailsResponse = fetchCustomerDetails(customerId);
        if(customerDetailsResponse != null)
            loanRepository.deleteById(loanId);
        loanResponse.setResponseMessage("Loan Request Deleted Successfully");

        return loanResponse;
    }

    @Override
    public LoanResponse getLoanRequestById(int loanId) {

        LoanResponse loanResponse =  new LoanResponse();
        LoanEntity loanEntity = new LoanEntity();
        loanEntity = loanRepository.findById(loanId).get();
        loanResponse = loanMapper.mapLoanEntityToLoanResponse(loanEntity);
        return loanResponse;
    }

    @Override
    public List<LoanResponse> getAllLoanRequestByCustomerId(int customerId) {

        List<LoanResponse> loanResponseList = new ArrayList<>();
        List<LoanEntity> loanEntityList = new ArrayList<>();
        loanEntityList = loanRepository.findAll();
        loanResponseList = loanMapper.mapLoanEntityListToLoanResponseList(loanEntityList);
        return loanResponseList;
    }

    private AccountResponse fetchAccountDetails(int customerId, String accountNumber) {
        return accountServiceFeignClient.getAccDetailsForLoanService(customerId, accountNumber);
    }

    private CustomerDetailsResponse fetchCustomerDetails(int customerId) {
        return customerServiceFeignClient.CusgetCustomerDetailsForLoanService(customerId);
    }


    private double getLoanInterestRate(LoanEntity loanRequest) {
        LoanType loanType = loanRequest.getLoanType();

        double interestRate;
        switch (loanType) {
            case HOME_LOAN:
                interestRate = LoanInterest.HOME_LOAN.getInterestRate();
                break;
            case CAR_LOAN:
                interestRate = LoanInterest.CAR_LOAN.getInterestRate();
                break;
            case BIKE_LOAN:
                interestRate = LoanInterest.BIKE_LOAN.getInterestRate();
                break;
            case EDUCATIONAL_LOAN:
                interestRate = LoanInterest.EDUCATIONAL_LOAN.getInterestRate();
                break;
            case PERSONAL_LOAN:
                interestRate = LoanInterest.PERSONAL_LOAN.getInterestRate();
                break;
            default:
                throw new IllegalArgumentException("Invalid Loan Type");
        }

        return interestRate;
    }

}
