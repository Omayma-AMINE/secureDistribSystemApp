package com.example.orderservice.restClients;

import com.example.orderservice.model.CustomerModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
@FeignClient(url = "http://localhost:8092", name = "customerService")
public interface CustomerRestClient {
    @GetMapping("/api/customers")
    List<CustomerModel> getAllCustomers();
    @GetMapping("/api/customers/{id}")
    CustomerModel findCustomerById(@PathVariable String id);

}
