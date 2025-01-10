package com.merin.paymentService.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.merin.paymentService.Confirmation;
import com.merin.paymentService.entity.Card;
import com.merin.paymentService.entity.NetBanking;
import com.merin.paymentService.entity.Upi;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class PaymentResponse {

    private int	paymentId;

    private String paymentType;

    private Confirmation paymentStatus;

    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate paymentDate;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime paymentTime;

    private double paymentAmount;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Card card;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Upi upi;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private NetBanking netBanking;

    private String responseMessage;
}
