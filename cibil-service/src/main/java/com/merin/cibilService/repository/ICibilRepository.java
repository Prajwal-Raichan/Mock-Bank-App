package com.merin.cibilService.repository;

import com.merin.cibilService.entity.CibilEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ICibilRepository extends JpaRepository<CibilEntity, Integer> {

    @Query(value = "SELECT * FROM TB_CIBIL_DETAILS WHERE PAN_NUMBER =?1", nativeQuery = true)
    CibilEntity getCibilScoreForByPanNumber(String panNumber);
}
