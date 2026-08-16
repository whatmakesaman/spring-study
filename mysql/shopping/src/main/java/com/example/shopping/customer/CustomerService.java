package com.example.shopping.customer;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer findById( Long id)
    {
        return customerRepository.findById(id)
                .orElseThrow(
                        ()->new IllegalArgumentException("고객 정보가 없습니다")
                );
    }


}
