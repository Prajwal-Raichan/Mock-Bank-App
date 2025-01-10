package com.merin.userService.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "TB_CustomerDetails")
public class Customer {

    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Customer_Id")
    private int customerId;

    @Column(name = "Customer_Name")
    private String customerName;

    @Column(name="DateOfBirth")
    private LocalDate dob;

    @Column(name = "Email_ID",unique = true)
    private String emailId;

    @Column(name = "Mobile_Number",unique = true)
    private String mobileNumber;

    @JsonIgnore
    @Column(name = "Temp_OTP")
    private String tempOtp;

    @JsonIgnore
    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "TB_Customer_Account_Ids", joinColumns = @JoinColumn(name = "Customer_Id"))
    @Column(name = "Account_Id")
    private List<Integer> accountIds = new ArrayList<>();

}
