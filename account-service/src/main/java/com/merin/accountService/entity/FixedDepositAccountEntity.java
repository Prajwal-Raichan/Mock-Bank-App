package com.merin.accountService.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TB_FixedDeposit_Account")
public class FixedDepositAccountEntity {

    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int fixedDepositAccountId;

    @JsonIgnore
    @Column(name = "FD_Interest_Rate")
    private double fixedDepositInterestRate;

    @Column(name = "FA_FixedDeposit_Amount")
    private double fixedDepositAmount;

    @Column(name = "FD_Tenure")
    private String fdTenure;

    @Column(name = "FD_Interest_Frequency")
    private int fdInterestFrequency;

    @JsonIgnore
    @Column(name = "FD_Maturity_Amount")
    private double fdMaturityAmount;

    @JsonIgnore
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "FD_Maturity_Date")
    private LocalDateTime fdMaturityDate;

    @JsonIgnore
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "FD_Creation_Date")
    private LocalDateTime fdCreationDate;

    @JsonIgnore
    @Column(name = "FD_Last_Updated_Date")
    private LocalDate fdLastUpdatedDate;

    @JsonIgnore
    @Column(name = "FD_Close_Date")
    private LocalDate fdCloseDate;

    @JsonIgnore
    @Column(name = "FD_Account_Status")
    private String fdAccountStatus;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "Account_Id",referencedColumnName = "Account_Id")
    private AccountEntity accountEntity;

}
