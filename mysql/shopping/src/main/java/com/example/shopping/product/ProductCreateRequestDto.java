package com.example.shopping.product;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public class ProductCreateRequestDto {

    @NotBlank
    private String name;

    @Min(0)
    private Integer price;

    @NotNull
    @PositiveOrZero
    private Integer stockQuantity;



    protected ProductCreateRequestDto(){}

    public String getName() {
        return name;
    }

    public Integer getPrice() {
        return price;
    }

    public Integer getStockQuantity() {
        return stockQuantity;
    }

}
