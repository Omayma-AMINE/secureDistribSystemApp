package com.example.customerservice;

import com.example.customerservice.entities.Customer;
import com.example.customerservice.repository.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CustomerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerServiceApplication.class, args);
	}
	@Bean
	CommandLineRunner commandLineRunner(CustomerRepository customerRepository){
		return args -> {
			customerRepository.save(Customer.builder()
					.customerId("C01")
					.firstName("Omayma")
					.lastName("AMINE")
					.email("omayma.amine@gmail.com")
					.build());
			customerRepository.save(Customer.builder()
					.customerId("C02")
					.firstName("ziad")
					.lastName("ALAOUI")
					.email("ziad.alaoui@gmail.com")
					.build());
			customerRepository.save(Customer.builder()
					.customerId("C03")
					.firstName("Sara")
					.lastName("MOUTAQI")
					.email("sara.moutaqi@gmail.com")
					.build());
		};


	}
}
