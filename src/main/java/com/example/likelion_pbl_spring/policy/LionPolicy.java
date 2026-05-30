package com.example.likelion_pbl_spring.policy;

public class LionPolicy implements AssignmentPolicy {
    @Override
    public boolean canSubmit() {
        return true; // 아기사자는 제출 가능
    }
}