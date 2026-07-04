package com.example.likelion_pbl_spring.member.domain;

import com.example.likelion_pbl_spring.assignment.domain.Assignment;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import com.example.likelion_pbl_spring.team.domain.Team;

@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String major;
    private String part;
    private int generation;

    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    private String studentId;
    private String position;

    // 🌟 과제 목표 요구사항: 1:N 양방향 연관관계 추가 (주인이 아니므로 mappedBy 추가)
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Assignment> assignments = new ArrayList<>();

    // 🌟 Team-Member 연관관계: 연관관계의 주인 (FK를 가진 쪽)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team team;

    protected Member() {}

    public Member(String name, String major, String part, int generation, RoleType roleType, String studentId, String position) {
        this.name = name;
        this.major = major;
        this.part = part;
        this.generation = generation;
        this.roleType = roleType;
        this.studentId = studentId;
        this.position = position;
    }

    public void updateInfo(String major, int generation, String part) {
        this.major = major;
        this.generation = generation;
        this.part = part;
    }

    public void updateStudentId(String studentId) {
        this.studentId = studentId;
    }

    public void updatePosition(String position) {
        this.position = position;
    }

    public void assignTeam(Team team) {
        this.team = team;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public String getMajor() { return major; }
    public String getPart() { return part; }
    public int getGeneration() { return generation; }
    public RoleType getRoleType() { return roleType; }
    public String getStudentId() { return studentId; }
    public String getPosition() { return position; }
    public List<Assignment> getAssignments() { return assignments; }
    public Team getTeam() { return team; }
}