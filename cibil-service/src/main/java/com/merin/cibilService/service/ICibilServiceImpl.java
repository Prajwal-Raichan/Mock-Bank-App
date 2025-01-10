package com.merin.cibilService.service;

import com.merin.cibilService.constants.CibilConstants;
import com.merin.cibilService.dto.CibilResponse;
import com.merin.cibilService.entity.CibilEntity;
import com.merin.cibilService.mapper.CibilMapper;
import com.merin.cibilService.repository.ICibilRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ICibilServiceImpl implements ICibilService {


    @Autowired
    private ICibilRepository cibilRepository;

    @Autowired
    private CibilMapper cibilMapper;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Transactional
    @Override
    public CibilResponse addNewCibilScoreRecord(CibilEntity cibilRequest) {

        CibilResponse cibilResponse = new CibilResponse();
        CibilEntity cibilEntity = new CibilEntity();

        cibilEntity = cibilRepository.save(cibilRequest);
        cibilResponse = cibilMapper.mapCibilEntityToCibilResponse(cibilEntity);

        return cibilResponse;
    }

    @Override
    public CibilResponse getCibilScoreRecord(String panNumber) {


        CibilResponse cibilResponse = new CibilResponse();
        CibilEntity cibilEntity = new CibilEntity();

        cibilEntity = cibilRepository.getCibilScoreForByPanNumber(panNumber);
        cibilResponse = cibilMapper.mapCibilEntityToCibilResponse(cibilEntity);
        return cibilResponse;
    }

    @Override
    public Integer getCibilScoreForExternalServices(String panNumber) {

        Integer consumerCibilScore = null;

        CibilResponse cibilResponse = new CibilResponse();
        CibilEntity cibilEntity = new CibilEntity();
        cibilEntity = cibilRepository.getCibilScoreForByPanNumber(panNumber);
        consumerCibilScore = cibilEntity.getCibilScore();

        //updateCibilScoreViaKafka(consumerCibilScore.toString());


        return consumerCibilScore;
    }

    public boolean updateCibilScoreViaKafka(String cibilScore) {
        kafkaTemplate.send(CibilConstants.CIBIL_SCORE, cibilScore);
        log.info("cibilScore {}", cibilScore);
        return true;
    }


}
