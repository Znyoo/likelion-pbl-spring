package com.example.likelion_pbl_spring.controller;

import com.example.likelion_pbl_spring.Member;
import com.example.likelion_pbl_spring.RoleType;
import com.example.likelion_pbl_spring.dto.*;
import com.example.likelion_pbl_spring.service.MemberService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController // 🌟 [필수] 스프링이 이 클래스를 컨트롤러로 인식하게 합니다!
@RequestMapping("/members") // 🌟 기본 주소를 /members로 지정합니다.
public class MemberController {

    private final MemberService injectedMemberService;

    // 생성자 주입
    public MemberController(MemberService memberService) {
        this.injectedMemberService = memberService;
    }

    // ================= [ 1. 회원 등록 API ] =================

    @PostMapping("/lion")
    public ResponseEntity<?> createLion(@RequestBody LionCreateRequest request) {
        Member lion = injectedMemberService.createLion(request);
        if (lion == null) {
            return ResponseEntity.badRequest().body("이미 존재하는 이름입니다.");
        }
        return ResponseEntity.ok(LionResponse.fromEntity(lion));
    }

    @PostMapping("/staff")
    public ResponseEntity<?> createStaff(@RequestBody StaffCreateRequest request) {
        Member staff = injectedMemberService.createStaff(request);
        if (staff == null) {
            return ResponseEntity.badRequest().body("이미 존재하는 이름입니다.");
        }
        return ResponseEntity.ok(StaffResponse.fromEntity(staff));
    }

    // ================= [ 2. 회원 수정 API ] =================

    @PutMapping("/lion/{name}")
    public ResponseEntity<?> updateLion(@PathVariable String name, @RequestBody LionUpdateRequest request) {
        Member updated = injectedMemberService.updateLion(name, request);
        if (updated == null) {
            return ResponseEntity.badRequest().body("해당 이름의 아기사자를 찾을 수 없거나 수정에 실패했습니다.");
        }
        return ResponseEntity.ok(LionResponse.fromEntity(updated));
    }

    @PutMapping("/staff/{name}")
    public ResponseEntity<?> updateStaff(@PathVariable String name, @RequestBody StaffUpdateRequest request) {
        Member updated = injectedMemberService.updateStaff(name, request);
        if (updated == null) {
            return ResponseEntity.badRequest().body("해당 이름의 운영진을 찾을 수 없거나 수정에 실패했습니다.");
        }
        return ResponseEntity.ok(StaffResponse.fromEntity(updated));
    }

    // ================= [ 3. 조회 및 삭제 API ] =================

    @GetMapping("/{name}")
    public ResponseEntity<?> getMemberByName(@PathVariable String name) {
        Member member = injectedMemberService.getMemberByName(name);
        if (member == null) {
            return ResponseEntity.notFound().build();
        }

        if (member.getRoleType() == RoleType.LION) {
            return ResponseEntity.ok(LionResponse.fromEntity(member));
        } else {
            return ResponseEntity.ok(StaffResponse.fromEntity(member));
        }
    }

    @GetMapping
    public ResponseEntity<List<?>> getAllMembers() {
        List<Member> members = injectedMemberService.getAllMembers();

        List<?> responses = members.stream()
                .map(m -> m.getRoleType() == RoleType.LION
                        ? LionResponse.fromEntity(m)
                        : StaffResponse.fromEntity(m))
                .collect(Collectors.toList());

        return ResponseEntity.ok(responses);
    }

    @DeleteMapping("/{name}")
    public ResponseEntity<?> deleteMember(@PathVariable String name) {
        boolean isDeleted = injectedMemberService.deleteMember(name);
        if (!isDeleted) {
            return ResponseEntity.badRequest().body("해당 이름의 회원이 존재하지 않습니다.");
        }
        return ResponseEntity.ok("성공적으로 삭제되었습니다.");
    }
}