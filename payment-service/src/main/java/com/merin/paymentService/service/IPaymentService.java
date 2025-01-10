package com.merin.paymentService.service;




import com.merin.paymentService.dto.PaymentResponse;
import com.merin.paymentService.entity.Payment;
import com.merin.paymentService.exception.PaymentNotFoundException;

import java.time.LocalDate;

public interface IPaymentService 
{


	PaymentResponse addPaymentByCard(Payment paymentRequest) throws PaymentNotFoundException;

	PaymentResponse addPaymentByUpi(Payment payment) throws PaymentNotFoundException;

	PaymentResponse addPaymentByNetBanking(Payment payment) throws PaymentNotFoundException;


	PaymentResponse getPaymentDetailsById(int id);

	PaymentResponse deletePaymentById(int id) throws PaymentNotFoundException;


	Boolean getPaymentStatusById(int paymentId, double paymentAmount);
}
