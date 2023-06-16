package com.project.Tariff_Microservice.Service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.project.Tariff_Microservice.Dto.EstimationDto;
import com.project.Tariff_Microservice.Exception.EstimationNotFoundException;
import com.project.Tariff_Microservice.Exception.EstimationNotSavedException;
import com.project.Tariff_Microservice.Model.EstimationModel;
import com.project.Tariff_Microservice.Model.RoomModel;
import com.project.Tariff_Microservice.Model.TariffModel;
import com.project.Tariff_Microservice.Repository.EstimationRepository;
import com.project.Tariff_Microservice.Repository.RoomRepository;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)



public class EstimationServiceTests {
	
	
	@Mock
	private EstimationRepository estimationRepository;
	@InjectMocks
	private EstimationServiceImp estimationServiceImp;
	@Mock
	private RoomRepository roomRepository;
	@Mock
	private TariffService tariffService;
	@Mock
	private discountService discountService;

	EstimationModel estimationModel=new EstimationModel("838cab92-d493-45a9-8c58-ba28de9e79f2",3256,1,4,LocalDate.now().toString(),LocalDate.now().plusDays(4).toString(),4,1L,22886.00,4329.4,27215.4);
	
	String id="838cab92-d493-45a9-8c58-ba28de9e79f2";
	
	EstimationDto estimationDto=new EstimationDto(id,3256,1,4,LocalDate.now().toString(),4,1L);
	RoomModel room=new RoomModel(1,3256,"classic",2,321,"yes");
	TariffModel tariffModel=new TariffModel(2L,"classic","999","7%");
	
//	private EstimationModel estimationModel;
//	
//	private  EstimationDto estimationDto;
//	private String id;
//	private  RoomModel room;
//	private 	TariffModel tariffModel;
//	private String category;
//	private Double serviceCharge;
//	private Long disId;
	

	
	
//	@BeforeEach
//	public void setup()
//	{
//		EstimationModel estimationModel= EstimationModel.builder()
//				.id("838cab92-d493-45a9-8c58-ba28de9e79f2")
//				.hotelId(3256)
//				.roomId(1)
//				.numberOfPersons(4)
//				.checkIn(LocalDate.now().toString())
//				.checkOut(LocalDate.now().plusDays(4).toString())
//				.durationDays(4)
//				.offerId(1L)
//				.serviceCharge(22886.00)
//				.estimationTax(4329.4)
//				.estimatedBillAmount(27215.4)
//				.build();
//		 id="838cab92-d493-45a9-8c58-ba28de9e79f2";
//		 estimationDto=EstimationDto.builder()
//				.Id(id)
//				.hotelId(3256)
//				.roomId(1)
//				.numberOfPersons(4)
//				.checkIn(LocalDate.now().toString())
//				.durationDays(4)
//				.disId(1L)
//				.build();
//	 room=RoomModel.builder()
//					.roomId(1)
//					.hotelId(3256)
//					.type("classic")
//					.ac("yes")
//					.roomNumber(321)
//					.noOfBeds(2)
//					.build();
	 
//		 tariffModel=TariffModel.builder()
//				.Id(2L)
//	            .roomCategory("classic")
//	            .serviceCharge("999")
//	            .tax("7%")
//	            .build();
//		 serviceCharge=8996.0;
//	     disId=estimationDto.getDisId();
//	}
//	
	@Test
	public void givenEstimation_WhenSaveEstimation_ThenReturnEstimationObject() throws EstimationNotSavedException
	{
		String category="classic";
		Double serviceCharge=8996.0;
		Long disId=estimationDto.getDisId();
		when(roomRepository.getById(estimationDto.getRoomId())).thenReturn(room);
		when(tariffService.getTariffByCategory(category)).thenReturn(tariffModel);
		when(discountService.eligibleForDiscount(serviceCharge, estimationDto.getDurationDays(), disId)).thenReturn(true);
		when(discountService.calculateDiscount(disId, serviceCharge)).thenReturn(629.72);
		when(estimationRepository.save(any(EstimationModel.class))).thenReturn(estimationModel);
		//assertEquals(estimationModel, estimationServiceImp.createEstimation(estimationDto));

//		
		
	}
	@DisplayName("junit test for get estimation by id")
	@Test
	public void   givenEstimationObject_whenEstimationFindByid_thenReturnEstimation() throws EstimationNotFoundException
	{
	
		//given -precondition
		BDDMockito.given(estimationRepository.findById(id)).willReturn(Optional.of(estimationModel));
		
		
		
		//When-action or behavior

		
		
		EstimationModel est=estimationServiceImp.estimationById(id);
		
		
	//then b=verify the output
		
		Assertions.assertThat(est).isNotNull();
		
	}

	
	

}
