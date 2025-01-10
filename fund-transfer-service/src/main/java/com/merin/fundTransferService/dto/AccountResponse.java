package com.merin.fundTransferService.dto;

import com.merin.fundTransferService.dto.common.ProviderInfo;
import lombok.*;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class AccountResponse {


    private String accountHolderName;

    private String accountNumber;

    private double accountBalance;

    private String accountType;

    private String accountStatus;

    private String responseMessage;

    private List<ProviderInfo> providerInfo;

    public static class FundTransferResponse {
    }
}
