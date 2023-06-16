package com.project.Tariff_Microservice.Controller;

import java.util.List;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.Tariff_Microservice.Exception.TariffNotFoundException;
import com.project.Tariff_Microservice.Model.TariffModel;
import com.project.Tariff_Microservice.Service.TariffServiceImp;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/v1")
public class TariffController {
	
	@Autowired(required=true)
public	TariffServiceImp tariffServiceImp;
	
	
	@Operation(summary = "Display all Tariff Plans", description = "Returns Tariff Plans a product as per the id")
	@GetMapping("/tariffs")
	public ResponseEntity<List<TariffModel>> GetAllTariff()
	{
		log.info("Fetching all List of Tariff");
		List<TariffModel> listofTariff=tariffServiceImp.getAllTariff();
		return new ResponseEntity<List<TariffModel>>(listofTariff,HttpStatus.OK);
	}
   
	@Operation(summary = "Display Tariff Plan by id", description = "Returns Tariff Plans as per the id")
	@GetMapping("/tariffs/{id}")
	public ResponseEntity<TariffModel> GetTariffById(@PathVariable Long id)
	{
		log.info("Searching Tariff all by id");
	TariffModel tariffModel=tariffServiceImp.getTariffById(id);
		return new ResponseEntity<TariffModel>(tariffModel,HttpStatus.OK);
	}
	@Operation(summary = "Display Tariff Plan by category", description = "Returns Tariff Plans as per the category")
	@GetMapping("/tariffs/room/{category}")
	public ResponseEntity<TariffModel> GetTariffByCategory(@PathVariable String category)
	{
		log.info("Searching tariff by category");
	TariffModel tariffModel=tariffServiceImp.getTariffByCategory(category);
		return new ResponseEntity<TariffModel>(tariffModel,HttpStatus.OK);
	}
	

}
