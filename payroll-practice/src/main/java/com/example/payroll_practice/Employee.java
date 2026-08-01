package com.example.payroll_practice;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Employee {

    @Id
    @GeneratedValue
    private Long id;

    private String firstName;
    private String lastName;
    private String role;

    Employee(){

    }

    Employee(String firstName,String lastName, String role){
        this.firstName=firstName;
        this.lastName=lastName;
        this.role=role;

    }

    public String getName(){
        return this.firstName+" "+this.lastName;
    }

    public void setName(String name){
        String[] parts=name.split(" ");

        this.firstName=parts[0];
        this.lastName=parts[1];
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRole() {
        return this.role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
