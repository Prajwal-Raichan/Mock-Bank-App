package com.merin.fundTransferService.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.merin.fundTransferService.enums.BeneficiaryStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TB_Beneficiary_Account")
public class BeneficiaryEntity {

    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Beneficiary_Id")
    private int beneficiaryId;

    @Column(name = "Beneficiary_Name", nullable = false)
    private String beneficiaryName;

    @Column(name = "Beneficiary_Account_Number", nullable = false)
    private String beneficiaryAccountNumber;

    @Column(name = "Beneficiary_IFSC_Code", nullable = false)
    private String beneficiaryIfscCode;

    @Column(name = "Relationship", nullable = true)
    private String relationship;

    @JsonIgnore
    @Enumerated(EnumType.STRING)
    @Column(name = "Beneficiary_Status", nullable = false)
    private BeneficiaryStatus beneficiaryStatus;

    @Column(name = "Beneficiary_Email", nullable = false)
    private String beneficiaryEmail;

    @JsonIgnore
    @Column(name = "Account_Number", nullable = false)
    private String accountNumber;

    @JsonIgnore
    @Column(name = "Customer_Id", nullable = false)
    private int customerId;

    @JsonIgnore
    @OneToMany(mappedBy = "beneficiary", fetch = FetchType.LAZY) // One-to-many relationship
    private List<FundTransferEntity> fundTransfers;
}
