package com.example.likelion_pbl_spring;

import com.example.likelion_pbl_spring.repository.MemberRepository;
import com.example.likelion_pbl_spring.role.Member;
import java.util.Collections;
import java.util.List;

public class MockMemberRepository implements MemberRepository {
    @Override
    public void save(String name, Member member) {}

    @Override
    public Member findByName(String name) { return null; }

    @Override
    public List<Member> findAll() { return Collections.emptyList(); }

    @Override
    public void updateByName(String name, Member member) {}

    @Override
    public boolean deleteByName(String name) { return false; }

    @Override
    public boolean existsByName(String name) { return false; } // 👈 인터페이스 규격 일치화
}