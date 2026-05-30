package com.example.likelion_pbl_spring.role;

import com.example.likelion_pbl_spring.policy.LionPolicy;

public class Lion extends Member {
    private String studentId;

    public Lion(String name, String major, int generation, String part, String studentId) {
        // super()를 통해 부모의 private 필드 초기화 및 정책 주입
        super(name, major, generation, part, new LionPolicy());
        this.studentId = studentId;
    }

    @Override
    public String getRoleName() { return "아기사자"; }

    @Override
    public void printExtraInfo() {
        System.out.println("🆔 학번: " + studentId);
    }
}