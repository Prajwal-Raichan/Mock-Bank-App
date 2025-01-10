package com.merin.fundTransferService.dto;

import com.merin.fundTransferService.dto.common.ProviderInfo;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ManagerResponse {

    private int managerId;

    private String managerName;

    private String managerEmail;

    private String managerContact;

    private String responseMessage;

    private List<ProviderInfo> providerInfo;
}