package com.merin.accountService.repository;

import com.merin.accountService.entity.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ITransactionRepository extends JpaRepository<TransactionEntity, Integer> {

    @Query(value = "SELECT MAX(T.TRANSACTION_NUMBER) FROM TB_Transaction_Details T WHERE T.TRANSACTION_NUMBER LIKE ?1%", nativeQuery = true)
    String findLatestTransactionCode(String transactionCodePrefix);

//    List<Transaction> findByAccountNumber(String accountNumber);
//
//    List<Transaction> findByTransactionDateBetween(Date startDate, Date endDate);
//
//    List<Transaction> findByTransactionDateBefore(Date endDate);
//
//    List<Transaction> findByTransactionDateAfter(Date startDate);
//
//    List<Transaction> findByTransactionAmountGreaterThan(double amount);
//
//    List<Transaction> findByTransactionAmountLessThan(double amount);
//
//    List<Transaction> findByTransactionAmountBetween(double startAmount, double endAmount);
//
//    List<Transaction> findByTransactionType(String transactionType);
//
//    List<Transaction> findByTransactionMode(String transactionMode);
//
//    List<Transaction> findByTransactionStatus(String transactionStatus);
//
//    List<Transaction> findByTransactionRemarks(String transactionRemarks);
//
//    List<Transaction> findByTransactionDateBetweenAndTransactionAmountBetween(Date startDate, Date endDate, double startAmount, double endAmount);
//
//    List<Transaction> findByTransactionDateBetweenAndTransactionAmountGreaterThan(Date startDate, Date endDate, double amount);
//
//    List<Transaction> findByTransactionDateBetweenAndTransactionAmountLessThan(Date startDate, Date endDate, double amount);
//
//    List<Transaction> findByTransactionDateBetweenAndTransactionType(Date startDate, Date endDate, String transactionType);
//
//    List<Transaction> findByTransactionDateBetweenAndTransactionMode(Date startDate, Date endDate, String transactionMode);
//
//    List<Transaction> findByTransactionDateBetweenAndTransactionStatus(Date startDate, Date endDate, String transactionStatus);
//
//    List<Transaction> findByTransactionDateBetweenAndTransactionRemarks(Date startDate, Date endDate, String transactionRemarks);
//
//    List<Transaction> findByTransactionDateBetweenAndTransactionTypeAndTransactionMode(Date startDate, Date endDate, String transactionType, String transactionMode);
//
//    List<Transaction> findByTransactionDateBetweenAndTransactionTypeAndTransactionStatus(Date startDate, Date endDate, String transactionType, String transactionStatus);
//
//    List<Transaction> findByTransactionDateBetweenAndTransactionTypeAndTransactionRemarks(Date startDate, Date endDate, String transactionType, String transactionRemarks);
//
//    List<Transaction> findByTransactionDateBetweenAndTransactionModeAndTransactionStatus(Date startDate, Date endDate, String transactionMode, String transactionStatus);
//
//    List<Transaction> findByTransactionDateBetweenAndTransactionModeAndTransactionRemarks(Date startDate, Date endDate, String transactionMode, String transactionRemarks);
//
//    List<Transaction> findByTransactionDateBetweenAndTransactionStatusAndTransactionRemarks(Date startDate, Date endDate, String transactionStatus, String transactionRemarks);

}
