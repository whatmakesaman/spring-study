package com.example.shopping.product;

import jakarta.validation.constraints.NotNull;

public class ProductStatusUpdateRequestDto {

    @NotNull
    private ProductStatus status;

    protected ProductStatusUpdateRequestDto()
    {}
    public ProductStatus getStatus()
    {
        return status;
    }
}
