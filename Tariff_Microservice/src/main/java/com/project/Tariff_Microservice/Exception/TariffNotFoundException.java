package com.project.Tariff_Microservice.Exception;

public class TariffNotFoundException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public TariffNotFoundException(String message)
	{
		super(message);
	}

}
