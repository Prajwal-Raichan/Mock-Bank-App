package com.merin.loanService.client.feignClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("CIBIL-SERVICE")
public interface ICibilScoreFeignClient {

    @GetMapping("/cibil/api/getCibilScore")
    Integer getCibilScoreForExternalServices(@RequestParam("panNumber") String panNumber);
}
