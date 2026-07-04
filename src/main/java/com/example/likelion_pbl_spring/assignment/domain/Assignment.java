package com.example.likelion_pbl_spring.assignment.domain;

import com.example.likelion_pbl_spring.member.domain.Member;
import jakarta.persistence.*;

@Entity
public class Assignment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    protected Assignment() {}

    public Assignment(String title, String description, Member member) {
        this.title = title;
        this.description = description;
        this.member = member;
    }

    public void update(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public Long getId() { return id; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public Member getMember() { return member; }
}