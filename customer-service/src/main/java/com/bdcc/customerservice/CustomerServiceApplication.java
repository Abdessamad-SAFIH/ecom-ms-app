package com.bdcc.customerservice;

import com.bdcc.customerservice.config.CustomerConfigParams;
import com.bdcc.customerservice.entities.Customer;
import com.bdcc.customerservice.repository.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableConfigurationProperties(CustomerConfigParams.class)
public class CustomerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner start (CustomerRepository customerRepository){
		return args -> {
			customerRepository.save(new Customer(null, "Mohamed", "med@gmail.com"));
			customerRepository.save(new Customer(null, "Karim", "kam@gmail.com"));
			customerRepository.save(new Customer(null, "Ssamed", "sam@gmail.com"));
		};
	}

}
