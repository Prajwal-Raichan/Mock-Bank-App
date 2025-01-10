package com.merin.accountService.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.merin.accountService.dto.common.ProviderInfo;
import com.merin.accountService.entity.AccountEntity;
import com.merin.accountService.enums.TransactionMode;
import com.merin.accountService.enums.TransactionStatus;
import com.merin.accountService.enums.TransactionType;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class TransactionResponse {

    private int id;

    private String transactionNumber;

    private TransactionType transactionType;

    private TransactionStatus transactionStatus;

    private TransactionMode transactionMode;

    private double transactionAmount;

    private double totalAccountBalance;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime transactionDate;

    private String transactionDescription;

    private AccountEntity accountEntity;

    private CustomerDetailsResponse customerDetailsResponse;

    private String responseMessage;

    private List<ProviderInfo> providerInfo;

}
