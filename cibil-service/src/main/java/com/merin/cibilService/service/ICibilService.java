package com.merin.cibilService.service;

import com.merin.cibilService.dto.CibilResponse;
import com.merin.cibilService.entity.CibilEntity;

public interface ICibilService {

    CibilResponse addNewCibilScoreRecord(CibilEntity cibilRequest);

    CibilResponse getCibilScoreRecord(String panNumber);

    Integer getCibilScoreForExternalServices(String panNumber);
}
