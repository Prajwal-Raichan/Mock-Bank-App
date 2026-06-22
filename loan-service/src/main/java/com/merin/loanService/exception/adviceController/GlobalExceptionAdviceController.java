package com.merin.loanService.exception.adviceController;

import com.merin.loanService.dto.LoanResponse;
import com.merin.loanService.dto.common.ProviderInfo;
import com.merin.loanService.exception.CustomerNotFoundException;
import com.merin.loanService.exception.LoanNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@ControllerAdvice
public class GlobalExceptionAdviceController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGlobalException(Exception ex, WebRequest request) {
        return new ResponseEntity<>("An error occurred: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = {CustomerNotFoundException.class})
    public ResponseEntity<LoanResponse> validationException(CustomerNotFoundException ex) {
        LoanResponse consumerResponse = new LoanResponse();
        List<ProviderInfo> providerInfo = new ArrayList<>();
        ProviderInfo info = new ProviderInfo();
        info.setDetail(ex.getMessage());
        info.setCode(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()));
        providerInfo.add(info);
        consumerResponse.setProviderInfo(providerInfo);
        return new ResponseEntity<>(consumerResponse, HttpStatus.OK);
    }

    @ExceptionHandler(value = {LoanNotFoundException.class})
    public ResponseEntity<LoanResponse> validationException(LoanNotFoundException ex) {
        LoanResponse consumerResponse = new LoanResponse();
        List<ProviderInfo> providerInfo = new ArrayList<>();
        ProviderInfo info = new ProviderInfo();
        info.setDetail(ex.getMessage());
        info.setCode(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()));
        providerInfo.add(info);
        consumerResponse.setProviderInfo(providerInfo);
        return new ResponseEntity<>(consumerResponse, HttpStatus.OK);
    }

}
