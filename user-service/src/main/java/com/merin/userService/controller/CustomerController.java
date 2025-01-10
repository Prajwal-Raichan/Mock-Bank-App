package com.merin.userService.controller;

import com.merin.userService.dto.CustomerResponse;
import com.merin.userService.dto.accountService.CustomerDetailsResponse;
import com.merin.userService.service.customer.ICustomerService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Tag(name = "Customer Controller",description = "Customer Management Portal")
@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private ICustomerService customerService;

    @GetMapping("/getAllCustomers")
    public ResponseEntity<List<CustomerResponse>> fetchAllCustomers() {

        List<CustomerResponse> customerResponseList = new ArrayList<>();
        customerResponseList = customerService.getAllCustomers();
        return new ResponseEntity<>(customerResponseList, HttpStatus.OK);
    }

    @GetMapping("/getCustomerById")
    public ResponseEntity<CustomerResponse> getCustomerById(@RequestParam("customerId") int customerId) {

        CustomerResponse customerResponse = new CustomerResponse();
        customerResponse = customerService.getCustomerById(customerId);
        return new ResponseEntity<>(customerResponse, HttpStatus.OK);
    }

    @GetMapping("/existenceCheck")
    public ResponseEntity<CustomerResponse> checkForExistance(@RequestParam("userName") String userName) {

        CustomerResponse customerResponse = new CustomerResponse();
        customerResponse = customerService.checkForExistance(userName);
        return new ResponseEntity<>(customerResponse, HttpStatus.OK);
    }

    @GetMapping("/getCustomerDetails/accountService")
    ResponseEntity<CustomerDetailsResponse> getCustomerDetailsForAccountService(@RequestParam("customerId") int customerId) {

        CustomerDetailsResponse customerDetailsResponse = new CustomerDetailsResponse();
        customerDetailsResponse = customerService.getCustomerDetailsForAccountService(customerId);

        return new ResponseEntity<>(customerDetailsResponse, HttpStatus.OK);
    }

    @GetMapping("/getCustomerDetails/laonService")
    ResponseEntity<CustomerDetailsResponse> getCustomerDetailsForLoanService(@RequestParam("customerId") int customerId) {

        CustomerDetailsResponse customerDetailsResponse = new CustomerDetailsResponse();
        customerDetailsResponse = customerService.getCustomerDetailsForAccountService(customerId);

        return new ResponseEntity<>(customerDetailsResponse, HttpStatus.OK);
    }

}
