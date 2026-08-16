package com.example.shopping.order;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class OrderItemRequestDto {

    @NotNull
    private Long productId;

    @NotNull
    @Positive
    private int quantity;

    public OrderItemRequestDto(){}

    public Long getProductId() {
        return productId;
    }

    public int getQuantity() {
        return quantity;
    }
}
