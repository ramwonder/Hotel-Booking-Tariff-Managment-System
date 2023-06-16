package com.project.Tariff_Microservice.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="hotel")
@Builder
public class RoomModel {
	
	@Id
	private Integer roomId;
	private int hotelId;
	private String type;
	private int roomNumber;
	private int noOfBeds;
	private String ac;
	

}
