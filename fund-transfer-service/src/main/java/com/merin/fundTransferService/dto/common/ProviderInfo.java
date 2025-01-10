package com.merin.fundTransferService.dto.common;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class ProviderInfo {

    private String detail;

    private String fiedlId;

    private String code;
}
