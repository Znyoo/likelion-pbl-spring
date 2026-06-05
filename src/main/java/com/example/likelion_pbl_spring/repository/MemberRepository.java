package com.example.likelion_pbl_spring.repository;

import com.example.likelion_pbl_spring.role.Member;
import java.util.List;

public interface MemberRepository {
    void save(String name, Member member);
    Member findByName(String name);
    List<Member> findAll();

    // 7주차 필수 요구 추상 메서드 추가
    void updateByName(String name, Member member);
    boolean deleteByName(String name);
    boolean existsByName(String name);
}