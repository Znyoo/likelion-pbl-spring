package com.example.likelion_pbl_spring.member.controller;

import com.example.likelion_pbl_spring.member.domain.Member;
import com.example.likelion_pbl_spring.member.dto.*;
import com.example.likelion_pbl_spring.member.service.MemberService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/members") // 🌟 과제 제약사항에 따른 기본 주소
public class MemberController {

    private final MemberService injectedMemberService;

    public MemberController(MemberService memberService) {
        this.injectedMemberService = memberService;
    }

    // ================= [ 1. 회원 등록 API ] =================

    // 아기사자 등록 (POST /members/lions)
    @PostMapping("/lions")
    public ResponseEntity<?> createLion(@RequestBody LionCreateRequest request) {
        Member lion = injectedMemberService.createLion(request);
        if (lion == null) {
            return ResponseEntity.badRequest().body("이미 존재하는 이름입니다.");
        }
        return ResponseEntity.ok(MemberResponse.from(lion)); // 통합 응답 DTO 사용
    }

    // 운영진 등록 (POST /members/staffs)
    @PostMapping("/staffs")
    public ResponseEntity<?> createStaff(@RequestBody StaffCreateRequest request) {
        Member staff = injectedMemberService.createStaff(request);
        if (staff == null) {
            return ResponseEntity.badRequest().body("이미 존재하는 이름입니다.");
        }
        return ResponseEntity.ok(MemberResponse.from(staff)); // 통합 응답 DTO 사용
    }

    // ================= [ 2. 회원 수정 API ] =================

    // 아기사자 수정 (PUT /members/lions/{id}) - name에서 id 식별로 변경
    @PutMapping("/lions/{id}")
    public ResponseEntity<?> updateLion(@PathVariable Long id, @RequestBody LionUpdateRequest request) {
        Member updated = injectedMemberService.updateLion(id, request);
        if (updated == null) {
            return ResponseEntity.badRequest().body("해당 ID의 아기사자를 찾을 수 없거나 수정에 실패했습니다.");
        }
        return ResponseEntity.ok(MemberResponse.from(updated));
    }

    // 운영진 수정 (PUT /members/staffs/{id}) - name에서 id 식별로 변경
    @PutMapping("/staffs/{id}")
    public ResponseEntity<?> updateStaff(@PathVariable Long id, @RequestBody StaffUpdateRequest request) {
        Member updated = injectedMemberService.updateStaff(id, request);
        if (updated == null) {
            return ResponseEntity.badRequest().body("해당 ID의 운영진을 찾을 수 없거나 수정에 실패했습니다.");
        }
        return ResponseEntity.ok(MemberResponse.from(updated));
    }

    // ================= [ 3. 조회 및 삭제 API ] =================

    // 단건 조회 (GET /members/{id}) - name에서 id 식별로 변경
    @GetMapping("/{id}")
    public ResponseEntity<?> getMemberById(@PathVariable Long id) {
        Member member = injectedMemberService.getMemberById(id); // 서비스도 ID 조회 메서드 필요
        if (member == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(MemberResponse.from(member));
    }

    // 전체 조회 (GET /members)
    @GetMapping
    public ResponseEntity<List<MemberResponse>> getAllMembers() {
        List<Member> members = injectedMemberService.getAllMembers();

        List<MemberResponse> responses = members.stream()
                .map(MemberResponse::from) // roleType 분기 없이 하나로 통합 변환
                .collect(Collectors.toList());

        return ResponseEntity.ok(responses);
    }

    // 삭제 (DELETE /members/{id}) - name에서 id 식별로 변경
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMember(@PathVariable Long id) {
        boolean isDeleted = injectedMemberService.deleteMember(id);
        if (!isDeleted) {
            return ResponseEntity.badRequest().body("해당 ID의 회원이 존재하지 않습니다.");
        }
        return ResponseEntity.ok("성공적으로 삭제되었습니다.");
    }
}