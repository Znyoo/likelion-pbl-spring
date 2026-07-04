package com.example.likelion_pbl_spring.member.dto;

public class StaffCreateRequest {
    private String name;
    private String major;
    private int generation;
    private String part;
    private String position;

    public StaffCreateRequest() {}

    public StaffCreateRequest(String name, String major, int generation, String part, String position) {
        this.name = name;
        this.major = major;
        this.generation = generation;
        this.part = part;
        this.position = position;
    }

    public String getName() { return name; }
    public String getMajor() { return major; }
    public int getGeneration() { return generation; }
    public String getPart() { return part; }
    public String getPosition() { return position; }
}