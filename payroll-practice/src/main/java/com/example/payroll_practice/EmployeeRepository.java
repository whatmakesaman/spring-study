package com.example.payroll_practice;


import org.springframework.data.jpa.repository.JpaRepository;

interface EmployeeRepository extends JpaRepository<Employee,Long> {

}
