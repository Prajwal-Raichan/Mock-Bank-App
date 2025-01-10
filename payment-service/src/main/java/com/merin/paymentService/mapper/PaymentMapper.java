package com.merin.paymentService.mapper;

import com.merin.paymentService.dto.PaymentResponse;
import com.merin.paymentService.entity.Payment;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class PaymentMapper {

    public PaymentResponse mapPaymentEntityToPaymentResponse(Payment paymentEntity) {

        PaymentResponse paymentResponse = new PaymentResponse();
        paymentResponse = PaymentResponse.builder()
                .paymentId(paymentEntity.getPaymentId())
                .paymentAmount(paymentEntity.getPaymentAmount())
                .paymentDate(paymentEntity.getPaymentDate())
                .paymentStatus(paymentEntity.getPaymentStatus())
                .paymentTime(paymentEntity.getPaymentTime())
                .paymentType(paymentEntity.getPaymentType())
                .build();

        return paymentResponse;

    }

    public List<PaymentResponse> mapPaymentEntityToPaymentResponse(List<Payment> paymentEntityList) {

        List<PaymentResponse> paymentResponseList = new ArrayList<>();
        paymentResponseList = paymentEntityList
                .stream()
                .map(this::mapPaymentEntityToPaymentResponse)
                .collect(Collectors.toList());

        return paymentResponseList;
    }
}
