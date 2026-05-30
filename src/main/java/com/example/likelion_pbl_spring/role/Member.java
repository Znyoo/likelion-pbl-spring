package com.example.likelion_pbl_spring.role;

import com.example.likelion_pbl_spring.policy.AssignmentPolicy;

public abstract class Member {
    // 모든 필드는 private으로 유지
    private String name;
    private String major;
    private int generation;
    private String part;
    private AssignmentPolicy policy; // 인터페이스를 필드로 가짐

    public Member(String name, String major, int generation, String part, AssignmentPolicy policy) {
        this.name = name;
        this.major = major;
        this.generation = generation;
        this.part = part;
        this.policy = policy;
    }

    // 공통 정보 출력 메서드
    public void printBaseInfo() {
        System.out.printf("👤 이름: %s | 🎓 전공: %s | 📌 기수: %d | 💻 파트: %s\n",
                name, major, generation, part);
    }

    // 정책 객체에 제출 판단을 맡김
    public boolean checkAssignment() {
        return policy.canSubmit();
    }

    public abstract String getRoleName();
    public abstract void printExtraInfo();
}