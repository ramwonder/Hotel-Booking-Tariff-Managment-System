package com.project.Tariff_Microservice.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.Tariff_Microservice.Exception.TariffNotFoundException;
import com.project.Tariff_Microservice.Model.TariffModel;
import com.project.Tariff_Microservice.Repository.TariffRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TariffServiceImp  implements TariffService{


	private TariffRepository tariffRepository;
	public TariffServiceImp(TariffRepository tariffRepository) {
		// TODO Auto-generated constructor stub
		this.tariffRepository=tariffRepository;
	}

	
	public List<TariffModel> getAllTariff() {
		// TODO Auto-generated method stub
		log.info("Fetching all List of Tariff");
		return tariffRepository.findAll();
	}

	
	public TariffModel getTariffById(Long id) {
		// TODO Auto-generated method stub
		log.info("Searching tariff by id");
		Optional<TariffModel> tariff=tariffRepository.findById(id);
		if(tariff.isPresent())
			
		{
			return tariff.get();
			
		}
		throw new  TariffNotFoundException("Tariff is not found for the id"+ id);
	}

	
	public TariffModel getTariffByCategory(String category) {
		// TODO Auto-generated method stub
	
		log.info("Fetching Tariff by category");
		Optional<TariffModel> tariff=tariffRepository.findByRoomCategory(category);
		
        if(tariff.isPresent())
        {
			
		return tariff.get();
        }
        throw new  TariffNotFoundException("Tariff  is not found for the category"+ category);
        
	}

}
