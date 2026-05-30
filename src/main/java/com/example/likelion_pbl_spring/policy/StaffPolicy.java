package com.example.likelion_pbl_spring.policy;

public class StaffPolicy implements AssignmentPolicy {
    @Override
    public boolean canSubmit() {
        return false; // 운영진은 제출 불가
    }
}