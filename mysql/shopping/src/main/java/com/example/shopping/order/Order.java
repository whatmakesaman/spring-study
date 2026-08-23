package com.example.shopping.order;


import com.example.shopping.customer.Customer;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Entity
@Table(name="orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="order_id",nullable = false)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name="status", nullable = false, length = 50)
    private  OrderStatus status;

    @CreationTimestamp
    @Column(name="ordered_at", nullable = false, updatable = false)
    private LocalDateTime orderedAt;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="customer_id", nullable = false)
    private Customer customer;

    public Order(){}

    public Order(Customer customer, OrderStatus status) {
        this.customer = customer;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public LocalDateTime getOrderedAt() {
        return orderedAt;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void cancel()
    {
        this.status=OrderStatus.CANCELLED;
    }
}
