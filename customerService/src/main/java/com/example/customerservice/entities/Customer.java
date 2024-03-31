package com.example.customerservice.entities;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter @Setter
@ToString
@NoArgsConstructor @AllArgsConstructor
@Builder
public class Customer {
    @Id
    private String customerId;
    private String firstName ;
    private String lastName;
    private String email ;

}
