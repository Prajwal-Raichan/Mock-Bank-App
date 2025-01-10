package com.merin.accountService.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.merin.accountService.dto.common.ProviderInfo;
import jakarta.persistence.Column;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CurrentAccountResponse {

    private Long currentAccountId;

    private String accountNumber;

    private String accountBankName;

    private String accountHolderName;

    private String panNumber;

    private double accountBalance;

    private double currentAccountInterestRate;

    private double overdraftLimit;

    private String accountType;

    private String accountStatus;

    private String accountBranchCode;

    private String accountBranchName;

    private String ifscCode;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime accountCreationDate;

    private CustomerDetailsResponse customerDetails;

    private List<TransactionResponse> cATransactionList;

    private String responseMessage;

    private List<ProviderInfo> providerInfo;
}
