package com.example.likelion_pbl_spring;

import com.example.likelion_pbl_spring.role.Member;
import com.example.likelion_pbl_spring.role.Lion;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MockMemberRepository implements MemberRepository {
    private final List<Member> dummyStore = new ArrayList<>();
    private final Map<String, Member> dummyMap = new HashMap<>();

    public MockMemberRepository() {
        // 과제 예시 화면에 명시된 기본 더미 데이터 세팅
        Lion mockLion = new Lion("김사자", "컴퓨터공학과", 14, "백엔드", "202020202");
        dummyStore.add(mockLion);
        dummyMap.put("김사자", mockLion);
    }

    @Override
    public void save(String name, Member member) {
        // Mock 제약: 실제 저장은 하지 않음
        System.out.println("[Mock] 가짜 저장소이므로 실제 보관되지 않습니다.");
    }

    @Override
    public Member findByName(String name) {
        return dummyMap.get(name);
    }

    @Override
    public List<Member> findAll() {
        return dummyStore;
    }
}