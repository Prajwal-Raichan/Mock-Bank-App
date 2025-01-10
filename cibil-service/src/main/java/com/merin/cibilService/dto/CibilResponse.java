package com.merin.cibilService.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.merin.cibilService.dto.common.ProviderInfo;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CibilResponse {

    private Long cibilId;

    private String panNumber;

    private int cibilScore;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate lastUpdatedDate;

    private String cibilStatus;

    private String responseMessage;

    private List<ProviderInfo> providerInfo;

}
