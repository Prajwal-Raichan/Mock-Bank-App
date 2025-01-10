package com.merin.userService.mapper;

import com.merin.userService.dto.CustomerResponse;
import com.merin.userService.dto.accountService.CustomerDetailsResponse;
import com.merin.userService.entity.Customer;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CustomerMapper {

    public CustomerResponse mapCustomerEntityToCustomerResponse(Customer customer) {

        CustomerResponse customerResponse = new CustomerResponse();
        customerResponse.setCustomerName(customer.getCustomerName());
        customerResponse.setCustomerEmail(customer.getEmailId());
        customerResponse.setCustomerContact(customer.getMobileNumber());
        return customerResponse;
    }

    public List<CustomerResponse> mapCustomerEntityListToCustomerResponseList(List<Customer> customerList) {

        List<CustomerResponse> customerResponseList = new ArrayList<>();
        customerResponseList = customerList.stream()
                .map(this::mapCustomerEntityToCustomerResponse)
                .collect(Collectors.toList());
        return customerResponseList;
    }

    public CustomerDetailsResponse mapCustomerEntityToCustomerCustomerDetailsResponse(Customer customer) {

        CustomerDetailsResponse customerDetailsResponse = new CustomerDetailsResponse();
        customerDetailsResponse.setCustomerId(customer.getCustomerId());
        customerDetailsResponse.setCustomerName(customer.getCustomerName());
        customerDetailsResponse.setEmailId(customer.getEmailId());
        customerDetailsResponse.setMobileNumber(customer.getMobileNumber());
        customerDetailsResponse.setDob(customer.getDob());
        return customerDetailsResponse;
    }
}
