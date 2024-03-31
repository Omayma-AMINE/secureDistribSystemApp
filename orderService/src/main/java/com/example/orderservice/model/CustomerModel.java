package com.example.orderservice.model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString

public class CustomerModel {

    private String customerId;
    private String firstName;
    private String lastName;
    private String email;
}

