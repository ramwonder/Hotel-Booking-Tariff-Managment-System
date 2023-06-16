package com.project.Tariff_Microservice.Service;

import java.util.List;

import com.project.Tariff_Microservice.Exception.DiscountNotFoundException;
import com.project.Tariff_Microservice.Model.Discount;

public interface discountService {
	
	public List<Discount> GetAllDiscount();
	
	public Discount GetDiscountById(Long id);
	
	public boolean eligibleForDiscount(Double roomcharge, Integer duration ,Long DiscountId) throws DiscountNotFoundException;
	
	public double calculateDiscount(Long discountId,Double roomServiceCharge);

}
