package com.merin.accountService.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.merin.accountService.dto.common.ProviderInfo;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.JoinColumn;
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

    private String panNumber;

    private double accountBalance;

    private String accountType;

    private String accountStatus;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<Integer> loansOnAccount;

    private String responseMessage;

    private List<ProviderInfo> providerInfo;
}
