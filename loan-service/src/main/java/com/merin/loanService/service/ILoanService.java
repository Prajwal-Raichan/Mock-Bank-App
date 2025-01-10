package com.merin.loanService.service;

import com.merin.loanService.dto.LoanResponse;
import com.merin.loanService.entity.LoanEntity;

import java.util.List;

public interface ILoanService {

    LoanResponse createNewLoanRequest(LoanEntity loanEntity, int customerId, String accountNumber);

    LoanResponse revokeLoanRequest(int loanId, int customerId);

    LoanResponse getLoanRequestById(int loanId);

    List<LoanResponse> getAllLoanRequestByCustomerId(int customerId);
}
