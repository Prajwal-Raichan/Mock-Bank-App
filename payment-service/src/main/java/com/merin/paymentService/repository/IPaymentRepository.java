package com.merin.paymentService.repository;

import com.merin.paymentService.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface IPaymentRepository extends JpaRepository<Payment, Integer> {

	
	
}
