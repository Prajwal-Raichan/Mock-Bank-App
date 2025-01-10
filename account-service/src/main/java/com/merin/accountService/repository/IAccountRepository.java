package com.merin.accountService.repository;

import com.merin.accountService.dto.AccountProjection;
import com.merin.accountService.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IAccountRepository extends JpaRepository<AccountEntity, Integer> {

    AccountEntity findByAccountNumber(String accountNumber);

    @Query(value = "SELECT MAX(A.ACCOUNT_NUMBER) FROM TB_ACCOUNT_DETAILS A WHERE A.ACCOUNT_NUMBER LIKE ?1%", nativeQuery = true)
    String findLatestAccountNumber(String prefix);

    @Query(nativeQuery = true)
    AccountProjection getAccountByCustomerId(Integer customerId, String accountType);

    @Query(value = "SELECT Account_Balance FROM TB_ACCOUNT_DETAILS A WHERE A.CUSTOMER_ID =?1", nativeQuery = true)
    double getAccountBalanceByCustomerId(int customerId);

    @Query(nativeQuery = true)
    AccountProjection getAccountByCustomerIdAndAccountNumber(int customerId, String accountNumber);
}
