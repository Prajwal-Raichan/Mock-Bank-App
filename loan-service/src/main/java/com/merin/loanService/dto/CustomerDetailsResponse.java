package com.merin.loanService.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class CustomerDetailsResponse {

    private int customerId;

    private String customerName;

    private String emailId;

    private String mobileNumber;

    private LocalDate dob;

}
