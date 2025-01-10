package com.merin.fundTransferService.mapper;

import com.merin.fundTransferService.dto.BeneficiaryResponse;
import com.merin.fundTransferService.entity.BeneficiaryEntity;
import com.merin.fundTransferService.enums.BeneficiaryStatus;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class BeneficiaryMapper {


    public BeneficiaryResponse mapBeneficiaryEntityToBeneficiaryResponse(BeneficiaryEntity beneficiaryEntity) {

        BeneficiaryResponse beneficiaryResponse = new BeneficiaryResponse();
        beneficiaryResponse.setBeneficiaryId(beneficiaryEntity.getBeneficiaryId());
        beneficiaryResponse.setBeneficiaryAccountNumber(beneficiaryEntity.getBeneficiaryAccountNumber());
        beneficiaryResponse.setBeneficiaryName(beneficiaryEntity.getBeneficiaryName());
        beneficiaryResponse.setBeneficiaryIfscCode(beneficiaryEntity.getBeneficiaryIfscCode());
        beneficiaryResponse.setBeneficiaryEmail(beneficiaryEntity.getBeneficiaryEmail());
        beneficiaryResponse.setBeneficiaryStatus(beneficiaryEntity.getBeneficiaryStatus());
        beneficiaryResponse.setRelationship(beneficiaryEntity.getRelationship());
        beneficiaryResponse.setCustomerId(beneficiaryEntity.getCustomerId());

        return beneficiaryResponse;

    }

    public List<BeneficiaryResponse> mapEntityListToBeneficiaryResponseList( List<BeneficiaryEntity> beneficiaryEntityList) {

        return beneficiaryEntityList
                .stream()
                .map(this::mapBeneficiaryEntityToBeneficiaryResponse)
                .collect(Collectors.toList());
    }

    public List<BeneficiaryResponse> mapUnAuthEntityListToBeneficiaryResponseList(List<BeneficiaryEntity> beneficiaryEntityList) {

        List<BeneficiaryResponse> beneficiaryResponseList = new ArrayList<>();

        return beneficiaryEntityList.stream()
                .filter(entity -> entity.getBeneficiaryStatus() == BeneficiaryStatus.UNAUTHORIZED ||
                        entity.getBeneficiaryStatus() == BeneficiaryStatus.PENDING) // Only collect approved entities
                .map(this::mapBeneficiaryEntityToBeneficiaryResponse) // Map HistEntity to MasterEntity
                .collect(Collectors.toList()); // Collect and return mapped entities
    }

    public List<BeneficiaryResponse> mapAuthEntityListToBeneficiaryResponseList(List<BeneficiaryEntity> beneficiaryEntityList) {

        List<BeneficiaryResponse> beneficiaryResponseList = new ArrayList<>();
        beneficiaryResponseList = beneficiaryEntityList.stream()
                .filter(entity -> entity.getBeneficiaryStatus() == BeneficiaryStatus.AUTHORIZED) // Only collect approved entities
                .map(this::mapBeneficiaryEntityToBeneficiaryResponse) // Map HistEntity to MasterEntity
                .collect(Collectors.toList());

        return beneficiaryResponseList;
    }

    public List<BeneficiaryEntity> authorizeBeneficiaryHistEntityList(List<BeneficiaryEntity> beneficiaryMasterHistEntityList) {

        return beneficiaryMasterHistEntityList.stream()
                .filter(entity -> entity.getBeneficiaryStatus() == BeneficiaryStatus.PENDING ||
                        entity.getBeneficiaryStatus() == BeneficiaryStatus.UNAUTHORIZED) // Filter by status
                .peek(entity -> {
                    entity.setBeneficiaryStatus(BeneficiaryStatus.AUTHORIZED); // Update status
                })
                .collect(Collectors.toList());
    }


}




