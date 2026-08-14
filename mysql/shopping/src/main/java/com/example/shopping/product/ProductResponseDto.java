package com.example.shopping.product;

import java.time.LocalDateTime;

public class ProductResponseDto {

    private Long id;
    private String name;
    private int price;
    private int stockQuantity;
    private ProductStatus status;
    private LocalDateTime createdAt;

   public ProductResponseDto(Product product)
   {
       this.id= product.getId();
       this.name = product.getName();
       this.price = product.getPrice();
       this.stockQuantity = product.getStockQuantity();
       this.status = product.getProductstatus();
       this.createdAt = product.getCreatedAt();
   }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public ProductStatus getStatus() {
        return status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
