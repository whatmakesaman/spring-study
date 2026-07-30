package com.example.payroll_practice;

public class EmployeeNotFoundException extends RuntimeException{

   EmployeeNotFoundException(Long id)
   {
       super("could not find"+id);
   }

}
