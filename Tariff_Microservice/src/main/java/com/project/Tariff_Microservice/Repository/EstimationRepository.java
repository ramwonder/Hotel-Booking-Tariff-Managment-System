package com.project.Tariff_Microservice.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.Tariff_Microservice.Model.EstimationModel;

public interface EstimationRepository extends JpaRepository<EstimationModel, String> {

}
