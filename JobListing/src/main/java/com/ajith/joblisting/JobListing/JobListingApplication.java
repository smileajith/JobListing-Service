package com.ajith.joblisting.JobListing;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication
//(scanBasePackages = "com.ajith.business.com") -- external call outside package which is in main application
public class JobListingApplication implements CommandLineRunner {

	@Value("${discount.offer}")
	private String offer;


	public static void main(String[] args) {

		SpringApplication.run(JobListingApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

	System.out.println("Offer price " + offer);


	}
}
