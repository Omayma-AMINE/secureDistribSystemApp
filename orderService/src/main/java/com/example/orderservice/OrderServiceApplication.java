package com.example.orderservice;

import com.example.orderservice.entities.Customer;
import com.example.orderservice.entities.Order;
import com.example.orderservice.entities.OrderState;
import com.example.orderservice.entities.ProductItem;
import com.example.orderservice.repository.CustomerRepository;
import com.example.orderservice.repository.OrderRepository;
import com.example.orderservice.repository.ProductItemRepository;
import com.example.orderservice.restClients.CustomerRestClient;
import com.example.orderservice.restClients.InventoryRestClient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@SpringBootApplication
@EnableFeignClients
public class OrderServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(OrderRepository orderRepository,
										ProductItemRepository productItemRepository,
										InventoryRestClient inventoryRestClient,
										CustomerRepository customerRepository,
										CustomerRestClient customerRestClient){



		return args -> {
			//List<Product> allProducts = inventoryRestClient.getAllProducts();

			List<String> productIds = List.of("IP1","IP2", "IP3");
			List<String> customerIds = List.of("C01","C02", "C03");
			customerIds.forEach(customerId -> {
				Customer customer = Customer.builder()
						.baseCustomerId(customerId)
						.build();
				Customer savedCustomer = customerRepository.save(customer);
				for(int i = 0; i<5 ; i++){
					Order order = Order.builder()
							.id(UUID.randomUUID().toString())
							.date(LocalDate.now())
							.state(OrderState.PENDING)
							.customer(savedCustomer)
							.build();
					Order savedOrder = orderRepository.save(order);
					productIds.forEach(productId ->{
						ProductItem productItem = ProductItem.builder()
								.productId(productId)
								.quantity(new Random().nextInt(20))
								.price(Math.random()*9000+5000)
								.order(savedOrder)
								.build();
						productItemRepository.save(productItem);
					});
				}
			});

		};
	}
}
