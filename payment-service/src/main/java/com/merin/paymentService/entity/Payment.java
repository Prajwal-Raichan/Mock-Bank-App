package com.merin.paymentService.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.merin.paymentService.Confirmation;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "TB_Payment_Details")
public class Payment 
{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int	paymentId;

	@NotNull(message = "Payment type cannot be null")
	@Size(max = 50, message = "Payment type length should not exceed 50 characters")
	@Column(name = "Payment_Type")
	private String paymentType;

	@JsonIgnore
	@NotNull(message = "Payment status cannot be null")
	@Enumerated(EnumType.STRING)
	@Column(name = "Payment_Status")
	private Confirmation paymentStatus;

	@JsonIgnore
	@NotNull(message = "Payment date cannot be null")
	@JsonFormat(pattern="yyyy-MM-dd")
	@Column(name = "Payment_Date")
	private LocalDate paymentDate;

	@JsonIgnore
	@NotNull(message = "Payment time cannot be null")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@Column(name = "Payment_Time")
	private	LocalDateTime paymentTime;

	@Positive(message = "Payment amount must be positive")
	@Column(name = "Payment_Amount")
	private double paymentAmount;

	@ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	@JoinColumn(name = "Card_Id", referencedColumnName = "Card_Id")
	private Card card;

	@ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	@JoinColumn(name = "uId", referencedColumnName = "uId")
	private Upi upi;

	@ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	@JoinColumn(name = "NetBanking_Id", referencedColumnName = "NetBanking_Id")
	private NetBanking netBanking;


}
