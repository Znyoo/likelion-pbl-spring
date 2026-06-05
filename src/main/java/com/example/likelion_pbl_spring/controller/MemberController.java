package com.example.likelion_pbl_spring.controller;

import com.example.likelion_pbl_spring.dto.*;
import com.example.likelion_pbl_spring.role.Lion;
import com.example.likelion_pbl_spring.role.Member;
import com.example.likelion_pbl_spring.role.Staff;
import com.example.likelion_pbl_spring.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    // ================= [ 0. 전체 멤버 조회 ] =================
    @GetMapping
    public ResponseEntity<List<Member>> getAllMembers() {
        // GET http://127.0.0.1:8080/members 요청 시 200 OK와 함께 전체 리스트 반환
        return ResponseEntity.ok(memberService.getAllMembers());
    }

    // ================= [ 1. Lion(아기사자) 등록 ] =================
    @PostMapping("/lions")
    public ResponseEntity<?> registerLion(@RequestBody LionCreateRequest request) {
        Lion lion = memberService.createLion(request);
        if (lion == null) {
            // 이름 중복 등록 시 409 Conflict 반환
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        // 정상 등록 시 201 Created와 함께 캡처본 포맷(roleName 포함)으로 반환
        return ResponseEntity.status(HttpStatus.CREATED).body(LionResponse.from(lion));
    }

    // ================= [ 2. Staff(운영진) 등록 ] =================
    @PostMapping("/staffs")
    public ResponseEntity<?> registerStaff(@RequestBody StaffCreateRequest request) {
        Staff staff = memberService.createStaff(request);
        if (staff == null) {
            // 이름 중복 등록 시 409 Conflict 반환
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        // 정상 등록 시 201 Created와 함께 캡처본 포맷(roleName 포함)으로 반환
        return ResponseEntity.status(HttpStatus.CREATED).body(StaffResponse.from(staff));
    }

    // ================= [ 4. 단일 멤버 조회 (이름 기준) ] =================
    @GetMapping("/{name}")
    public ResponseEntity<?> findMemberByName(@PathVariable String name) {
        Member member = memberService.getMemberByName(name);
        if (member == null) {
            // 없는 이름 조회 시 404 Not Found 반환
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        // 인스턴스 타입에 맞춰 각각 맞는 Response DTO로 감싸서 반환 (roleName 매핑용)
        if (member instanceof Lion) {
            return ResponseEntity.ok(LionResponse.from((Lion) member));
        } else if (member instanceof Staff) {
            return ResponseEntity.ok(StaffResponse.from((Staff) member));
        }
        return ResponseEntity.ok(member);
    }

    // ================= [ 5. Lion 수정 ] =================
    @PutMapping("/lions/{name}")
    public ResponseEntity<?> updateLion(@PathVariable String name, @RequestBody LionUpdateRequest request) {
        Lion updatedLion = memberService.updateLion(name, request);
        if (updatedLion == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(LionResponse.from(updatedLion));
    }

    // ================= [ 6. Staff 수정 ] =================
    @PutMapping("/staffs/{name}")
    public ResponseEntity<?> updateStaff(@PathVariable String name, @RequestBody StaffUpdateRequest request) {
        Staff updatedStaff = memberService.updateStaff(name, request);
        if (updatedStaff == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(StaffResponse.from(updatedStaff));
    }

    // ================= [ 7. 멤버 삭제 ] =================
    @DeleteMapping("/{name}")
    public ResponseEntity<?> deleteMember(@PathVariable String name) {
        boolean isDeleted = memberService.deleteMember(name);
        if (!isDeleted) {
            // 없는 이름 삭제 시 404 Not Found 반환
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        // 삭제 성공 시 바디 없이 204 No Content 반환
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}