package com.example.likelion_pbl_spring.member.dto;

public class LionUpdateRequest {
    private String major;
    private int generation;
    private String part;
    private String studentId;

    public LionUpdateRequest() {}

    public LionUpdateRequest(String major, int generation, String part, String studentId) {
        this.major = major;
        this.generation = generation;
        this.part = part;
        this.studentId = studentId;
    }

    public String getMajor() { return major; }
    public int getGeneration() { return generation; }
    public String getPart() { return part; }
    public String getStudentId() { return studentId; }
}