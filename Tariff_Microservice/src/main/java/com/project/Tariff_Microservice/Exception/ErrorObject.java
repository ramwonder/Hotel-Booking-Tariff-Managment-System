package com.project.Tariff_Microservice.Exception;

import java.util.Date;

import lombok.Data;

@Data
public class ErrorObject {
	
	private Integer statusCode;
	private String message;
	private Date timestamp;
	public void setTimeStamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	
	
	

}
