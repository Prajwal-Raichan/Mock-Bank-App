package com.merin.paymentService.controller;

import com.merin.paymentService.dto.PaymentResponse;
import com.merin.paymentService.entity.Payment;
import com.merin.paymentService.exception.PaymentNotFoundException;
import com.merin.paymentService.service.IPaymentService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/payment")
@Tag(name = "Payment-Service Controller",description = "Payment-Service Management Portal")
public class PaymentController
{

	@Autowired
	private IPaymentService paymentService;


	@PostMapping("/byCard")
	public ResponseEntity<PaymentResponse> addPaymentByCard(@RequestBody Payment paymentRequest) throws PaymentNotFoundException {
		
		PaymentResponse paymentResponse = new PaymentResponse();
		paymentResponse = paymentService.addPaymentByCard(paymentRequest);

		return new ResponseEntity<>(paymentResponse, HttpStatus.CREATED);
		
	}
	
	@PostMapping("/byUpi")
	public ResponseEntity<PaymentResponse> addPaymentByUpi(@RequestBody Payment paymentRequest) throws PaymentNotFoundException {

		PaymentResponse paymentResponse = new PaymentResponse();
		paymentResponse = paymentService.addPaymentByUpi(paymentRequest);
		return new ResponseEntity<>(paymentResponse, HttpStatus.CREATED);
		
	}
	
	
	@PostMapping("/byNetBanking")
	public ResponseEntity<PaymentResponse> addPaymentByNetBanking(@RequestBody Payment paymentRequest) throws PaymentNotFoundException	{

		PaymentResponse paymentResponse = new PaymentResponse();
		paymentResponse = paymentService.addPaymentByNetBanking(paymentRequest);
		return new ResponseEntity<>(paymentResponse, HttpStatus.CREATED);
		
	}
	
	@GetMapping("viewPaymentById")
	public ResponseEntity<PaymentResponse> getPaymentDetailsById(@RequestParam("paymentId") int paymentId) throws PaymentNotFoundException {

		PaymentResponse paymentResponse = new PaymentResponse();
		paymentResponse = paymentService.getPaymentDetailsById(paymentId);
		return new ResponseEntity<>(paymentResponse, HttpStatus.OK);
		
	}
	
	@GetMapping("/validatePaymentStatus")
	public ResponseEntity<Boolean> getPaymentStatusForExternalService(@RequestParam("paymentId") int paymentId,
																	  @RequestParam("paymentAmount") double paymentAmount) {

		Boolean paymentStatus = paymentService.getPaymentStatusById(paymentId, paymentAmount);
		return new ResponseEntity<>(paymentStatus, HttpStatus.OK);
	}

	
	@DeleteMapping("/deletePaymentById")
	public ResponseEntity<PaymentResponse> removePaymentById(@RequestParam("paymentId") int paymentId) throws PaymentNotFoundException {

		PaymentResponse paymentResponse = new PaymentResponse();
		paymentResponse = paymentService.deletePaymentById(paymentId);
		return new ResponseEntity<>(paymentResponse, HttpStatus.OK);
		
	}
	
	//@DateTimeFormat(iso=DateTimeFormat.ISO.DATE)
}
