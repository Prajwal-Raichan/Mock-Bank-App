package com.merin.accountService.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.merin.accountService.dto.common.ProviderInfo;
import jakarta.persistence.Column;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class FixedDepositAccountResponse {

    private String accountNumber;

    private String accountBankName;

    private String accountHolderName;

    private String panNumber;

    private double accountBalance;

    private String accountType;

    private String accountStatus;

    private String accountBranchCode;

    private String accountBranchName;

    private String ifscCode;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime accountCreationDate;

    private CustomerDetailsResponse customerDetails;

    private int fixedDepositAccountId;

    private double fixedDepositInterestRate;

    private double fixedDepositAmount;

    private String fdTenure;

    private double fdMaturityAmount;

    private int fdInterestFrequency;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime fdMaturityDate;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime fdCreationDate;

    private LocalDate fdLastUpdatedDate;

    private LocalDate fdCloseDate;

    private String fdAccountStatus;

    private String responseMessage;

    private List<ProviderInfo> providerInfo;

    private List<TransactionResponse> fdTransactionList;
}
