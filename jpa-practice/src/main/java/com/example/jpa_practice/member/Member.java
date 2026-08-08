package com.example.jpa_practice.member;


import com.example.jpa_practice.post.Post;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="members")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;

    @OneToMany(mappedBy = "member",cascade = CascadeType.REMOVE)
    private List<Post> posts=new ArrayList<>();

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
