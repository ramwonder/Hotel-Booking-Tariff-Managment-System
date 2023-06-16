package com.project.Tariff_Microservice.Controller;

import java.time.LocalDate;

import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.Tariff_Microservice.Dto.EstimationDto;
import com.project.Tariff_Microservice.Model.EstimationModel;
import com.project.Tariff_Microservice.Model.TariffModel;
import com.project.Tariff_Microservice.Service.EstimationServiceImp;
import com.project.Tariff_Microservice.Service.discountServiceImp;

@WebMvcTest(EstimationController.class)
public class EstimationControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private EstimationServiceImp estimationServiceImp;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Test
	public void   givenEstimationObject_whenCreateEstimation_thenReturnEstimation() throws Exception
	{
		//given -precondition
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
		BDDMockito.given(estimationServiceImp.createEstimation(ArgumentMatchers.any(EstimationDto.class)))
		.willReturn(estimationModel);
		//When-action or behavior
		ResultActions response=	mockMvc.perform(MockMvcRequestBuilders.post("/estimation")
		        .contentType(MediaType.APPLICATION_JSON)
		        .content(objectMapper.writeValueAsString(estimationDto)));
	     
		//then b=verify the output
	response.andDo(MockMvcResultHandlers.print())
	                 .andExpect(MockMvcResultMatchers.status().isCreated())
	                 .andExpect(MockMvcResultMatchers.jsonPath("$.hotelId", CoreMatchers.is(estimationModel.getHotelId())))  
	                 .andExpect(MockMvcResultMatchers.jsonPath("$.estimationTax",CoreMatchers.is(estimationModel.getEstimationTax())));
	                // .andExpect(MockMvcResultMatchers.jsonPath("$.email", CoreMatchers.is(employee.getEmail())));
	
     }
	
	@Test
	public void   givenTariffbyid_whenGetTariffbyid_thenTariffObject() throws Exception
	{
		//given -precondition
		String id="838cab92-d493-45a9-8c58-ba28de9e79f2";
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
		BDDMockito.given(estimationServiceImp.estimationById(id)).willReturn(estimationModel);
		
		
		//When-action or behavior
		ResultActions response=	mockMvc.perform(MockMvcRequestBuilders.get("/estimation/{id}",id));
		//then b=verify the output
		response.andDo(MockMvcResultHandlers.print())
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.hotelId", CoreMatchers.is(estimationModel.getHotelId())))  
        .andExpect(MockMvcResultMatchers.jsonPath("$.estimationTax",CoreMatchers.is(estimationModel.getEstimationTax())));       
		
		
		
		
	}


}
