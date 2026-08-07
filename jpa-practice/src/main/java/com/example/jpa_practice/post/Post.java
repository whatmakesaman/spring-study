package com.example.jpa_practice.post;

import com.example.jpa_practice.member.Member;
import jakarta.persistence.*;

@Entity
@Table(name="posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 200)
    String title;

    @Lob
    @Column(nullable = false,length = 200)
    String content;

    @ManyToOne(
            fetch=FetchType.LAZY,
            optional = false
    )
    @JoinColumn(
            name="member_id",
            nullable = false
    )
    private Member member;

    protected Post(){}

    public Post(String title, String content, Member member) {
        this.title = title;
        this.content = content;
        this.member = member;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public Member getMember() {
        return member;
    }

    public void changeContent(String title, String content)
    {
        this.title=title;
        this.content=content;
    }
}
