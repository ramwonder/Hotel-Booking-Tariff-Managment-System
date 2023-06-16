package com.project.Tariff_Microservice.Service;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.Tariff_Microservice.Dto.EstimationDto;
import com.project.Tariff_Microservice.Exception.EstimationNotFoundException;
import com.project.Tariff_Microservice.Exception.EstimationNotSavedException;
import com.project.Tariff_Microservice.Model.EstimationModel;
import com.project.Tariff_Microservice.Model.RoomModel;
import com.project.Tariff_Microservice.Repository.EstimationRepository;
import com.project.Tariff_Microservice.Repository.RoomRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class EstimationServiceImp implements EstimationService {
	@Autowired
	EstimationRepository estimationRepository;
	@Autowired
	discountService disService;
	@Autowired
	TariffService tariffService;
	
	@Autowired
	RoomRepository roomRepository;

	public EstimationServiceImp(EstimationRepository estimationRepository) {
		// TODO Auto-generated constructor stub
		this.estimationRepository=estimationRepository;
	}

	public EstimationModel createEstimation(EstimationDto estimationDto) throws EstimationNotSavedException  {
		// TODO Auto-generated method stub
		log.info("Creating new estimation");

  	   RoomModel room=roomRepository.getById(estimationDto.getRoomId());
			
		//	RoomModel room=new RoomModel(5,414,"classic",321,2,"yes");
			
			EstimationModel estimationModel=new EstimationModel();
			estimationModel.setId(UUID.randomUUID().toString());
			estimationModel.setHotelId(estimationDto.getHotelId());
			estimationModel.setRoomId(estimationDto.getRoomId());
			estimationModel.setNumberOfPersons(estimationDto.getNumberOfPersons());
			
			estimationModel.setCheckIn(estimationDto.getCheckIn());
			estimationModel.setCheckOut(LocalDate.parse(estimationDto.getCheckIn()).plusDays(estimationDto.getDurationDays()).toString());
			estimationModel.setDurationDays(estimationDto.getDurationDays());
			estimationModel.setOfferId(estimationDto.getDisId());
			estimationModel=calculateCharges(estimationModel,room.getType().toLowerCase());
			
			EstimationModel saveEstimation=estimationRepository.save(estimationModel);
			if(!saveEstimation.equals(null))
			{
				log.info("Save estimation");
				return saveEstimation;
				
			}throw new EstimationNotSavedException("Unable to save Estimation");
			
//		}catch (Exception e) {
//			// TODO: handle exception
//			throw new EstimationNotSavedException("Unable to save Estimation");
//		}
		
	}

	private EstimationModel calculateCharges(EstimationModel estimationModel, String category) {
		// TODO Auto-generated method stub
		Integer durationDays=estimationModel.getDurationDays();
		Long disId=estimationModel.getOfferId();
		Double servicecharge=calculateServiceCharge(category,durationDays);
		if(disService.eligibleForDiscount(servicecharge, durationDays, disId))
		{
			servicecharge=servicecharge-disService.calculateDiscount(disId, servicecharge);
			
		}
		Double estimationTax=calculateTax(category,servicecharge);
		estimationModel.setServiceCharge(servicecharge);
		estimationModel.setEstimationTax(estimationTax);
		estimationModel.setEstimatedBillAmount(servicecharge+estimationTax);
		
		return estimationModel;
	}

	

	

	private Double calculateTax(String category, Double servicecharge) {
		// TODO Auto-generated method stub
		String taxper=tariffService.getTariffByCategory(category).getTax();
		Double taxRate=Double.parseDouble(taxper.substring(0,taxper.indexOf("%")));
		return (servicecharge*taxRate)/100D;
	 
		
	}

	public Double calculateServiceCharge(String category, Integer durationDays) {
		// TODO Auto-generated method stub
		Double chargepay= Double.parseDouble(tariffService.getTariffByCategory(category).getServiceCharge());
		 
		return chargepay*durationDays;
		
		
	}

	
	public EstimationModel estimationById(String Id) throws EstimationNotFoundException {
		// TODO Auto-generated method stub
		log.info("Searching estimation by id");
		Optional<EstimationModel> est=estimationRepository.findById(Id);
		if(!est.isPresent())
		{
			throw new EstimationNotFoundException("Estimation Not Found " +Id);
		}
		
		return est.get();
	}

}
