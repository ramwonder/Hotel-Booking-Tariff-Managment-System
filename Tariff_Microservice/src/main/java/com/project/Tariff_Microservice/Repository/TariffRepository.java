package com.project.Tariff_Microservice.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.project.Tariff_Microservice.Model.TariffModel;

@Repository
public interface TariffRepository extends JpaRepository<TariffModel, Long> {
	
//	@Query("select t from tariff where t.room_category=:category")
//	public TariffModel findByCategory(@Param("category")String category);
	 @Query("Select k from TariffModel k where k.roomCategory = :category")
	 public Optional<TariffModel> findByRoomCategory(@Param("category")String category);
	
}
