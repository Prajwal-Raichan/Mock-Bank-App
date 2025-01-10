package com.merin.fundTransferService.mapper;

import com.merin.fundTransferService.dto.FundTransferResponse;
import com.merin.fundTransferService.entity.FundTransferEntity;
import org.springframework.stereotype.Component;

@Component
public class FundTransferMapper {

    public FundTransferResponse mapFundTransferEntityToFundTransferResponse(FundTransferEntity fundTransferEntity) {

        FundTransferResponse fundTransferResponse = new FundTransferResponse();
        fundTransferResponse = FundTransferResponse.builder()
                .transferId(fundTransferEntity.getTransferId())
                .transferAmount(fundTransferEntity.getTransferAmount())
                .transferDate(fundTransferEntity.getTransferDate())
                .transferMode(fundTransferEntity.getTransferMode())
                .transferAmount(fundTransferEntity.getTransferAmount())
                .transferStatus(fundTransferEntity.getTransferStatus())
                .beneficiary(fundTransferEntity.getBeneficiary())
                .customerId(fundTransferEntity.getCustomerId())
                .remarks(fundTransferEntity.getRemarks())
                .build();

        return fundTransferResponse;
    }
}
