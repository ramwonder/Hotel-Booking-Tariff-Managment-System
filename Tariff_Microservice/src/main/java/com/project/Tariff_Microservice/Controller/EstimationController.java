package com.project.Tariff_Microservice.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.Tariff_Microservice.Dto.EstimationDto;
import com.project.Tariff_Microservice.Exception.EstimationNotFoundException;
import com.project.Tariff_Microservice.Exception.EstimationNotSavedException;
import com.project.Tariff_Microservice.Model.EstimationModel;
import com.project.Tariff_Microservice.Service.EstimationService;
import com.project.Tariff_Microservice.Service.EstimationServiceImp;

import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/v1")
public class EstimationController {

	@Autowired
	EstimationService estimationService;
	@Operation(summary = "Create estimations", description = "Create estimations")
	@PostMapping("/estimations")
	public ResponseEntity<EstimationModel> createEstimation(@RequestBody EstimationDto estimationDto) throws EstimationNotSavedException
	{
		log.info("Fetching all List of Estimation");
		EstimationModel estimationModel=estimationService.createEstimation(estimationDto);
		return new ResponseEntity<EstimationModel>(estimationModel,HttpStatus.CREATED);
	}
	@Operation(summary = "Display estimations by id", description = "Returns estimation as per the id")
	@GetMapping("/estimations/{Id}")
	public ResponseEntity<EstimationModel> getEstimationById(@PathVariable String Id) throws EstimationNotFoundException
	{
		log.info("Searching estimation by id");
		EstimationModel estimationModel=estimationService.estimationById(Id);
		
		return new ResponseEntity<EstimationModel>(estimationModel,HttpStatus.OK);
	}
	
	
}
