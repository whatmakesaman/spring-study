package com.example.shopping.order;


import java.time.LocalDateTime;

public class OrderResponseDto {

    private Long id;

    private OrderStatus status;

    private LocalDateTime orderedAt;

    public OrderResponseDto(Order order)
    {
        this.id= order.getId();
        this.status= order.getStatus();
        this.orderedAt=order.getOrderedAt();
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
}
