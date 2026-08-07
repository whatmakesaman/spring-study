package com.example.jpa_practice.member;


import jakarta.persistence.*;

@Entity
@Table(name="members")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;

    public Member(){}

    public Member(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public void changeInfo(
            String name,
            String email
    ){
        this.name=name;
        this.email=email;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}
