package com.merin.accountService.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TB_Account_Details",uniqueConstraints = @UniqueConstraint(columnNames = {"Customer_Id", "PAN_Number","Account_Type"}))
public class AccountEntity {

    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Account_Id")
    private int accountId;

    @JsonIgnore
    @Column(name = "Account_Bank")
    private String accountBankName;

    @Column(name = "Account_Holder_Name")
    private String accountHolderName;

    @JsonIgnore
    @Column(name = "Account_Number", unique = true)
    private String accountNumber;

    @JsonIgnore
    @Column(name = "Account_Balance")
    private double accountBalance;

    @Column(name = "PAN_NUMBER")
    private String panNumber;

    @JsonIgnore
    @Column(name = "Account_Type")
    private String accountType;

    @JsonIgnore
    @Column(name = "Account_Status")
    private String accountStatus;

    @JsonIgnore
    @Column(name = "IFSC_Code")
    private String ifscCode;

    @JsonIgnore
    @Column(name = "Account_Branch_Name")
    private String accountBranchName;

    @JsonIgnore
    @Column(name = "Account_Branch_Code")
    private String accountBranchCode;

    @JsonIgnore
    @Column(name = "Customer_Id")
    private Integer customerId;

    @JsonIgnore
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "Account_Creation_Date")
    private LocalDateTime accountCreationDate;

    @JsonIgnore
    @OneToOne(mappedBy = "accountEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private SavingsAccountEntity savingsAccountEntity;

    @JsonIgnore
    @OneToOne(mappedBy = "accountEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private CurrentAccountEntity currentAccountEntity;

    @OneToOne(mappedBy = "accountEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private FixedDepositAccountEntity fixedDepositAccountEntity;

    @JsonIgnore
    @OneToMany(mappedBy = "accountEntity", cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
    private List<TransactionEntity> transactionEntities;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @ElementCollection
    @CollectionTable(name = "TB_Account_Loans", joinColumns = @JoinColumn(name = "Account_Id"))
    @Column(name = "Loan_Id")
    private List<Integer> loansOnAccount;
}
