package com.example.orderservice.web;

import com.example.orderservice.entities.Customer;
import com.example.orderservice.entities.Order;
import com.example.orderservice.repository.CustomerRepository;
import com.example.orderservice.repository.OrderRepository;
import com.example.orderservice.restClients.CustomerRestClient;
import com.example.orderservice.restClients.InventoryRestClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class OrderRestController {

    private OrderRepository orderRepository;
    private InventoryRestClient inventoryRestClient;
    private CustomerRestClient customerRestClient;
    private CustomerRepository customerRepository;

    public OrderRestController(OrderRepository orderRepository,
                               InventoryRestClient inventoryRestClient,
                               CustomerRestClient customerRestClient,
                               CustomerRepository customerRepository) {
        this.orderRepository = orderRepository;
        this.inventoryRestClient = inventoryRestClient;
        this.customerRestClient = customerRestClient;
        this.customerRepository = customerRepository;
    }

    @GetMapping("/orders")
    public List<Order> findAllOrders(){
        List<Order> allOrders = orderRepository.findAll();
        allOrders.forEach(order -> {

            order.getProductItems().forEach(productItem -> {
                productItem.setProduct(inventoryRestClient.findProductById(productItem.getProductId()));
            });


        });
               return allOrders;
    }
    @GetMapping("/orderCustomers")
    public List<Customer> findAllOrdersWCustomer(){

        List<Customer> allCustomers = customerRepository.findAll();

        allCustomers.forEach(customer -> {
            customer.setFirstName(customerRestClient.findCustomerById(customer.getBaseCustomerId()).getFirstName());
            customer.setLastName(customerRestClient.findCustomerById(customer.getBaseCustomerId()).getLastName());
            customer.setEmail(customerRestClient.findCustomerById(customer.getBaseCustomerId()).getEmail());

            customer.getOrders().forEach(order -> {
                order.getProductItems().forEach(productItem -> {
                    productItem.setProduct(inventoryRestClient.findProductById(productItem.getProductId()));
                });
            });
        });

        return allCustomers;
    }
    @GetMapping("/orderCustomers/{id}")
    public Customer findAllOrdersWCustomer(@PathVariable String id){

        Customer customer = customerRepository.findByBaseCustomerId(id);


            customer.setFirstName(customerRestClient.findCustomerById(customer.getBaseCustomerId()).getFirstName());
            customer.setLastName(customerRestClient.findCustomerById(customer.getBaseCustomerId()).getLastName());
            customer.setEmail(customerRestClient.findCustomerById(customer.getBaseCustomerId()).getEmail());

            customer.getOrders().forEach(order -> {
                order.getProductItems().forEach(productItem -> {
                    productItem.setProduct(inventoryRestClient.findProductById(productItem.getProductId()));
                });
            });


        return customer;
    }

    @GetMapping("/orders/{id}")
    public Order findAllOrders(@PathVariable String id){
        Order order = orderRepository.findById(id).get();
        order.getProductItems().forEach(productItem -> {
            productItem.setProduct(inventoryRestClient.findProductById(productItem.getProductId()));
        });
        //order.getCustomer().setCustomer(customerRestClient.findCustomerById(order.getCustomer().getCustomerId()));

        return order;
    }
}
