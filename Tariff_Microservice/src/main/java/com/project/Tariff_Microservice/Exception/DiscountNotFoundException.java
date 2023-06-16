package com.project.Tariff_Microservice.Exception;

public class DiscountNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public DiscountNotFoundException (String message)
	{
		super(message);
	}

}
