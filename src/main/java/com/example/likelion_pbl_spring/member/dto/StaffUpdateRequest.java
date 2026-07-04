package com.example.likelion_pbl_spring.member.dto;

public class StaffUpdateRequest {
    private String major;
    private int generation;
    private String part;
    private String position;

    public StaffUpdateRequest() {}

    public StaffUpdateRequest(String major, int generation, String part, String position) {
        this.major = major;
        this.generation = generation;
        this.part = part;
        this.position = position;
    }

    public String getMajor() { return major; }
    public int getGeneration() { return generation; }
    public String getPart() { return part; }
    public String getPosition() { return position; }
}