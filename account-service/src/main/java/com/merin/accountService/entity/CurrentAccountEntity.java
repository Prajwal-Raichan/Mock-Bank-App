package com.merin.accountService.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TB_Current_Account")
public class CurrentAccountEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long currentAccountId;

    @JsonIgnore
    @Column(name = "CA_Interest_Rate")
    private double currentAccountInterestRate;

    @Column(name = "CA_Overdraft_Limit")
    private double overdraftLimit;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "Account_Id",referencedColumnName = "Account_Id")
    private AccountEntity accountEntity;
}
