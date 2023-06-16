package com.project.Tariff_Microservice.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.Tariff_Microservice.Model.RoomModel;

public interface RoomRepository extends JpaRepository<RoomModel, Integer> {

}
