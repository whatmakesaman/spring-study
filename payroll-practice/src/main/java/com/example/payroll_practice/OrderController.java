package com.example.payroll_practice;


import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class OrderController {

    private final OrderRepository orderRepository;
    private final OrderModelAssembler orderModelAssembler;

    public OrderController(OrderRepository orderRepository, OrderModelAssembler orderModelAssembler) {
        this.orderRepository = orderRepository;
        this.orderModelAssembler = orderModelAssembler;
    }


    @GetMapping("/orders")
    CollectionModel<EntityModel<Order>> all(){
        List<EntityModel<Order>> orders=
                orderRepository.findAll()
                        .stream()
                        .map(orderModelAssembler::toModel)
                        .toList();

        return CollectionModel.of(
                orders,
                linkTo(methodOn(OrderController.class)
                        .all())
                        .withSelfRel()
        );
    }

    @GetMapping("/orders/{id}")
    EntityModel<Order> one(@PathVariable Long id){

        Order order=orderRepository.findById(id)
                .orElseThrow(()->new OrderNotFoundException(id));
        return orderModelAssembler.toModel(order);
    }

    @PostMapping("/orders")
    ResponseEntity<EntityModel<Order>> newOrder(@RequestBody Order order)
    {
        order.setStatus(Status.IN_PROGRESS);

        Order savedOrder=orderRepository.save(order);
        EntityModel<Order> entityModel=orderModelAssembler.toModel(savedOrder);

        return ResponseEntity
                .created(
                        linkTo(methodOn(OrderController.class)
                                .one(savedOrder.getId()))
                                .toUri()
                )
                .body(entityModel);

    }
}
