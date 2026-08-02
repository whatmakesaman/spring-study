package com.example.payroll_practice;


import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name="CUSTOMER_ORDER")
public class Order {

    @Id
    @GeneratedValue
    private Long id;

    private String description;

    @Enumerated(EnumType.STRING)
    private Status status;

    Order(){}

    public Order(String description, Status status) {
        this.description = description;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) return false;
        Order order = (Order) object;
        return Objects.equals(id, order.id) && Objects.equals(description, order.description) && status == order.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, status);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", status=" + status +
                '}';
    }
}
