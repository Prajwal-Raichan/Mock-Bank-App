package com.merin.fundTransferService.service;

import com.merin.fundTransferService.dto.BeneficiaryResponse;

import java.util.List;

public interface IManagerService {

    List<BeneficiaryResponse> gellUnAuthorizedBeneficiaryList();

    List<BeneficiaryResponse> bulkAuthorizeBeneficiaryList();

    List<BeneficiaryResponse> getAllAuthorizeBeneficiaryList();

    BeneficiaryResponse rejectBeneficiaryAccount(int beneficiaryId);
}
