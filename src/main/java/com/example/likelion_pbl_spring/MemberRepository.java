package com.example.likelion_pbl_spring;

import com.example.likelion_pbl_spring.role.Member;
import java.util.List;

public interface MemberRepository {
    void save(String name, Member member);
    Member findByName(String name);
    List<Member> findAll();
}