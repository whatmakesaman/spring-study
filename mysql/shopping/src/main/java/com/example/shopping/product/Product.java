package com.example.shopping.product;



import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name="products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="product_id")
    private Long id;

    @Column(nullable = false,length=50)
    private String name;

    @Column(nullable = false)
    private int price;

    @Column(name="stock_quantity",nullable = false)
    private int stockQuantity;

    @Enumerated(EnumType.STRING)
    @Column(name="status",nullable = false, length = 50)
    private  ProductStatus productstatus;

    @CreationTimestamp
    @Column(name="created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    protected Product()
    {}

    public Product(String name, int price, int stockQuantity, ProductStatus productstatus) {
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.productstatus = productstatus;
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

    public ProductStatus getProductstatus() {
        return productstatus;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void changeStatus(ProductStatus status)
    {
        this.productstatus=status;
    }

    public void decreaseStock(int quantity)
    {
      if(this.stockQuantity<quantity)
      {
          throw new IllegalArgumentException("재고가 부족합니다");
      }
      this.stockQuantity-=quantity;
    }
}
