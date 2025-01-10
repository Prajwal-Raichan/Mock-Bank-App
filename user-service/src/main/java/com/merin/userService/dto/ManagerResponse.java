package com.merin.userService.dto;

import com.merin.userService.dto.common.ProviderInfo;
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