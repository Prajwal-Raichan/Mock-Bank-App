package com.merin.fundTransferService.service;

import com.merin.fundTransferService.client.feignClient.IAccountServiceFeignClient;
import com.merin.fundTransferService.dto.AccountResponse;
import com.merin.fundTransferService.dto.BeneficiaryResponse;
import com.merin.fundTransferService.entity.BeneficiaryEntity;
import com.merin.fundTransferService.enums.BeneficiaryStatus;
import com.merin.fundTransferService.mapper.BeneficiaryMapper;
import com.merin.fundTransferService.repository.IBeneficiaryMasterRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class IBeneficiaryServiceImpl implements IBeneficiaryService {


    @Autowired
    private IBeneficiaryMasterRepository iBeneficiaryMasterRepository;

    @Autowired
    private IAccountServiceFeignClient accountServiceFeignClient;

    @Autowired
    private BeneficiaryMapper beneficiaryMapper;


    @Transactional
    @Override
    public BeneficiaryResponse addNewBeneficiary(BeneficiaryEntity beneficiaryEntityRequest, int customerId, String accountNumber) throws Exception {

        BeneficiaryResponse beneficiaryResponse = new BeneficiaryResponse();
        AccountResponse  accountResponse = new AccountResponse();
        BeneficiaryEntity beneficiaryMasterEntity =  new BeneficiaryEntity();

        accountResponse = accountServiceFeignClient.getAccDetailsForFundTransferService(customerId, accountNumber);
        if(accountResponse != null) {
            beneficiaryEntityRequest.setCustomerId(customerId);
            beneficiaryEntityRequest.setAccountNumber(accountNumber);
        }
        beneficiaryEntityRequest.setBeneficiaryStatus(BeneficiaryStatus.PENDING);

        beneficiaryMasterEntity = iBeneficiaryMasterRepository.save(beneficiaryEntityRequest);
        beneficiaryResponse = beneficiaryMapper.mapBeneficiaryEntityToBeneficiaryResponse(beneficiaryMasterEntity);
        beneficiaryResponse.setAccountResponse(accountResponse);

        return beneficiaryResponse;
    }

    @Override
    public List<BeneficiaryResponse> getCustomerBeneficiaryListById(int customerId) {

        List<BeneficiaryResponse> beneficiaryResponseList = new ArrayList<>();
        List<BeneficiaryEntity> beneficiaryEntityList = new ArrayList<>();

        beneficiaryEntityList = iBeneficiaryMasterRepository.getCustomerBeneficiaryListById(customerId);
        beneficiaryResponseList = beneficiaryMapper.mapAuthEntityListToBeneficiaryResponseList(beneficiaryEntityList);
        return beneficiaryResponseList;
    }

}
