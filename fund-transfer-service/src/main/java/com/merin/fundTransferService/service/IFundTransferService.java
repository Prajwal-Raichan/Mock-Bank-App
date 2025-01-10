package com.merin.fundTransferService.service;

import com.merin.fundTransferService.dto.FundTransferResponse;
import com.merin.fundTransferService.entity.FundTransferEntity;

public interface IFundTransferService {


    FundTransferResponse transferFund(FundTransferEntity fundTransferRequest, int customerId,
                                      String accountNumber, int beneficiaryId, int paymentId,
                                      String transferMode, double transferAmount);
}
