package com.merin.fundTransferService.exception;

@SuppressWarnings("serial")
public class PaymentNotFoundException extends Exception
{
	
	public String message;
	
	public PaymentNotFoundException(String message) 
	{
		super(message);
	}
	
	@Override
	public String getMessage() 
	{
		return super.getMessage();
	}

}
