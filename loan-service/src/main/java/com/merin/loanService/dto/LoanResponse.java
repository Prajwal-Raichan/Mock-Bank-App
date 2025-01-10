package com.merin.loanService.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.merin.loanService.dto.common.ProviderInfo;
import com.merin.loanService.enums.LoanStatus;
import com.merin.loanService.enums.LoanType;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

import java.security.Provider;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class LoanResponse {

    private Long loanId;

    private int customerId;

    private String loanAccountNumber;

    private LoanType loanType;

    private double loanAmount;

    private int cibilScore;

    private double interestRate;

    private int loanTerm;

    private LocalDate loanCreationDate;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate startDate;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate endDate;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private double outstandingAmount;

    private LoanStatus loanStatus;

    private String remarks;

    private String responseMessage;

    private List<ProviderInfo> providerInfo;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private AccountResponse accountResponse;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private CustomerDetailsResponse customerDetailsResponse;
}
