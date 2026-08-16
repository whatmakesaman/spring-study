package com.example.shopping.customer;

import java.time.LocalDateTime;

public class CustomerResponseDto {

    private Long id;
    private String name;
    private String email;
    private LocalDateTime createdAt;

    public CustomerResponseDto(Customer customer)
    {
        this.id= customer.getId();
        this.name= customer.getName();
        this.email= customer.getEmail();
        this.createdAt=customer.getCreatedAt();
    }
    public Long getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public LocalDateTime getCreatedAt() { return createdAt; }
}
