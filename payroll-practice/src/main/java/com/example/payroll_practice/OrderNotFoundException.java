package com.example.payroll_practice;

public class OrderNotFoundException extends RuntimeException{

    OrderNotFoundException(Long id){
        super("could not find order"+id);
    }
}


