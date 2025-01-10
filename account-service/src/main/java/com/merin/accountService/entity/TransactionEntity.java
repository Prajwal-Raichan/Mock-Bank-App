package com.merin.accountService.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.merin.accountService.enums.TransactionMode;
import com.merin.accountService.enums.TransactionStatus;
import com.merin.accountService.enums.TransactionType;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TB_Transaction_Details")
public class TransactionEntity {

    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int transactionId;

    @JsonIgnore
    @Column(name = "Transaction_Number")
    private String transactionNumber;

    @JsonIgnore
    @Enumerated(EnumType.STRING)
    @Column(name = "Transaction_Type")
    private TransactionType transactionType;

    @JsonIgnore
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @Column(name = "Transaction_Date")
    private LocalDateTime transactionDate;

    @JsonIgnore
    @Column(name = "Transaction_Amount")
    @Min(value = 10, message = "Transaction amount must be positive")
    private double transactionAmount;

    @Column(name = "totalAccountBalance")
    private double totalAccountBalance;

    @JsonIgnore
    @Enumerated(EnumType.STRING)
    @Column(name = "Transaction_Status")
    private TransactionStatus transactionStatus;

    @JsonIgnore
    @Enumerated(EnumType.STRING)
    @Column(name = "Transaction_Mode")
    private TransactionMode transactionMode;

    @JsonIgnore
    @Column(name = "Transaction_Description",length = 500)
    private String transactionDescription;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Account_Id")
    private AccountEntity accountEntity;

    @JsonIgnore
    @Column(name = "Customer_Id")
    private int customerId;
}
