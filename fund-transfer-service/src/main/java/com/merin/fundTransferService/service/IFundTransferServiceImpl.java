package com.merin.fundTransferService.service;

import com.merin.fundTransferService.client.feignClient.IAccountServiceFeignClient;
import com.merin.fundTransferService.client.feignClient.IPaymentServiceFeignClient;
import com.merin.fundTransferService.dto.AccountResponse;
import com.merin.fundTransferService.dto.FundTransferResponse;
import com.merin.fundTransferService.entity.BeneficiaryEntity;
import com.merin.fundTransferService.entity.FundTransferEntity;
import com.merin.fundTransferService.enums.TransactionMode;
import com.merin.fundTransferService.enums.TransactionType;
import com.merin.fundTransferService.enums.TransferMode;
import com.merin.fundTransferService.enums.TransferStatus;
import com.merin.fundTransferService.mapper.FundTransferMapper;
import com.merin.fundTransferService.repository.IBeneficiaryMasterRepository;
import com.merin.fundTransferService.repository.IFundTransferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class IFundTransferServiceImpl implements IFundTransferService {

    @Autowired
    private IFundTransferRepository fundTransferRepository;

    @Autowired
    private IAccountServiceFeignClient accountServiceFeignClient;

    @Autowired
    private IBeneficiaryMasterRepository beneficiaryMasterRepository;

    @Autowired
    private FundTransferMapper fundTransferMapper;

    @Autowired
    private IPaymentServiceFeignClient paymentServiceFeignClient;

    @Transactional
    @Override
    public FundTransferResponse transferFund(FundTransferEntity fundTransferRequest, int customerId,
                                             String accountNumber, int beneficiaryId,
                                             int paymentId, String transferMode, double transferAmount) {

        FundTransferResponse fundTransferResponse = new FundTransferResponse();

        // Validating account and balance
        AccountResponse accountResponse = validateAccountAndBalance(customerId, accountNumber, transferAmount);
        if (accountResponse == null) {
            return createErrorResponse(fundTransferResponse, "Insufficient balance or account not found.");
        }

        // Validating payment status
        boolean isPaymentValid = paymentServiceFeignClient.validatePaymentStatusFromPaymentService(paymentId, transferAmount);
        if (!isPaymentValid) {
            return createErrorResponse(fundTransferResponse, "Payment is invalid.");
        }

        // Retrieving beneficiary details
        BeneficiaryEntity beneficiaryEntity = getBeneficiaryDetails(beneficiaryId);
        if (beneficiaryEntity == null) {
            return createErrorResponse(fundTransferResponse, "Beneficiary not found.");
        }

        // Performing the transaction
        boolean transactionStatus = performTransaction(customerId, accountNumber, paymentId, transferAmount, fundTransferRequest.getRemarks());
        if (!transactionStatus) {
            return createErrorResponse(fundTransferResponse, "Error performing transaction.");
        }
        // Populating and saving the fund transfer entity
        fundTransferRequest.setBeneficiary(beneficiaryEntity);
        fundTransferRequest.setTransferStatus(TransferStatus.COMPLETED);
        fundTransferRequest.setTransferMode(TransferMode.fromValue(transferMode));
        fundTransferRequest.setTransferDate(LocalDateTime.now());
        fundTransferRequest.setCustomerId(customerId);
        fundTransferRequest.setTransferAmount(transferAmount);

        FundTransferEntity fundTransferEntity = fundTransferRepository.save(fundTransferRequest);

        // Mapping and returning the response
        fundTransferResponse = fundTransferMapper.mapFundTransferEntityToFundTransferResponse(fundTransferEntity);
        fundTransferResponse.setResponseMessage("Fund transfer completed successfully.");

        return fundTransferResponse;
    }

    private FundTransferResponse createErrorResponse(FundTransferResponse response, String errorMessage) {
        response.setResponseMessage(errorMessage);
        return response;
    }


    private AccountResponse validateAccountAndBalance(int customerId, String accountNumber, double transferAmount) {

        AccountResponse accountResponse = accountServiceFeignClient.getAccDetailsForFundTransferService(customerId, accountNumber);
        if (accountResponse != null && accountResponse.getAccountBalance() >= transferAmount) {
            double currentAccountBalance = accountResponse.getAccountBalance();
            accountResponse.setAccountBalance(currentAccountBalance - transferAmount); // Deduct transfer amount
            return accountResponse;
        }
        return null;
    }

    private BeneficiaryEntity getBeneficiaryDetails(int beneficiaryId) {

        return beneficiaryMasterRepository.findById(beneficiaryId).orElse(null);
    }

    private boolean performTransaction(int customerId, String accountNumber, int paymentId, double transferAmount, String remarks) {

        String transactionType = String.valueOf(TransactionType.DEBIT);
        String transactionMode = String.valueOf(TransactionMode.ONLINE);
        try {
            accountServiceFeignClient.performTransaction(customerId, accountNumber, transactionType, transactionMode, paymentId, transferAmount, remarks);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
