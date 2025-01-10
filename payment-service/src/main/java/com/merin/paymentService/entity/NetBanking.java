package com.merin.paymentService.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TB_NetBanking_Details")
public class NetBanking 
{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "NetBanking_Id")
	private int netBankingId;

	@NotNull(message = "Net Banking Login ID cannot be null")
	@Size(min = 5, max = 50, message = "Net Banking Login ID must be between 5 and 50 characters")
	@Column(name = "NetBankingLogin_Id")
	private String netLoginId;


	@NotNull(message = "Net Banking Password cannot be null")
	@Size(min = 8, max = 100, message = "Net Banking Password must be between 8 and 100 characters")
	@Column(name = "NetBanking_Password")
	private String netPassword;

}
