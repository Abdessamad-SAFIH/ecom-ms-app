package com.bdcc.billingservice;

import com.bdcc.billingservice.entities.Bill;
import com.bdcc.billingservice.entities.ProductItem;
import com.bdcc.billingservice.model.Product;
import com.bdcc.billingservice.repository.BillRepository;
import com.bdcc.billingservice.repository.ProductItemRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.List;
import java.util.Random;

@SpringBootApplication
@EnableFeignClients
public class BillingServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BillingServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(BillRepository billRepository, ProductItemRepository productItemRepository){
		return args -> {
			List<Long> customerIds = List.of(1L, 2L, 3L);
			List<Long> productIds = List.of(1L, 2L, 3L);
			customerIds.forEach(clientId -> {
				Bill bill = new Bill();
				bill.setBillingDate(new Date());
				bill.setCustomerId(clientId);
				billRepository.save(bill);

				productIds.forEach(productId -> {
					ProductItem productItem = new ProductItem();
					productItem.setBill(bill);
					productItem.setProductId(productId);
					productItem.setPrice(1000*Math.random()*6000);
					productItem.setQuantity(1+new Random().nextInt(20));
					productItemRepository.save(productItem);
				});

			});
		};
	}
}
