package com.example.shopping.order;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
public class OrderController {


    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrderResponseDto createOrder(@RequestBody OrderCreateRequestDto request)
    {
        Order order=orderService.createOrder(request.getCustomerId(), request.getItem());

        return new OrderResponseDto(order);
    }
}
