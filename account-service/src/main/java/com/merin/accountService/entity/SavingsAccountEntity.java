package com.merin.accountService.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TB_Savings_Account")
public class SavingsAccountEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long savingsAccountId;

    @JsonIgnore
    @Column(name = "SA_Interest_Rate")
    private double savingsAccountInterestRate;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "Account_Id",referencedColumnName = "Account_Id")
    private AccountEntity accountEntity;

}
