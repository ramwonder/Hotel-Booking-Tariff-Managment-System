package com.project.Tariff_Microservice.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.Tariff_Microservice.Model.TariffModel;
import com.project.Tariff_Microservice.Service.TariffServiceImp;

//@ExtendWith(SpringExtension.class)
//@AutoConfigureMockMvc
//@SpringBootTest
@WebMvcTest(TariffController.class)
public class TariffControllerTest {
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private TariffServiceImp tariffService;
	
	@Autowired
	private ObjectMapper objectMapper;

	@Test
	void givenTariff_whenGetAllTariff_thenReturnTariff() throws Exception {

		List<TariffModel> listofTariff=new ArrayList<>();
		TariffModel tariffModel=TariffModel.builder()
	               .Id(2L)
	               .roomCategory("classic")
	               .serviceCharge("999")
	               .tax("7%")
	               .build();
		TariffModel tariffModel1=TariffModel.builder()
	               .Id(1L)
	               .roomCategory("classic")
	               .serviceCharge("999")
	               .tax("7%")
	               .build();
		listofTariff.add(tariffModel1);

   BDDMockito.given(tariffService.getAllTariff()).willReturn(listofTariff);
	
  //When-action or behavior
	
	ResultActions response=	mockMvc.perform(MockMvcRequestBuilders.get("/tariff"));
	
	//then b=verify the output
	response.andDo(MockMvcResultHandlers.print())
    .andExpect(MockMvcResultMatchers.status().isOk())
    .andExpect(MockMvcResultMatchers.jsonPath("$.size()", CoreMatchers.is(listofTariff.size())));

		
	}
	@Test
	public void   givenTariffbyid_whenGetTariffbyid_thenTariffObject() throws Exception
	{
		//given -precondition
		long id=1L;
		TariffModel tariffModel=TariffModel.builder()
	               .Id(1L)
	               .roomCategory("classic")
	               .serviceCharge("999")
	               .tax("7%")
	               .build();
		BDDMockito.given(tariffService.getTariffById(id)).willReturn(tariffModel);
		
		
		//When-action or behavior
		ResultActions response=	mockMvc.perform(MockMvcRequestBuilders.get("/tariff/{id}",id));
		//then b=verify the output
		response.andDo(MockMvcResultHandlers.print())
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.roomCategory",CoreMatchers.is(tariffModel.getRoomCategory())))
        .andExpect(MockMvcResultMatchers.jsonPath("$.serviceCharge",CoreMatchers.is(tariffModel.getServiceCharge())))
        .andExpect(MockMvcResultMatchers.jsonPath("$.tax",CoreMatchers.is(tariffModel.getTax())));
       
		
		
		
		
	}
	
	@Test
	public void   givenTariffbyCategory_whenGetTariffbyCategory_thenTariffObject() throws Exception
	{
		//given -precondition
		long id=1L;
		String category="classic";
		TariffModel tariffModel=TariffModel.builder()
	               .Id(1L)
	               .roomCategory("classic")
	               .serviceCharge("999")
	               .tax("7%")
	               .build();
		BDDMockito.given(tariffService.getTariffByCategory(category)).willReturn(tariffModel);
		
		
		//When-action or behavior
		ResultActions response=	mockMvc.perform(MockMvcRequestBuilders.get("/tariff/room/{category}",category));
		//then b=verify the output
		response.andDo(MockMvcResultHandlers.print())
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.roomCategory",CoreMatchers.is(tariffModel.getRoomCategory())))
        .andExpect(MockMvcResultMatchers.jsonPath("$.serviceCharge",CoreMatchers.is(tariffModel.getServiceCharge())))
        .andExpect(MockMvcResultMatchers.jsonPath("$.tax",CoreMatchers.is(tariffModel.getTax())));
       
		
		
		
		
	}


}
