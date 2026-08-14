package com.example.shopping.product;


import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<ProductResponseDto> findAll()
    {
        return productService.findAll()
                .stream()
                .map(ProductResponseDto::new)
                .toList();
    }

    @GetMapping("/{id}")
    public ProductResponseDto findById(@PathVariable Long id)
    {
        Product product= productService.findById(id);
        return new ProductResponseDto(product);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductResponseDto create(@Valid @RequestBody ProductCreateRequestDto request)
    {
        Product product= productService.create(
                request.getName(),
                request.getPrice(),
                request.getStockQuantity()
        );
        return new ProductResponseDto(product);
    }

    @PatchMapping("/{id}/status")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void statusUpdate(
            @PathVariable("id") Long id,
            @Valid @RequestBody ProductStatusUpdateRequestDto requestDto
    )
    {
        productService.statusUpdate(id,requestDto.getStatus());
    }

}
