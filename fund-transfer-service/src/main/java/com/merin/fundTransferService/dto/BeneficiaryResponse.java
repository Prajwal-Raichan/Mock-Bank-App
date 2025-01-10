package com.merin.fundTransferService.dto;

import com.merin.fundTransferService.dto.common.ProviderInfo;
import com.merin.fundTransferService.enums.BeneficiaryStatus;
import jakarta.persistence.Column;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class BeneficiaryResponse {

    private int beneficiaryId;

    private String beneficiaryName;

    private int customerId;

    private String beneficiaryAccountNumber;

    private String beneficiaryIfscCode;

    private String relationship;

    private BeneficiaryStatus beneficiaryStatus;

    private String beneficiaryEmail;

    private AccountResponse accountResponse;

    private String responseMessage;

    private List<ProviderInfo> providerInfo;

}
