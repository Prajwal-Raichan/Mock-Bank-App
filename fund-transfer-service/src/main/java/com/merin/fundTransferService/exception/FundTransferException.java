package com.merin.fundTransferService.exception;

@SuppressWarnings("serial")
public class FundTransferException extends Exception
{

	public String message;

	public FundTransferException(String message)
	{
		super(message);
	}
	
	@Override
	public String getMessage() 
	{
		return super.getMessage();
	}

}
