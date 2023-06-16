package com.project.Tariff_Microservice.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.Tariff_Microservice.Model.Discount;
import com.project.Tariff_Microservice.Model.TariffModel;
import com.project.Tariff_Microservice.Service.discountServiceImp;

import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/v1")
public class DiscountController {
	
	public discountServiceImp disServiceImp;
	public DiscountController(discountServiceImp disServiceImp) {
		// TODO Auto-generated constructor stub
		this.disServiceImp=disServiceImp;
	}
	@Operation(summary = "Display all discounts", description = "List of discounts")
	@GetMapping("/discounts")
	public ResponseEntity<List<Discount>> GetAllTariff()
	
	{
		log.info("Fetching all List of discounts");
		List<Discount> listofdiscount=disServiceImp.GetAllDiscount();
		return new ResponseEntity<List<Discount>>(listofdiscount,HttpStatus.OK);
	}
	@Operation(summary = "Display discounts by id", description = "Returns Discount as per the id")
	@GetMapping("/discounts/{id}")
	public ResponseEntity<Discount> GetTariffById(@PathVariable Long id)
	{
	
		log.info("Searching Discount by id");
	Discount discount=disServiceImp.GetDiscountById(id);
		return new ResponseEntity<Discount>(discount,HttpStatus.OK);
	}

}
