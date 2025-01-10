package com.merin.accountService.client.feignClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("payment-service")
public interface IPaymentServiceFeignClient {

    @GetMapping("/payment/validatePaymentStatus")
    Boolean validatePaymentStatusFromPaymentService(@RequestParam("paymentId") int paymentId,
                                                    @RequestParam("paymentAmount") double paymentAmount);
}
