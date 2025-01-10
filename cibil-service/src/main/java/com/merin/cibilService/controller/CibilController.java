package com.merin.cibilService.controller;

import com.merin.cibilService.dto.CibilResponse;
import com.merin.cibilService.entity.CibilEntity;
import com.merin.cibilService.service.ICibilService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cibil")
@Tag(name = " Cibil-Service Controller",description = " Cibil-Service Management Portal")
public class CibilController {

    @Autowired
    private ICibilService cibilService;


    @PostMapping("/api/addNewCibilRecord")
    public ResponseEntity<CibilResponse> addNewCibilScoreRecord(@RequestBody CibilEntity cibilRequest) {

        CibilResponse cibilResponse = new CibilResponse();
        cibilResponse = cibilService.addNewCibilScoreRecord(cibilRequest);
        return new ResponseEntity<>(cibilResponse, HttpStatus.OK);
    }

    @GetMapping("/api/getCibilRecord")
    public ResponseEntity<CibilResponse> getCibilScoreRecord(@RequestParam("panNumber") String panNumber) {

        CibilResponse cibilResponse = new CibilResponse();
        cibilResponse = cibilService.getCibilScoreRecord(panNumber);
        return new ResponseEntity<>(cibilResponse, HttpStatus.OK);
    }

    @GetMapping("/api/getCibilScore")
    public ResponseEntity<Integer> getCibilScoreForExternalServices(@RequestParam("panNumber") String panNumber) {

        Integer cibilScore = null;
        cibilScore = cibilService.getCibilScoreForExternalServices(panNumber);
        return new ResponseEntity<>(cibilScore, HttpStatus.OK);
    }

}
