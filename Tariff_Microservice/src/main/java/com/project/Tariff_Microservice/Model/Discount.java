package com.project.Tariff_Microservice.Model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Table(name= "discounts")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Discount {
	
	@jakarta.persistence.Id
	private Long id;
	@Column(name="modeofPayment")
	private String modeOfPayment;
	@Column(name="discountEndsOn")
	private LocalDate discountEndsOn;
	@Column(name="durationInDays")
	private Integer durationInDays;
	@Column(name="minimumAmount")
	private Double minimumAmount;
	@Column(name="discount")
	private String discount;
	@Column(name="maximumCashback")
	private Double maximumCashback;
	
	
	

}
