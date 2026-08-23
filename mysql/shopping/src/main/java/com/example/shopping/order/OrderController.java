package com.example.shopping.order;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/{id}")
    public OrderDetailResponseDto findOrder(@PathVariable Long id)
    {
        Order order=orderService.findOrder(id);
        List<OrderItem> orderItem=orderService.findOrderItems(id);

        return new OrderDetailResponseDto(order,orderItem);
    }

    @PatchMapping("/{id}/cancel")
    public void cancelOrder(@PathVariable Long id)
    {
        orderService.cancelOrder(id);
    }
}
