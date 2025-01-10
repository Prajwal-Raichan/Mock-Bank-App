package com.merin.paymentService.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TB_Upi_Details")
public class Upi {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int uId;

	@NotNull(message = "UPI ID cannot be null")
	@Pattern(regexp = "^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+$", message = "Invalid UPI ID format")
	@Column(name = "Upi_ID")
	private String upiId;

	@Min(value = 1000, message = "UPI PIN must be at least 4 digits")
	@Max(value = 9999, message = "UPI PIN must be at most 4 digits")
	@Column(name = "Upi_PIN")
	private int upiPin;



}
