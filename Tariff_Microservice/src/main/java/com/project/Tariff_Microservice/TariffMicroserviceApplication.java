package com.project.Tariff_Microservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.stereotype.Component;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;


@SpringBootApplication
@EnableDiscoveryClient
//@OpenAPIDefinition
@OpenAPIDefinition(info=@Info(title = "Hb core Tariff Microservice", version = "2.0", description = "View list of tariffs, discount and estimations"))
public class TariffMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TariffMicroserviceApplication.class, args);
	}

}

