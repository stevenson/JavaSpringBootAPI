package com.stevenson.parcel;

import com.stevenson.parcel.repo.RuleRepo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ParcelApplication {

	RuleRepo repo;
	public static void main(String[] args) {
		SpringApplication.run(ParcelApplication.class, args);
	}

}
