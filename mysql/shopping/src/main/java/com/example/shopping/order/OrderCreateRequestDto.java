package com.example.shopping.order;

import jakarta.validation.constraints.NotNull;

import java.util.List;

public class OrderCreateRequestDto {

    @NotNull
    private Long customerId;

    private List<OrderItemRequestDto> item;

    public OrderCreateRequestDto(){}

    public Long getCustomerId() {
        return customerId;
    }

    public List<OrderItemRequestDto> getItem() {
        return item;
    }
}
