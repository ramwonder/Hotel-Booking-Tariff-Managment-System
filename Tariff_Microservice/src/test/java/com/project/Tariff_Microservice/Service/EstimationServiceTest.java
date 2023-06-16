package com.project.Tariff_Microservice.Service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.project.Tariff_Microservice.Dto.EstimationDto;
import com.project.Tariff_Microservice.Exception.EstimationNotFoundException;
import com.project.Tariff_Microservice.Exception.EstimationNotSavedException;
import com.project.Tariff_Microservice.Model.EstimationModel;
import com.project.Tariff_Microservice.Model.RoomModel;
import com.project.Tariff_Microservice.Model.TariffModel;
import com.project.Tariff_Microservice.Repository.EstimationRepository;
import com.project.Tariff_Microservice.Repository.RoomRepository;
import com.project.Tariff_Microservice.Repository.TariffRepository;
import com.project.Tariff_Microservice.Repository.discountRepository;


public class EstimationServiceTest {

	
	private EstimationServiceImp estimationServiceImp;
	private EstimationService estimationService;
	

	private EstimationRepository estimationRepository;
	
	private TariffService tariffService;
	
	private discountService disService;
	
	@Autowired
	private RoomRepository roomRepository;
	
	private discountRepository disRepository;
	private TariffRepository tariffRepository;
	
	EstimationModel estimationModel= EstimationModel.builder()
			.id("838cab92-d493-45a9-8c58-ba28de9e79f2")
			.hotelId(3256)
			.roomId(1)
			.numberOfPersons(4)
			.checkIn(LocalDate.now().toString())
			.checkOut(LocalDate.now().plusDays(4).toString())
			.durationDays(4)
			.offerId(1L)
			.serviceCharge(22886.00)
			.estimationTax(4329.4)
			.estimatedBillAmount(27215.4)
			.build();
	
	String id="838cab92-d493-45a9-8c58-ba28de9e79f2";
	EstimationDto estimationDto=EstimationDto.builder()
			.Id(id)
			.hotelId(3256)
			.roomId(1)
			.numberOfPersons(4)
			.checkIn(LocalDate.now().toString())
			.durationDays(4)
			.disId(1L)
			.build();
	RoomModel room=RoomModel.builder()
			.roomId(1)
			.hotelId(3256)
			.type("classic")
			.ac("yes")
			.roomNumber(321)
			.noOfBeds(2)
			.build();
	
	String category="classic";
	TariffModel tariffModel=TariffModel.builder()
			.Id(2L)
            .roomCategory("classic")
            .serviceCharge("999")
            .tax("7%")
            .build();
	Double serviceCharge=8996.0;
	Long disId=estimationDto.getDisId();
	@BeforeEach
	public void setup()
	{
		
		estimationRepository=Mockito.mock(EstimationRepository.class);
		estimationService=new EstimationServiceImp(estimationRepository);
		disRepository=Mockito.mock(discountRepository.class);
		//disService=new discountServiceImp(disRepository);

		tariffRepository = Mockito.mock(TariffRepository.class);
		tariffService=new TariffServiceImp(tariffRepository);
		
		
	}
	@Test
	public void givenEstimation_WhenSaveEstimation_ThenReturnEstimationObject() throws EstimationNotSavedException
	{
		
		
		BDDMockito.given(estimationRepository.save(estimationModel)).willReturn(estimationModel);
	//DDMockito.given(disService.eligibleForDiscount(serviceCharge, estimationDto.getDurationDays(), disId)).willReturn(true);
		BDDMockito.given(tariffService.getTariffByCategory(category)).willReturn(tariffModel);
       //DDMockito.given(disService.calculateDiscount(disId, serviceCharge)).willReturn(629.72);
		
       
//		when(roomRepository.getById(estimationDto.getRoomId())).thenReturn(room);
//		when(tariffService.getTariffByCategory(category)).thenReturn(tariffModel);
//		when(discountService.eligibleForDiscount(serviceCharge, estimationDto.getDurationDays(), disId)).thenReturn(true);
//		when(discountService.calculateDiscount(disId, serviceCharge)).thenReturn(629.72);
//		when(estimationRepository.save(any(EstimationModel.class))).thenReturn(estimationModel);
//		assertEquals(estimationModel, estimationServiceImp.createEstimation(estimationDto));
//		
		
	}
	
	@DisplayName("junit test for get estimation by id")
	@Test
	public void   givenEstimationObject_whenEstimationFindByid_thenReturnEstimation() throws EstimationNotFoundException
	{
	
		//given -precondition
		BDDMockito.given(estimationRepository.findById(id)).willReturn(Optional.of(estimationModel));
		
		
		
		//When-action or behavior

		
		
		EstimationModel est=estimationService.estimationById(id);
		
		
	//then b=verify the output
		
		Assertions.assertThat(est).isNotNull();
		
	}



	

	
	
	
	
}
