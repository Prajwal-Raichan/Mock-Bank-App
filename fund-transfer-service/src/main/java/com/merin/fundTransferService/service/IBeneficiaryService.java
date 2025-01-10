package com.merin.fundTransferService.service;

import com.merin.fundTransferService.dto.BeneficiaryResponse;
import com.merin.fundTransferService.entity.BeneficiaryEntity;

import java.util.List;

public interface IBeneficiaryService {

    BeneficiaryResponse addNewBeneficiary(BeneficiaryEntity beneficiaryEntity, int customerId, String accountNumber) throws Exception;

    List<BeneficiaryResponse> getCustomerBeneficiaryListById(int customerId);
}

