package com.merin.loanService.dto;


import com.merin.loanService.dto.common.ProviderInfo;
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

}
