package com.project.Tariff_Microservice.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EstimationDto {

	private String Id;
	private Integer hotelId;
	private Integer roomId;
	private Integer numberOfPersons;
	private String checkIn;
	private Integer durationDays;
	private Long disId;
	
}
