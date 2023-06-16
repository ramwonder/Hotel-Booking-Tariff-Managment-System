package com.project.Tariff_Microservice.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.Tariff_Microservice.Model.Discount;

@Repository
public interface discountRepository extends JpaRepository<Discount, Long> {

}
