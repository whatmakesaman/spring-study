package com.example.shopping.init;


import com.example.shopping.customer.Customer;
import com.example.shopping.customer.CustomerRepository;
import com.example.shopping.product.Product;
import com.example.shopping.product.ProductRepository;
import com.example.shopping.product.ProductStatus;
import net.datafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class DataInitializer implements CommandLineRunner {

    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;

    public DataInitializer(CustomerRepository customerRepository, ProductRepository productRepository) {
        this.customerRepository = customerRepository;
        this.productRepository = productRepository;
    }


    @Override
    public void run(String... args) throws Exception {

        if(customerRepository.count()>0)
        {
            return;
        }
        Faker fake=new Faker();
        for(int i=0;i<500;i++)
        {
            String name=fake.name().fullName();
            String email=fake.internet().emailAddress();
            Customer customer=new Customer(name,email,"password1234");
            customerRepository.save(customer);
        }
        for(int i=0;i<5000;i++)
        {
            String name=fake.name().fullName();
            Random random=new Random();
            int price=fake.number().numberBetween(1000,1000000);
            int quantity= random.nextInt(200);
            ProductStatus[] status=ProductStatus.values();
            ProductStatus randomStatus=status[fake.number().numberBetween(0,status.length)];
            Product product=new Product(name,price,quantity,randomStatus);
            productRepository.save(product);

        }
    }
}
