package com.example.shopping.product;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Transactional
    public Product create(
            String name,
            int price,
            int stockQuantity
    )
    {
        Product product=new Product(
                name,
                price,
                stockQuantity,
                ProductStatus.DRAFT
        );
        return productRepository.save(product);
    }

    public Product findById(Long productId){
        return productRepository.findById(productId)
                .orElseThrow(()->
                        new IllegalArgumentException("상품을 찾을 수 없습니다"));
    }

    public List<Product> findAll(){
        return productRepository.findAll();
    }

    @Transactional
    public void statusUpdate(Long productId, ProductStatus status)
    {
        Product product=findById(productId);
        product.changeStatus(status);


    }
}
