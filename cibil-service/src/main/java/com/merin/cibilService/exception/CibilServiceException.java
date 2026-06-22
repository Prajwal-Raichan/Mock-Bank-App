package com.merin.cibilService.exception;

@SuppressWarnings("serial")
public class CibilServiceException extends Exception
{

	public String message;

	public CibilServiceException(String message)
	{
		super(message);
	}
	
	@Override
	public String getMessage() 
	{
		return super.getMessage();
	}

}
