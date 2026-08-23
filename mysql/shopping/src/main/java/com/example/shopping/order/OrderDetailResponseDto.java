package com.example.shopping.order;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class OrderDetailResponseDto {

    private Long id;

    private OrderStatus status;

    private LocalDateTime orderedAt;

    private List<OrderItemResponseDto> items;

    public OrderDetailResponseDto(Order order, List<OrderItem> orderItems)
    {
        this.id= order.getId();
        this.status=order.getStatus();
        this.orderedAt=order.getOrderedAt();

        List<OrderItemResponseDto> items=new ArrayList<>();

        for (OrderItem orderItem : orderItems) {
            items.add(new OrderItemResponseDto (orderItem));
        }
        this.items=items;
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

    public List<OrderItemResponseDto> getItems() {
        return items;
    }
}
