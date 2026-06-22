package com.merin.cloudGateway.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fallback")
public class FallbackController {

    @GetMapping("/userServiceFallback")
    public ResponseEntity<String> userServiceFallback() {
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                .body("Registration Service is currently unavailable. Please try again later.");
    }

    @GetMapping("/accountServiceFallback")
    public ResponseEntity<String> accountServiceFallback() {
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                .body("Account Service is currently unavailable. Please try again later.");
    }

    @GetMapping("/fundTransferServiceFallback")
    public ResponseEntity<String> fundTransferServiceFallback() {
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                .body("Fund Transfer Service is currently unavailable. Please try again later.");
    }

    @GetMapping("/loanServiceFallback")
    public ResponseEntity<String> loanServiceFallback() {
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                .body("Loan Service is currently unavailable. Please try again later.");
    }

    @GetMapping("/paymentServiceFallback")
    public ResponseEntity<String> paymentServiceFallback() {
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                .body("Payment Gateway is currently unavailable. Please try again later.");
    }

    @GetMapping("/cibilServiceFallback")
    public ResponseEntity<String> cibilServiceFallback() {
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                .body("Credit Rating Agency is currently unavailable. Please try again later.");
    }
}
