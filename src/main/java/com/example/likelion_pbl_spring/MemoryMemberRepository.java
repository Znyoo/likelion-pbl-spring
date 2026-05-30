package com.example.likelion_pbl_spring;

import com.example.likelion_pbl_spring.role.Member;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository // 👈 자동 주입을 위해 빈 등록 어노테이션 추가
public class MemoryMemberRepository implements MemberRepository {
    private final List<Member> store = new ArrayList<>();
    private final Map<String, Member> nameMap = new HashMap<>();

    @Override
    public void save(String name, Member member) {
        store.add(member);
        nameMap.put(name, member);
    }

    @Override
    public Member findByName(String name) {
        return nameMap.get(name);
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store);
    }
}