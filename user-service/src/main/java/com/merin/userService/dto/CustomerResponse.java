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
public class CustomerResponse {

    private String customerName;

    private String customerEmail;

    private String customerContact;

    private String responseMessage;

    private List<ProviderInfo> providerInfo;


}
