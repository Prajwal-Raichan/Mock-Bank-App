package com.merin.fundTransferService.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.merin.fundTransferService.dto.common.ProviderInfo;
import com.merin.fundTransferService.entity.BeneficiaryEntity;
import com.merin.fundTransferService.enums.TransferMode;
import com.merin.fundTransferService.enums.TransferStatus;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class FundTransferResponse {

    private Long transferId;

    private int customerId;

    private BeneficiaryEntity beneficiary;

    private double transferAmount;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime transferDate;

    private TransferStatus transferStatus;

    private TransferMode transferMode;

    private String remarks;

    private String responseMessage;

    private List<ProviderInfo> providerInfo;

}
