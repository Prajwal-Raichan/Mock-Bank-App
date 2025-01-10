package com.merin.fundTransferService.repository;

import com.merin.fundTransferService.entity.FundTransferEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IFundTransferRepository extends JpaRepository<FundTransferEntity, Integer> {

}
