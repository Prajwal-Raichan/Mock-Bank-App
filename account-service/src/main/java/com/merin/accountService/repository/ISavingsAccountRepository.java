package com.merin.accountService.repository;

import com.merin.accountService.entity.SavingsAccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISavingsAccountRepository extends JpaRepository<SavingsAccountEntity, Integer> {
}
