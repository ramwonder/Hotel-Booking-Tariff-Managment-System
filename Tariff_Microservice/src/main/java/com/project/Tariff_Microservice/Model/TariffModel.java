package com.project.Tariff_Microservice.Model;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="Tariff")
@Builder
public class TariffModel {
	
	@Id
	@GeneratedValue(strategy=jakarta.persistence.GenerationType.AUTO)
	private Long Id;
	@Column(name ="roomCategory",nullable=false) 
	private String roomCategory;
	@Column(name ="serviceCharge",nullable=false)
	private String serviceCharge;
	@Column(name ="tax",nullable=false)
	private String tax;
	
	
	
	
	
	

}
