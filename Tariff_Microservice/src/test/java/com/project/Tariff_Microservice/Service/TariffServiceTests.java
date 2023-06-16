package com.project.Tariff_Microservice.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.mockito.BDDMockito;
import org.mockito.Mockito;


import com.mysql.cj.x.protobuf.MysqlxCrud.Collection;
import com.project.Tariff_Microservice.Model.TariffModel;
import com.project.Tariff_Microservice.Repository.TariffRepository;

public class TariffServiceTests {
	
	private TariffRepository tariffRepository;
	private TariffService tariffService;
	private TariffModel tariffModel;
	@BeforeEach
	public void setup()
	{
		tariffRepository = Mockito.mock(TariffRepository.class);
		tariffService=new TariffServiceImp(tariffRepository);
	}
	
	@DisplayName("Junit test for getAllTariff method")
	@Test
	public void givenTariffList_whenGetAllTariff_thenReturnGetAllTariff(){
		//given -precondition
				
		TariffModel tariffModel=TariffModel.builder()
				               .Id(2L)
				               .roomCategory("classic")
				               .serviceCharge("999")
				               .tax("7%")
				               .build();
			BDDMockito.given(tariffRepository.findAll()).willReturn(List.of(tariffModel));
			
			//When-action or behavior
			List<TariffModel> tariffList=tariffService.getAllTariff();
			
			
			//then b=verify the output
			Assertions.assertThat(tariffList).isNotNull();
			Assertions.assertThat(tariffList.size()).isEqualTo(1);
		
	}
	
	@DisplayName("Junit test for getAllTariff method (negative scenario)")
	@Test
	public void givenEmptyTariffList_whenGetAllTariff_thenReturnEmptyTariffList(){
		//given -precondition
				
		TariffModel tariffModel=TariffModel.builder()
				               .Id(2L)
				               .roomCategory("classic")
				               .serviceCharge("999")
				               .tax("7%")
				               .build();
			BDDMockito.given(tariffRepository.findAll()).willReturn(Collections.emptyList());
			
			//When-action or behavior
			List<TariffModel> tariffList=tariffService.getAllTariff();
			
			
			//then b=verify the output
			Assertions.assertThat(tariffList).isNotNull();
			Assertions.assertThat(tariffList.size()).isEqualTo(0);
		
	}
	@DisplayName("junit test for get tariff by id")
	@Test
	public void   givenTariffObject_whenTariffFindByid_thenReturnTariff()
	{
		TariffModel tariffModel=TariffModel.builder()
	               .Id(2L)
	               .roomCategory("classic")
	               .serviceCharge("999")
	               .tax("7%")
	               .build();
		//given -precondition
		BDDMockito.given(tariffRepository.findById(2L)).willReturn(Optional.of(tariffModel));
		
		
		
		//When-action or behavior

		
		TariffModel tar=tariffService.getTariffById(tariffModel.getId());
		
		
	//then b=verify the output
		
		Assertions.assertThat(tar).isNotNull();
		
	}
	@DisplayName("junit test for get tariff by category")
	@Test
	public void   givenTariffObject_whenTariffFindByCategory_thenReturnTariff()
	{
		TariffModel tariffModel=TariffModel.builder()
	               .Id(2L)
	               .roomCategory("classic")
	               .serviceCharge("999")
	               .tax("7%")
	               .build();
		//given -precondition
		BDDMockito.given(tariffRepository.findByRoomCategory("classic")).willReturn(Optional.of(tariffModel));
		
			
		//When-action or behavior

		TariffModel tar=tariffService.getTariffByCategory(tariffModel.getRoomCategory());
	
		//then b=verify the output
		
		Assertions.assertThat(tar).isNotNull();
		
	}

}
