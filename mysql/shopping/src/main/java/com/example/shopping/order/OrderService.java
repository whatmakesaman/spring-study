package com.example.shopping.order;


import com.example.shopping.customer.Customer;
import com.example.shopping.customer.CustomerRepository;
import com.example.shopping.product.Product;
import com.example.shopping.product.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final ProductRepository productRepository;
    private final CustomerRepository customerRepository;


    public OrderService(
            OrderRepository orderRepository,
            OrderItemRepository orderItemRepository,
            ProductRepository productRepository,
            CustomerRepository customerRepository
            )
    {
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
        this.productRepository=productRepository;
        this.customerRepository=customerRepository;
    }

    @Transactional
    public Order createOrder(Long customerId, List<OrderItemRequestDto> items)
    {
        Customer customer=customerRepository.findById(customerId)
                            .orElseThrow(
                                    ()->new IllegalArgumentException("고객이 존재하지 않습니다")
                            );

        Order order=new Order(customer,OrderStatus.PENDING);
        orderRepository.save(order);

        for(OrderItemRequestDto itemRequestDto:items)
        {
            Product product=productRepository.findById(itemRequestDto.getProductId())
                    .orElseThrow(()->new IllegalArgumentException("상품이 없습니다"));

            product.decreaseStock(itemRequestDto.getQuantity());

            OrderItem orderItem=new OrderItem(product.getPrice(), itemRequestDto.getQuantity(), order,product);
            orderItemRepository.save(orderItem);
        }

        return order;



    }



}
