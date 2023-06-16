package com.project.Tariff_Microservice.Controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.Tariff_Microservice.Model.Discount;
import com.project.Tariff_Microservice.Model.TariffModel;
import com.project.Tariff_Microservice.Service.TariffServiceImp;
import com.project.Tariff_Microservice.Service.discountServiceImp;

@WebMvcTest(DiscountController.class)
public class DiscountControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private discountServiceImp discountServiceImp;
	
	@Autowired
	private ObjectMapper objectMapper;

	@Test
	void givenDiscount_whenGetAllDiscount_thenReturnDiscount() throws Exception {

		List<Discount> listofDiscount=new ArrayList<>();
		Discount discount=Discount.builder()
				.id(2L)
				.modeOfPayment("online")
				.discount("7%")
				.durationInDays(3)
				.minimumAmount(5000.0)
				.maximumCashback(null)
				.discountEndsOn(LocalDate.parse("2016-08-16"))
				.build();
		
		listofDiscount.add(discount);

   BDDMockito.given(discountServiceImp.GetAllDiscount()).willReturn(listofDiscount);
	
  //When-action or behavior
	
	ResultActions response=	mockMvc.perform(MockMvcRequestBuilders.get("/discounts"));
	
	//then b=verify the output
	response.andDo(MockMvcResultHandlers.print())
    .andExpect(MockMvcResultMatchers.status().isOk())
    .andExpect(MockMvcResultMatchers.jsonPath("$.size()", CoreMatchers.is(listofDiscount.size())));

		
	}
	
	@Test
	public void   givenDiscountbyid_whenGetDiscountbyid_thenDiscountObject() throws Exception
	{
		//given -precondition
		long id=1L;
		Discount discount=Discount.builder()
				.id(1L)
				.modeOfPayment("online")
				.discount("7%")
				.durationInDays(3)
				.minimumAmount(5000.0)
				.maximumCashback(null)
				.discountEndsOn(LocalDate.parse("2016-08-16"))
				.build();
		BDDMockito.given(discountServiceImp.GetDiscountById(id)).willReturn(discount);
		
		
		//When-action or behavior
		ResultActions response=	mockMvc.perform(MockMvcRequestBuilders.get("/discount/{id}",id));
		//then b=verify the output
		response.andDo(MockMvcResultHandlers.print())
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.modeOfPayment",CoreMatchers.is(discount.getModeOfPayment())))
        .andExpect(MockMvcResultMatchers.jsonPath("$.discount",CoreMatchers.is(discount.getDiscount())))
        .andExpect(MockMvcResultMatchers.jsonPath("$.durationInDays",CoreMatchers.is(discount.getDurationInDays())));
       
		
		
		
		
	}
	

}
