package com.merin.fundTransferService.controller;

import com.merin.fundTransferService.dto.BeneficiaryResponse;
import com.merin.fundTransferService.service.IManagerService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController()
@RequestMapping("/manager")
@Tag(name = "Manager-Service Controller",description = "Manager-Service Management Portal")
public class ManagerController {

    @Autowired
    private IManagerService iManagerService;



    @GetMapping("/unAuthorized/beneficiaryList")
    public ResponseEntity<List<BeneficiaryResponse>> gellAllUnAuthorizedBeneficiaryList() {

        List<BeneficiaryResponse> beneficiaryResponse = new ArrayList<>();
        beneficiaryResponse = iManagerService.gellUnAuthorizedBeneficiaryList();

        return new ResponseEntity<>(beneficiaryResponse, HttpStatus.OK);

    }

    @GetMapping("/bulkAuth")
    public ResponseEntity<List<BeneficiaryResponse>> bulkAuthorizeBeneficiaryList() {

        List<BeneficiaryResponse> beneficiaryResponse = new ArrayList<>();
        beneficiaryResponse = iManagerService.bulkAuthorizeBeneficiaryList();

        return new ResponseEntity<>(beneficiaryResponse, HttpStatus.OK);

    }

    @GetMapping("/viewAuth/beneficiaryList")
    public ResponseEntity<List<BeneficiaryResponse>> getAuthorizeBeneficiaryList() {

        List<BeneficiaryResponse> beneficiaryResponse = new ArrayList<>();
        beneficiaryResponse = iManagerService.getAllAuthorizeBeneficiaryList();

        return new ResponseEntity<>(beneficiaryResponse, HttpStatus.OK);

    }

    @PutMapping("/reject/beneficiaryAccount")
    public ResponseEntity<BeneficiaryResponse> rejectBeneficiaryAccount(@RequestParam("beneficiaryId")int beneficiaryId) {

        BeneficiaryResponse beneficiaryResponse = new BeneficiaryResponse();
        beneficiaryResponse = iManagerService.rejectBeneficiaryAccount(beneficiaryId);

        return new ResponseEntity<>(beneficiaryResponse, HttpStatus.OK);

    }
}


