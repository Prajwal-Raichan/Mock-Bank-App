package com.merin.accountService.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.merin.accountService.dto.common.ProviderInfo;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class SavingsAccountResponse {

    private Long savingsAccountId;

    private String accountNumber;

    private String accountBankName;

    private String accountHolderName;

    private String panNumber;

    private double accountBalance;

    private String accountType;

    private double savingsAccountInterestRate;

    private String accountStatus;

    private String accountBranchCode;

    private String accountBranchName;

    private String ifscCode;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime accountCreationDate;

    private CustomerDetailsResponse customerDetails;

    private List<TransactionResponse> saTransactionList;

    private String responseMessage;

    private List<ProviderInfo> providerInfo;

}
