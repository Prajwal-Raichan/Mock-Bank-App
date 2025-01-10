package com.merin.loanService.service;

import com.merin.loanService.dto.LoanResponse;

import java.util.List;

public interface IManagerService {

    List<LoanResponse> getAllUnApprovedLoanRequests();

    LoanResponse authorizeLoanRequest(int loanId, int customerId);

    LoanResponse getLoanRequestById(int loanId);
}
