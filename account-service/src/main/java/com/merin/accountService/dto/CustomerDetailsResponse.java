package com.merin.accountService.dto;

import jakarta.persistence.Column;
import lombok.*;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CustomerDetailsResponse {

    private int customerId;

    private String customerName;

    private LocalDate dob;

    private String emailId;

    private String mobileNumber;
}
