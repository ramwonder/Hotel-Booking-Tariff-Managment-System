package com.project.Tariff_Microservice.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name="Estimation")
public class EstimationModel {
	
	@Id
	private String id;
	@Column(name ="hotelId")
	private Integer hotelId;
	@Column(name ="roomId")
	private Integer roomId;
	@Column(name ="numberOfPersons")
	private Integer numberOfPersons;
	@Column(name ="checkIn")
	private String checkIn;
	@Column(name ="checkOut")
	private String checkOut;
	@Column(name ="durationDays")
	private Integer durationDays;
	@Column(name ="offerId")
	private Long offerId;
	@Column(name ="serviceCharge")
	private Double serviceCharge;
	@Column(name ="estimationTax")
	private Double estimationTax;
	@Column(name ="estimatedBillAmount")
	private Double estimatedBillAmount;
	

}
