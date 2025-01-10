package com.merin.userService.service.customer;

import com.merin.userService.dto.CustomerResponse;
import com.merin.userService.dto.accountService.CustomerDetailsResponse;

import java.util.List;

public interface ICustomerService {

    List<CustomerResponse> getAllCustomers();

    CustomerResponse getCustomerById(int customerId);

    CustomerResponse checkForExistance(String userName);

    CustomerResponse removeCustomer(int customerId);

    /* Account-Service Client Calls Start*/
    CustomerDetailsResponse getCustomerDetailsForAccountService(int customerId);
    /* Account-Service Client Calls End*/
}
