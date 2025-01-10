package com.merin.fundTransferService.controller;


import com.merin.fundTransferService.dto.BeneficiaryResponse;
import com.merin.fundTransferService.entity.BeneficiaryEntity;
import com.merin.fundTransferService.service.IBeneficiaryService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController()
@RequestMapping("/beneficiary")
@Tag(name = "Beneficiary-Service Controller",description = "Beneficiary-Service Management Portal")
public class BeneficiaryController {


    @Autowired
    private IBeneficiaryService beneficiaryService;




    @PostMapping("/addBeneficiary")
    public ResponseEntity<BeneficiaryResponse> addNewBeneficiary(@RequestBody BeneficiaryEntity beneficiaryEntity,
                                                                 @RequestParam("customerId") int customerId,
                                                                 @RequestParam("accountNumber") String accountNumber) throws Exception {

        BeneficiaryResponse beneficiaryResponse = new BeneficiaryResponse();
        beneficiaryResponse = beneficiaryService.addNewBeneficiary(beneficiaryEntity, customerId, accountNumber);

        return new ResponseEntity<>(beneficiaryResponse, HttpStatus.OK);
    }

    @GetMapping("/viewAuth/customerBeneficiaryList")
    public ResponseEntity<List<BeneficiaryResponse>> getCustomerBeneficiaryList(@RequestParam("customerId")int customerId) {

        List<BeneficiaryResponse> beneficiaryResponse = new ArrayList<>();
        beneficiaryResponse = beneficiaryService.getCustomerBeneficiaryListById(customerId);

        return new ResponseEntity<>(beneficiaryResponse, HttpStatus.OK);

    }



}
