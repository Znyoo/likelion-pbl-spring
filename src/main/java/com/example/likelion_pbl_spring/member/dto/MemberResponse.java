package com.example.likelion_pbl_spring.member.dto;

import com.example.likelion_pbl_spring.member.domain.Member; // 🌟 domain을 빼고 올바른 위치로 수정했습니다!


public class MemberResponse {
    private final Long id;          // 🌟 필드가 변하지 않으므로 final 추가 경고를 해결했습니다.
    private final String name;
    private final String major;
    private final int generation;
    private final String part;
    private final String roleName;
    private final String studentId;
    private final String position;

    public MemberResponse(Long id, String name, String major, int generation, String part, String roleName, String studentId, String position) {
        this.id = id;
        this.name = name;
        this.major = major;
        this.generation = generation;
        this.part = part;
        this.roleName = roleName;
        this.studentId = studentId;
        this.position = position;
    }

    public static MemberResponse from(Member member) {
        return new MemberResponse(
                member.getId(),
                member.getName(),
                member.getMajor(),
                member.getGeneration(),
                member.getPart(),
                member.getRoleType().getDisplayName(),
                member.getStudentId(),
                member.getPosition()
        );
    }

    // Getter 메서드들 (컨트롤러와 JSON 변환 시 실제로 사용됩니다)
    public Long getId() { return id; }
    public String getName() { return name; }
    public String getMajor() { return major; }
    public int getGeneration() { return generation; }
    public String getPart() { return part; }
    public String getRoleName() { return roleName; }
    public String getStudentId() { return studentId; }
    public String getPosition() { return position; }
}