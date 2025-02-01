package com.coderdot.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity(name = "Singup")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phone;
    private String gender;
    
    private LocalDate dateOfBirth;
    
    private String address;
    private String city;
    private String state;
    private String country;
    private String postalCode;
    
    private LocalDateTime accountCreationDate=LocalDateTime.now();
    private LocalDateTime lastLoginDate;
    
    private String accountStatus; // e.g., ACTIVE, INACTIVE, SUSPENDED
    
    private String profilePictureUrl;
    
    private String preferredLanguage;
    
    private boolean marketingOptIn; // true if the customer has opted in for marketing emails

    // You can also add relationships with other entities if needed, e.g., orders, payments, etc.
    // Example:
    // @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    // private List<Order> orders;
}