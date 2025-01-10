package com.merin.fundTransferService.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.merin.fundTransferService.enums.TransactionType;
import com.merin.fundTransferService.enums.TransferMode;
import com.merin.fundTransferService.enums.TransferStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TB_FundTransfer")
public class FundTransferEntity {

    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transferId;

    @JsonIgnore
    @Column(name = "Customer_Id", nullable = false)
    private int customerId;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Beneficiary_Id", nullable = false)
    private BeneficiaryEntity beneficiary;

    @JsonIgnore
    @Column(name = "Transfer_Amount", nullable = false)
    private double transferAmount;

    @JsonIgnore
    @Column(name = "Transfer_Date", nullable = false)
    private LocalDateTime transferDate;

    @JsonIgnore
    @Column(name = "Transfer_Status", nullable = false)
    @Enumerated(EnumType.STRING)
    private TransferStatus transferStatus;

    @Column(name = "Transfer_Mode")
    private TransferMode transferMode;

    @Column(name = "Remarks")
    private String remarks;
}
