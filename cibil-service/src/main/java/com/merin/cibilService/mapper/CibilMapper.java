package com.merin.cibilService.mapper;

import com.merin.cibilService.dto.CibilResponse;
import com.merin.cibilService.entity.CibilEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CibilMapper {

    public CibilResponse mapCibilEntityToCibilResponse(CibilEntity cibilEntity) {

        CibilResponse cibilResponse = new CibilResponse();
        cibilResponse = CibilResponse.builder()
                .cibilId(cibilEntity.getCibilId())
                .cibilScore(cibilEntity.getCibilScore())
                .cibilStatus(cibilEntity.getCibilStatus())
                .panNumber(cibilEntity.getPanNumber())
                .lastUpdatedDate(cibilEntity.getLastUpdatedDate())
                .build();

        return cibilResponse;
    }

    public List<CibilResponse> mapCibilEntityListToCibilResponseList(List<CibilEntity> cibilEntityList) {

        List<CibilResponse> cibilResponseList = new ArrayList<>();

        cibilResponseList = cibilEntityList.stream()
                .map(this::mapCibilEntityToCibilResponse)
                .collect(Collectors.toList());

        return cibilResponseList;

    }

}
