package com.example.likelion_pbl_spring.dto;

import com.example.likelion_pbl_spring.Member;

public class LionResponse {
    private String name;
    private String major;
    private int generation;
    private String part;
    private String studentId;

    // 생성자
    public LionResponse(String name, String major, int generation, String part, String studentId) {
        this.name = name;
        this.major = major;
        this.generation = generation;
        this.part = part;
        this.studentId = studentId;
    }

    // 🌟 [핵심 변경] 리플렉션을 싹 걷어내고 일반 Member 엔티티에서 안전하게 꺼내옵니다!
    public static LionResponse fromEntity(Member member) {
        return new LionResponse(
                member.getName(),
                member.getMajor(),
                member.getGeneration() != null ? member.getGeneration() : 0, // null 방지 처리
                member.getPart(),
                member.getStudentId()
        );
    }

    // ================= [ Getters ] =================
    public String getName() { return name; }
    public String getMajor() { return major; }
    public int getGeneration() { return generation; }
    public String getPart() { return part; }
    public String getStudentId() { return studentId; }
}