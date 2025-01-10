package com.merin.accountService.repository;

import com.merin.accountService.entity.FixedDepositAccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IFixedDepositAccountRepository extends JpaRepository<FixedDepositAccountEntity, Integer> {


}
