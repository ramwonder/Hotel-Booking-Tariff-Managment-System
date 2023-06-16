package com.project.Tariff_Microservice.Service;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import com.project.Tariff_Microservice.Model.Discount;
import com.project.Tariff_Microservice.Model.TariffModel;
import com.project.Tariff_Microservice.Repository.TariffRepository;
import com.project.Tariff_Microservice.Repository.discountRepository;

public class DiscountServiceTest {
	

	@Mock
	private discountRepository disRepository;
	@InjectMocks
	private discountService disService;
	private Discount discount;
	
	@BeforeEach
	public void setup()
	{
		
//		disRepository=Mockito.mock(discountRepository.class);
//		disService=new discountServiceImp(disRepository);
	}
	
	@DisplayName("Junit test for getAllDiscounts method")
	@Test
	public void givenDiscountList_whenGetAllDiscounts_thenReturnGetAllDiscounts(){
		//given -precondition
				
		Discount discount=Discount.builder()
				.id(2L)
				.modeOfPayment("online")
				.discount("7%")
				.durationInDays(3)
				.minimumAmount(5000.0)
				.maximumCashback(null)
				.discountEndsOn(LocalDate.parse("2016-08-16"))
				.build();

			BDDMockito.given(disRepository.findAll()).willReturn(List.of(discount));
			
			//When-action or behavior
			List<Discount> discountList=disService.GetAllDiscount();
			
			
			//then b=verify the output
			Assertions.assertThat(discountList).isNotNull();
			Assertions.assertThat(discountList.size()).isEqualTo(1);
		
	}
	@DisplayName("Junit test for getDiscountById method")
	@Test
	public void givenDiscountObject_whenGetDiscountsById_thenReturnGetDiscountsById(){
		//given -precondition
				
		Discount discount=Discount.builder()
				.id(2L)
				.modeOfPayment("online")
				.discount("7%")
				.durationInDays(3)
				.minimumAmount(5000.0)
				.maximumCashback(null)
				.discountEndsOn(LocalDate.parse("2016-08-16"))
				.build();

			
		//given -precondition
		BDDMockito.given(disRepository.findById(2L)).willReturn(Optional.of(discount));
		
		
		
		//When-action or behavior

		
		Discount dis=disService.GetDiscountById(discount.getId());
		
		
		//then b=verify the output
		
		Assertions.assertThat(dis).isNotNull();
		
	}
	@DisplayName("Junit test for getAllDiscount method (negative scenorio)")
	@Test
	public void givenAllDiscount_whenGetAllDiscountsNegativeScenario_thenReturnGetAllDiscounts(){
		//given -precondition
				
		Discount discount=Discount.builder()
				.id(2L)
				.modeOfPayment("online")
				.discount("7%")
				.durationInDays(3)
				.minimumAmount(5000.0)
				.maximumCashback(null)
				.discountEndsOn(LocalDate.parse("2016-08-16"))
				.build();
		BDDMockito.given(disRepository.findAll()).willReturn(Collections.emptyList());
		
		//When-action or behavior
		List<Discount> discountList=disService.GetAllDiscount();
		
		
		//then b=verify the output
		Assertions.assertThat(discountList).isNotNull();
		Assertions.assertThat(discountList.size()).isEqualTo(0);
			
		
	}

}
