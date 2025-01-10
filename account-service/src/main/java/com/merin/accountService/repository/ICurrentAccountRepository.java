package com.merin.accountService.repository;

import com.merin.accountService.entity.CurrentAccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICurrentAccountRepository extends JpaRepository<CurrentAccountEntity, Integer> {
}
