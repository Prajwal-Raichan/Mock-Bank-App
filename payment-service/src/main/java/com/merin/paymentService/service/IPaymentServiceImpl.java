package com.merin.paymentService.service;


import com.merin.paymentService.Confirmation;
import com.merin.paymentService.dto.PaymentResponse;
import com.merin.paymentService.entity.Card;
import com.merin.paymentService.entity.NetBanking;
import com.merin.paymentService.entity.Payment;
import com.merin.paymentService.entity.Upi;
import com.merin.paymentService.mapper.PaymentMapper;
import com.merin.paymentService.repository.IPaymentRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Slf4j
@Service
public class IPaymentServiceImpl implements IPaymentService
{

	@Autowired
	private IPaymentRepository paymentRepository;

	@Autowired
	private PaymentMapper paymentMapper;


	@Transactional
	@Override
	public PaymentResponse addPaymentByCard(Payment paymentRequest) {

		PaymentResponse cardPaymentResponse =  new PaymentResponse();
		Payment payment	= new Payment();
		paymentRequest = updateTransaction(paymentRequest);
		payment = paymentRepository.save(paymentRequest);
		cardPaymentResponse = paymentMapper.mapPaymentEntityToPaymentResponse(paymentRequest);
		cardPaymentResponse.setCard(paymentRequest.getCard());
		return cardPaymentResponse;

	}

	@Transactional
	@Override
	public PaymentResponse addPaymentByUpi(Payment paymentRequest) {

		PaymentResponse upiPaymentResponse =  new PaymentResponse();
		Payment payment	= new Payment();

		paymentRequest = updateTransaction(paymentRequest);
		payment = paymentRepository.save(paymentRequest);
		upiPaymentResponse = paymentMapper.mapPaymentEntityToPaymentResponse(payment);
		upiPaymentResponse.setUpi(paymentRequest.getUpi());
		return upiPaymentResponse;
	}


	@Transactional
	@Override
	public PaymentResponse addPaymentByNetBanking(Payment paymentRequest)  {

		PaymentResponse netBankingPaymentResponse =  new PaymentResponse();
		Payment payment	= new Payment();

		paymentRequest = updateTransaction(paymentRequest);
		payment = paymentRepository.save(paymentRequest);
		netBankingPaymentResponse = paymentMapper.mapPaymentEntityToPaymentResponse(paymentRequest);
		netBankingPaymentResponse.setNetBanking(paymentRequest.getNetBanking());
		return netBankingPaymentResponse;

	}

	@Override
	public PaymentResponse getPaymentDetailsById(int paymentId) {

		PaymentResponse paymentResponse =  new PaymentResponse();
		Payment payment	= new Payment();

		payment = paymentRepository.findById(paymentId).get();
		paymentResponse = paymentMapper.mapPaymentEntityToPaymentResponse(payment);
		paymentResponse.setNetBanking(payment.getNetBanking());
		paymentResponse = getPaymentType(payment, paymentResponse);
		return paymentResponse;
	}


	@Override
	public PaymentResponse deletePaymentById(int paymentId) {

		PaymentResponse paymentResponse =  new PaymentResponse();
		Payment payment	= new Payment();
		paymentRepository.deleteById(paymentId);
		boolean status = paymentRepository.existsById(paymentId);
		if(!status)
			paymentResponse.setResponseMessage("Payment Deleted Successfully");
		else
			paymentResponse.setResponseMessage("Failed to Delete Payment");

		return paymentResponse;
	}

	@Override
	public Boolean getPaymentStatusById(int paymentId, double paymentAmount) {

		Payment payment	= new Payment();
		payment = paymentRepository.findById(paymentId).get();
		Confirmation paymentStatus = payment.getPaymentStatus();
		double paidAmount = payment.getPaymentAmount();

		boolean response = (paymentStatus == Confirmation.SUCCESSFULL && paymentAmount == paidAmount);

		return response;
	}

	private Payment updateTransaction(Payment paymentRequest) {

		paymentRequest.setPaymentTime(LocalDateTime.now());
		paymentRequest.setPaymentDate(LocalDate.now());
		paymentRequest.setPaymentStatus(Confirmation.SUCCESSFULL);

		return paymentRequest;
	}

	private PaymentResponse getPaymentType(Payment payment, PaymentResponse paymentResponse) {

		Card card = null;
		Upi upi = null;
		NetBanking netBanking =  null;

		if(payment.getCard()!=null) {
			paymentResponse.setCard(payment.getCard());
			return paymentResponse;
		}
		else if(payment.getNetBanking()!=null) {
			paymentResponse.setNetBanking(payment.getNetBanking());
			return paymentResponse;
		}
		else if(payment.getUpi()!=null) {
			paymentResponse.setCard(payment.getCard());
			return paymentResponse;
		}
        return paymentResponse;
    }


}
