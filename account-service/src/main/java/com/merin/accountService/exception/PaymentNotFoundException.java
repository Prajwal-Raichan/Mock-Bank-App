package com.merin.accountService.exception;

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
