package com.merin.userService.service.customer;

import com.merin.userService.dto.CustomerResponse;
import com.merin.userService.dto.accountService.CustomerDetailsResponse;
import com.merin.userService.entity.Customer;
import com.merin.userService.mapper.CustomerMapper;
import com.merin.userService.repository.ICustomerRepository;
import com.merin.userService.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ICustomerServiceImpl implements ICustomerService {

    @Autowired
    private ICustomerRepository customerRepository;

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private CustomerMapper customerMapper;

    @Override
    public List<CustomerResponse> getAllCustomers(){

        List<CustomerResponse> customerResponseList = new ArrayList<>();
        List<Customer> customerList = new ArrayList<>();
        customerList = customerRepository.findAll();
        customerResponseList = customerMapper.mapCustomerEntityListToCustomerResponseList(customerList);
        return customerResponseList;
    }

    @Override
    public CustomerResponse removeCustomer(int customerId) {

        CustomerResponse customerResponse = new CustomerResponse();
        Optional<Customer> customerOptional = customerRepository.findById(customerId);

        if (customerOptional.isPresent()) {
            Customer customer = customerOptional.get();
            customerResponse.setCustomerName(customer.getCustomerName());
            customerRepository.deleteById(customerId);
            userRepository.deleteById(customerId);

            boolean deleteStatus = !customerRepository.existsById(customerId);
            customerResponse.setResponseMessage(deleteStatus ? "Customer Deleted Successfully!!" : "Failed to Delete Customer!!");
        } else {
            customerResponse.setResponseMessage("Customer not found with ID: " + customerId);
        }
        return customerResponse;
    }

    @Override
    public CustomerResponse getCustomerById(int customerId)  {

        CustomerResponse customerResponse = new CustomerResponse();
        Customer customer = new Customer();
        customer = customerRepository.findById(customerId).get();
        customerResponse = customerMapper.mapCustomerEntityToCustomerResponse(customer);
        return customerResponse;
    }

    @Override
    public CustomerResponse checkForExistance(String userName) {
        CustomerResponse customerResponse = new CustomerResponse();
        Long existStatus = customerRepository.getByUserName(userName);
        customerResponse.setCustomerName(userName);
        customerResponse.setResponseMessage(existStatus > 0 ? "Customer Exist" : "Customer Doesn't Exist");
        return customerResponse;
    }

    @Override
    public CustomerDetailsResponse getCustomerDetailsForAccountService(int customerId) {

        CustomerDetailsResponse customerDetailsResponse = new CustomerDetailsResponse();
        Customer customer = new Customer();
        customer = customerRepository.findById(customerId).get();
        customerDetailsResponse = customerMapper.mapCustomerEntityToCustomerCustomerDetailsResponse(customer);

        return customerDetailsResponse;
    }
}
