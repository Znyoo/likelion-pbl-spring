package com.example.likelion_pbl_spring.dto;

import com.example.likelion_pbl_spring.Member;

public class StaffResponse {
    private String name;
    private String major;
    private String part;
    private String position;

    // 생성자
    public StaffResponse(String name, String major, String part, String position) {
        this.name = name;
        this.major = major;
        this.part = part;
        this.position = position;
    }

    // 🌟 운영진 엔티티 데이터를 Response DTO로 안전하게 변환합니다.
    public static StaffResponse fromEntity(Member member) {
        return new StaffResponse(
                member.getName(),
                member.getMajor(),
                member.getPart(),
                member.getPosition()
        );
    }

    // ================= [ Getters ] =================
    public String getName() { return name; }
    public String getMajor() { return major; }
    public String getPart() { return part; }
    public String getPosition() { return position; }
}