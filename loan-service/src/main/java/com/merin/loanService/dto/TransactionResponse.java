package com.merin.loanService.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.merin.loanService.enums.TransactionMode;
import com.merin.loanService.enums.TransactionStatus;
import com.merin.loanService.enums.TransactionType;
import lombok.*;

import java.time.LocalDateTime;

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

    private AccountResponse accountResponse;


}
