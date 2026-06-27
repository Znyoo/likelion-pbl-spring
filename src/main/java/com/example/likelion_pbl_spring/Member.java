package com.example.likelion_pbl_spring;

import jakarta.persistence.*;

@Entity // 🌟 이 클래스가 데이터베이스 테이블이 된다고 지정합니다.
public class Member {

    @Id // 🌟 기본키(PK) 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 🌟 id를 자동으로 1씩 올려줍니다.
    private Long id;

    private String name;
    private String major;
    private Integer generation; // Lion 필드 통합

    @Enumerated(EnumType.STRING) // 🌟 Enum을 DB에 글자(LION, STAFF) 그대로 저장합니다.
    private RoleType roleType;

    private String part;       // Lion/Staff 공통 필드
    private String studentId;  // Lion 전용 필드
    private String position;   // Staff 전용 필드

    // 기본 생성자 (JPA 필수)
    protected Member() {}

    // 아기사자(LION) 생성을 위한 편의 생성자
    public static Member createLion(String name, String major, Integer generation, String part, String studentId) {
        Member member = new Member();
        member.name = name;
        member.major = major;
        member.generation = generation;
        member.roleType = RoleType.LION;
        member.part = part;
        member.studentId = studentId;
        return member;
    }

    // 운영진(STAFF) 생성을 위한 편의 생성자
    public static Member createStaff(String name, String major, String part, String position) {
        Member member = new Member();
        member.name = name;
        member.major = major;
        member.roleType = RoleType.STAFF;
        member.part = part;
        member.position = position;
        return member;
    }

    // 데이터 수정(Update)을 위한 비즈니스 메서드
    public void updateLion(String major, Integer generation, String part, String studentId) {
        this.major = major;
        this.generation = generation;
        this.part = part;
        this.studentId = studentId;
    }

    public void updateStaff(String major, String part, String position) {
        this.major = major;
        this.part = part;
        this.position = position;
    }

    // ================= [ Getters ] =================
    public Long getId() { return id; }
    public String getName() { return name; }
    public String getMajor() { return major; }
    public Integer getGeneration() { return generation; }
    public RoleType getRoleType() { return roleType; }
    public String getPart() { return part; }
    public String getStudentId() { return studentId; }
    public String getPosition() { return position; }
}