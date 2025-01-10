package com.merin.loanService.controller;

import com.merin.loanService.constant.LoanConstants;
import com.merin.loanService.dto.LoanResponse;
import com.merin.loanService.service.IManagerService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/manager")
@Tag(name = "Manager Controller",description = "Manager Management Portal")
public class ManagerController {

    @Autowired
    private IManagerService managerService;

    @GetMapping("/viewUnaApprovedLoanRequests")
    public ResponseEntity<List<LoanResponse>> getAllUnApprovedLoanRequests() {


        List<LoanResponse> loanResponseList = new ArrayList<>();
        loanResponseList = managerService.getAllUnApprovedLoanRequests();
        return new ResponseEntity<>(loanResponseList, HttpStatus.OK);
    }

    @GetMapping("/viewLoanRequest/byLoanId")
    public ResponseEntity<LoanResponse> getLoanRequestById(@RequestParam("loanId") int loanId) {

        LoanResponse loanResponse = new LoanResponse();
        loanResponse = managerService.getLoanRequestById(loanId);

        return new ResponseEntity<>(loanResponse, HttpStatus.OK);
    }

    @PostMapping("/authorize/loanRequest")
    public ResponseEntity<LoanResponse> authorizeLoanRequest(@RequestParam("loanId")int loanId,
                                                             @RequestParam("customerId")int customerId) {


        log.info(LoanConstants.INSIDE_CONTROLLER + "authorizeLoanRequest");
        LoanResponse loanResponse = new LoanResponse();
        loanResponse = managerService.authorizeLoanRequest(loanId, customerId);

        return new ResponseEntity<>(loanResponse, HttpStatus.OK);
    }


}
