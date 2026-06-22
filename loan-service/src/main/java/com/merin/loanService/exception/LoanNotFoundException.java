package com.merin.loanService.exception;

@SuppressWarnings("serial")
public class LoanNotFoundException extends Exception
{

	public String message;

	public LoanNotFoundException(String message)
	{
		super(message);
	}
	
	@Override
	public String getMessage() 
	{
		return super.getMessage();
	}

}
