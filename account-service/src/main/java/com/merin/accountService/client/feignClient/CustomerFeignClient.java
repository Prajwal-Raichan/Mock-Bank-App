package com.merin.accountService.client.feignClient;

import com.merin.accountService.dto.CustomerDetailsResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("user-service")
public interface CustomerFeignClient {

    @GetMapping("/customer/getCustomerDetails/accountService")
    CustomerDetailsResponse getCustomerDetailsForAccountService(@RequestParam("customerId") int customerId);
}
