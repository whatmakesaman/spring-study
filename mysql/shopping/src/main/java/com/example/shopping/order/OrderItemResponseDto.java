package com.example.shopping.order;



public class OrderItemResponseDto {

    private int price;

    private int quantity;

    private String name;

    public OrderItemResponseDto(OrderItem orderItem) {
        this.price = orderItem.getPrice();
        this.quantity = orderItem.getQuantity();
        this.name = orderItem.getProduct().getName();
    }

    public int getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getName() {
        return name;
    }
}
