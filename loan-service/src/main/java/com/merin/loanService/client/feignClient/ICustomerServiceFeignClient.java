package com.merin.loanService.client.feignClient;

import com.merin.loanService.dto.CustomerDetailsResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("USER-SERVICE")
public interface ICustomerServiceFeignClient {

    @GetMapping("/customer/getCustomerDetails/laonService")
    CustomerDetailsResponse CusgetCustomerDetailsForLoanService(@RequestParam("customerId") int customerId);
}
