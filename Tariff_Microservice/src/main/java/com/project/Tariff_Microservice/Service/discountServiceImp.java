package com.project.Tariff_Microservice.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.Tariff_Microservice.Exception.DiscountNotFoundException;
import com.project.Tariff_Microservice.Exception.TariffNotFoundException;
import com.project.Tariff_Microservice.Model.Discount;
import com.project.Tariff_Microservice.Model.TariffModel;
import com.project.Tariff_Microservice.Repository.discountRepository;

import lombok.extern.slf4j.Slf4j;
@Service
@Slf4j
public class discountServiceImp implements discountService {

	@Autowired
	public discountRepository discountRepository;
//	public discountServiceImp(discountRepository disRepository) {
//		// TODO Auto-generated constructor stub
//		this.discountRepository=disRepository;
//	}

	public List<Discount> GetAllDiscount() {
		// TODO Auto-generated method stub
		log.info("Fetching all List of discounts");
		return discountRepository.findAll();
	}

	
	public Discount GetDiscountById(Long id) {
		// TODO Auto-generated method stub
		Optional<Discount> dis=discountRepository.findById(id);
		if(dis.isPresent())
			
		{
			log.info("Searching discount by id");
			return dis.get();
			
		}
		throw new  DiscountNotFoundException("discount is not found for the id"+ id);
		
	}


	public boolean eligibleForDiscount(Double roomcharge, Integer duration, Long DiscountId) {
		// TODO Auto-generated method stub
		log.info("Checking eligiblity of the discount");
		Optional<Discount> dis=discountRepository.findById(DiscountId);
		if(!dis.isPresent())
		{
			log.info("Discount not found exception");
			throw new  DiscountNotFoundException("discount is not found for the id"+ DiscountId);
		}
		
		if(roomcharge >=dis.get().getMinimumAmount() && duration >= dis.get().getDurationInDays())
		{
			return true;
		}
		return false;
	}


	public double calculateDiscount(Long discountId, Double roomServiceCharge) {
		// TODO Auto-generated method stub
		log.info("Calculate discount");
		Optional<Discount> dis=discountRepository.findById(discountId);
		if(!dis.isPresent())
		{
			throw new  DiscountNotFoundException("discount is not found for the id"+ discountId);
		}
		String disper=dis.get().getDiscount();
		Double disrate=Double.parseDouble(disper.substring(0,disper.indexOf("%")));
		Double eligibleDis=(roomServiceCharge*disrate)/100D;
		log.info("Applying eligibility to the discount");
		if(eligibleDis<=dis.get().getMinimumAmount())
		{
			
			return eligibleDis;
		}
		return dis.get().getMaximumCashback();
		
	}

}
