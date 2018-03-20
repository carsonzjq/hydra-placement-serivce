package com.revature.hydra.placement.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.revature.hydra.placement.data.PlacementRepository;
import com.revature.hydra.placement.service.PlacementService;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableJpaRepositories("com.revature.hydra.placement.data")
@SpringBootApplication
@Configuration
@EnableSwagger2
@ComponentScan(basePackages= {"com.revature.hydra.placement.controller", "com.revature.hydra.placement.service"})
@EntityScan("com.revature.beans")
public class PlacementRepositoryServiceApplication {
	@Autowired
	PlacementService placementService;
	
	@Autowired
	PlacementRepository placementRepository;

	public static void main(String[] args) {
		SpringApplication.run(PlacementRepositoryServiceApplication.class, args);
	}
	
	
}
