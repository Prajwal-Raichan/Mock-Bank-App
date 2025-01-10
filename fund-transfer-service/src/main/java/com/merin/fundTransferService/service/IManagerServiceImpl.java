package com.merin.fundTransferService.service;

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
public class IManagerServiceImpl implements IManagerService {



    @Autowired
    private IBeneficiaryMasterRepository masterRepository;

    @Autowired
    private BeneficiaryMapper beneficiaryMapper;

    @Override
    public List<BeneficiaryResponse> gellUnAuthorizedBeneficiaryList() {

        List<BeneficiaryResponse> beneficiaryResponseList = new ArrayList<>();
        List<BeneficiaryEntity> beneficiaryMasterHist = new ArrayList<>();
        beneficiaryMasterHist = masterRepository.findAll();
        beneficiaryResponseList = beneficiaryMapper.mapUnAuthEntityListToBeneficiaryResponseList(beneficiaryMasterHist);
        return beneficiaryResponseList;
    }

    @Transactional
    @Override
    public List<BeneficiaryResponse> bulkAuthorizeBeneficiaryList() {

        List<BeneficiaryResponse> beneficiaryResponseList = new ArrayList<>();
        List<BeneficiaryEntity> beneficiaryHistList = masterRepository.findAll();

        List<BeneficiaryEntity> beneficiaryMaster = beneficiaryMapper.authorizeBeneficiaryHistEntityList(beneficiaryHistList);

        beneficiaryMaster = masterRepository.saveAll(beneficiaryMaster);

        beneficiaryResponseList = beneficiaryMapper.mapEntityListToBeneficiaryResponseList(beneficiaryMaster);

        return beneficiaryResponseList;
    }


    @Override
    public List<BeneficiaryResponse> getAllAuthorizeBeneficiaryList() {

        List<BeneficiaryResponse> beneficiaryResponseList = new ArrayList<>();
        List<BeneficiaryEntity> beneficiaryMasterHist = new ArrayList<>();
        beneficiaryMasterHist = masterRepository.findAll();
        beneficiaryResponseList = beneficiaryMapper.mapAuthEntityListToBeneficiaryResponseList(beneficiaryMasterHist);
        return beneficiaryResponseList;
    }

    @Override
    public BeneficiaryResponse rejectBeneficiaryAccount(int beneficiaryId) {

        BeneficiaryResponse beneficiaryResponse = new BeneficiaryResponse();
        BeneficiaryEntity beneficiaryEntity = new BeneficiaryEntity();

        beneficiaryEntity =  masterRepository.findById(beneficiaryId).get();
        beneficiaryEntity.setBeneficiaryStatus(BeneficiaryStatus.REJECTED);
        beneficiaryEntity =  masterRepository.save(beneficiaryEntity);
        beneficiaryResponse = beneficiaryMapper.mapBeneficiaryEntityToBeneficiaryResponse(beneficiaryEntity);
        return beneficiaryResponse;
    }
}
