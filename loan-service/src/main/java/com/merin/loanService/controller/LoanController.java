package com.merin.loanService.controller;

import com.merin.loanService.dto.LoanResponse;
import com.merin.loanService.entity.LoanEntity;
import com.merin.loanService.service.ILoanService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/loan")
@Tag(name = "Loan-Service Controller",description = "Loan-Service Management Portal")
public class LoanController {


    @Autowired
    private ILoanService loanService;

    @PostMapping("/fileNew/loanRequest")
    public ResponseEntity<LoanResponse> createNewLoanRequest(@RequestBody LoanEntity loanEntity,
                                                             @RequestParam("customerId") int customerId,
                                                             @RequestParam("accountNumber") String accountNumber) {

        LoanResponse loanResponse = new LoanResponse();
        loanResponse = loanService.createNewLoanRequest(loanEntity, customerId, accountNumber );

        return new ResponseEntity<>(loanResponse, HttpStatus.OK);
    }

    @GetMapping("/viewLoanRequest/byLoanId")
    public ResponseEntity<LoanResponse> getLoanRequestById(@RequestParam("loanId") int loanId) {

        LoanResponse loanResponse = new LoanResponse();
        loanResponse = loanService.getLoanRequestById(loanId);

        return new ResponseEntity<>(loanResponse, HttpStatus.OK);
    }

    @GetMapping("/viewAllLoanRequests/byCustomerId")
    public ResponseEntity<List<LoanResponse>> getAllLoanRequestByCustomerId(@RequestParam("customerId") int customerId) {

        List<LoanResponse> loanResponseList = new ArrayList<>();
        loanResponseList = loanService.getAllLoanRequestByCustomerId(customerId);

        return new ResponseEntity<>(loanResponseList, HttpStatus.OK);
    }

    @DeleteMapping("/revoke/loanRequest")
    public ResponseEntity<LoanResponse> revokeLoanRequest(@RequestParam("loanId") int loanId,
                                                          @RequestParam("customerId") int customerId) {

        LoanResponse loanResponse = new LoanResponse();
        loanResponse = loanService.revokeLoanRequest(loanId, customerId);
        return new ResponseEntity<>(loanResponse, HttpStatus.OK);
    }
}
