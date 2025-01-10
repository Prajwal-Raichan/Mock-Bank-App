package com.merin.fundTransferService.controller;

import com.merin.fundTransferService.dto.FundTransferResponse;
import com.merin.fundTransferService.entity.FundTransferEntity;
import com.merin.fundTransferService.service.IFundTransferService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/fundTransfer")
@Tag(name = "FundTransfer-Service Controller",description = "FundTransfer-Service Management Portal")
public class FundTransferController {


    @Autowired
    private IFundTransferService fundTransferService;

    @PostMapping("/transfer/toBeneficiary")
    public ResponseEntity<FundTransferResponse> transferFund(@RequestBody FundTransferEntity fundTransferRequest,
                                                             @RequestParam("customerId") int customerId,
                                                             @RequestParam("accountNumber") String accountNumber,
                                                             @RequestParam("beneficiaryId") int beneficiaryId,
                                                             @RequestParam("paymentId") int paymentId,
                                                             @RequestParam("transferMode") String transferMode,
                                                             @RequestParam("transferAmount") double transferAmount) {

        FundTransferResponse fundTransferResponse =  new FundTransferResponse();
        fundTransferResponse = fundTransferService.transferFund(fundTransferRequest, customerId, accountNumber, beneficiaryId, paymentId, transferMode, transferAmount);
        return new ResponseEntity<>(fundTransferResponse,HttpStatus.OK);
    }

}
