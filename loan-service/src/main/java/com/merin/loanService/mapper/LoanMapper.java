package com.merin.loanService.mapper;

import com.merin.loanService.dto.LoanResponse;
import com.merin.loanService.entity.LoanEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class LoanMapper {

    public LoanResponse mapLoanEntityToLoanResponse(LoanEntity loanEntity) {

        LoanResponse loanResponse = new LoanResponse();
        loanResponse = LoanResponse.builder()
                .loanId(loanEntity.getLoanId())
                .loanAmount(loanEntity.getLoanAmount())
                .loanStatus(loanEntity.getLoanStatus())
                .loanTerm(loanEntity.getLoanTerm())
                .loanType(loanEntity.getLoanType())
                .loanAccountNumber(loanEntity.getLoanAccountNumber())
                .cibilScore(loanEntity.getCibilScore())
                .customerId(loanEntity.getCustomerId())
                .endDate(loanEntity.getEndDate())
                .interestRate(loanEntity.getInterestRate())
                .loanCreationDate(loanEntity.getLoanCreationDate())
                .outstandingAmount(loanEntity.getOutstandingAmount())
                .remarks(loanEntity.getRemarks()).build();

        return loanResponse;
    }

    public List<LoanResponse> mapLoanEntityListToLoanResponseList(List<LoanEntity> loanEntityList) {

        List<LoanResponse> loanResponseList = new ArrayList<>();
        loanResponseList = loanEntityList
                .stream()
                .map(this::mapLoanEntityToLoanResponse)
                .collect(Collectors.toList());

        return loanResponseList;
    }

}
