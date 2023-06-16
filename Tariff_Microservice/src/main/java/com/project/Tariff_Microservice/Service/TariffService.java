package com.project.Tariff_Microservice.Service;

import java.util.List;

import com.project.Tariff_Microservice.Model.TariffModel;

public interface TariffService {
	
	public List<TariffModel> getAllTariff();
	
	public TariffModel getTariffById(Long id);
	
	public TariffModel getTariffByCategory(String category);
	
	
	

}
