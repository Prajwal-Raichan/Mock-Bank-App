package com.merin.loanService.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.merin.loanService.enums.LoanStatus;
import com.merin.loanService.enums.LoanType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TB_Loan")
public class LoanEntity {

    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Loan_Id")
    private Long loanId;

    @JsonIgnore
    @Column(name = "Customer_Id", nullable = false)
    private int customerId;

    @JsonIgnore
    @Column(name = "Account_Number",  nullable = false)
    private String accountNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "Loan_Type", nullable = false)
    private LoanType loanType;

    @Column(name = "Loan_Amount", nullable = false)
    private double loanAmount;

    @JsonIgnore
    @Column(name = "Cibil_Score", nullable = false)
    private Integer cibilScore;

    @JsonIgnore
    @Column(name = "Interest_Rate", nullable = false)
    private double interestRate;

    @Column(name = "Loan_Term", nullable = false)
    private int loanTerm;

    @JsonIgnore
    @JsonFormat(pattern="yyyy-MM-dd")
    @Column(name = "Loan_Creation_Date", nullable = false)
    private LocalDate loanCreationDate;

    @JsonFormat(pattern="yyyy-MM-dd")
    @Column(name = "Loan_Start_Date")
    private LocalDate startDate;

    @JsonIgnore
    @JsonFormat(pattern="yyyy-MM-dd")
    @Column(name = "End_Date")
    private LocalDate endDate;

    @JsonIgnore
    @Column(name = "Outstanding_Amount", nullable = false)
    private double outstandingAmount;

    @JsonIgnore
    @Enumerated(EnumType.STRING)
    @Column(name = "Loan_Status", nullable = false)
    private LoanStatus loanStatus;

    @Column(name = "Loan_Account_Number")
    private String loanAccountNumber;

    @Column(name = "Remarks")
    private String remarks;
}
