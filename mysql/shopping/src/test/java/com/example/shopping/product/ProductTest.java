package com.example.shopping.product;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ProductTest{

    @Test
    void 상품을_생성할_수_있다(){
        Product product=new Product(
                "무선 마우스",
                29900,
                10,
                ProductStatus.ON_SALE
        );

        assertThat(product.getName()).isEqualTo("무선 마우스");
        assertThat(product.getPrice()).isEqualTo(29900);
        assertThat(product.getStockQuantity()).isEqualTo(10);
        assertThat(product.getProductstatus()).isEqualTo(ProductStatus.ON_SALE);
    }
}