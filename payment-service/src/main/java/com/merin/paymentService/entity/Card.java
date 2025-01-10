package com.merin.paymentService.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TB_Card_Details")
public class Card 
{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Card_Id")
	private int cardId;

	@NotNull(message = "Card holder name cannot be null")
	@Size(max = 100, message = "Card holder name cannot exceed 100 characters")
	@Column(name = "Card_HolderName")
	private String cardHolderName;

	@NotNull(message = "Card number cannot be null")
	@Pattern(regexp = "\\d{16}", message = "Card number must be exactly 16 digits")
	@Column(name = "Card_Number")
	private String cardNumber;

	@Min(value = 100, message = "CVV must be at least 3 digits")
	@Max(value = 999, message = "CVV must be at most 3 digits")
	@Column(name = "Card_CVV")
	private int cvv;

	@NotNull(message = "Card expiry date cannot be null")
	@JsonFormat(pattern="yyyy-MM-dd")
	@Column(name = "Card_Expiry")
	private	LocalDate cardExpiry;

	
}
