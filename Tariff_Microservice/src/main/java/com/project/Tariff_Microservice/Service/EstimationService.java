package com.project.Tariff_Microservice.Service;

import com.project.Tariff_Microservice.Dto.EstimationDto;
import com.project.Tariff_Microservice.Exception.EstimationNotFoundException;
import com.project.Tariff_Microservice.Exception.EstimationNotSavedException;
import com.project.Tariff_Microservice.Model.EstimationModel;

public interface EstimationService {

	public EstimationModel createEstimation(EstimationDto estimationDto) throws EstimationNotSavedException;
	public  EstimationModel estimationById(String Id) throws EstimationNotFoundException;
	
}
