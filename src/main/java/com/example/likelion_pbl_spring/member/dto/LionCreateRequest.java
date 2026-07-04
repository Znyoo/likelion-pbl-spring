package com.example.likelion_pbl_spring.member.dto;

public class LionCreateRequest {
    private String name;
    private String major;
    private int generation;
    private String part;
    private String studentId;

    // 기본 생성자 (Jackson 라이브러리가 JSON을 객체로 변환할 때 필수)
    public LionCreateRequest() {}

    public LionCreateRequest(String name, String major, int generation, String part, String studentId) {
        this.name = name;
        this.major = major;
        this.generation = generation;
        this.part = part;
        this.studentId = studentId;
    }

    // Getter들 (스프링 바인딩에 필요)
    public String getName() { return name; }
    public String getMajor() { return major; }
    public int getGeneration() { return generation; }
    public String getPart() { return part; }
    public String getStudentId() { return studentId; }
}