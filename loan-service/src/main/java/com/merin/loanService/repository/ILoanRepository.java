package com.merin.loanService.repository;

import com.merin.loanService.entity.LoanEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ILoanRepository extends JpaRepository<LoanEntity, Integer> {
}
