package com.merin.accountService.exception;

@SuppressWarnings("serial")
public class AccountNotFoundException extends Exception
{

	public String message;

	public AccountNotFoundException(String message)
	{
		super(message);
	}
	
	@Override
	public String getMessage() 
	{
		return super.getMessage();
	}

}
