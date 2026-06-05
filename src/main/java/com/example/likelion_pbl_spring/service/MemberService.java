package com.example.likelion_pbl_spring.service;

import com.example.likelion_pbl_spring.dto.*;
import com.example.likelion_pbl_spring.role.Lion;
import com.example.likelion_pbl_spring.role.Member;
import com.example.likelion_pbl_spring.role.Staff;
import com.example.likelion_pbl_spring.repository.MemberRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    // ================= [ 1. 생성 로직 ] =================
    public Lion createLion(LionCreateRequest request) {
        if (memberRepository.existsByName(request.getName())) { // 중복 검사
            return null;
        }
        Lion lion = new Lion(
                request.getName(),
                request.getMajor(),
                request.getGeneration(),
                request.getPart(),
                request.getStudentId()
        );
        memberRepository.save(request.getName(), lion);
        return lion;
    }

    public Staff createStaff(StaffCreateRequest request) {
        if (memberRepository.existsByName(request.getName())) { // 중복 검사
            return null;
        }
        Staff staff = new Staff(
                request.getName(),
                request.getMajor(),
                request.getGeneration(),
                request.getPart(),
                request.getPosition()
        );
        memberRepository.save(request.getName(), staff);
        return staff;
    }

    // ================= [ 2. 수정 로직 ] =================
    public Lion updateLion(String name, LionUpdateRequest request) {
        Member member = memberRepository.findByName(name);
        if (!(member instanceof Lion)) { // 인텔리제이 경고를 반영한 널체크 최적화 식
            return null;
        }
        Lion updatedLion = new Lion(
                name,
                request.getMajor(),
                request.getGeneration(),
                request.getPart(),
                request.getStudentId()
        );
        memberRepository.updateByName(name, updatedLion);
        return updatedLion;
    }

    public Staff updateStaff(String name, StaffUpdateRequest request) {
        Member member = memberRepository.findByName(name);
        if (!(member instanceof Staff)) { // 인텔리제이 경고를 반영한 널체크 최적화 식
            return null;
        }
        Staff updatedStaff = new Staff(
                name,
                request.getMajor(),
                request.getGeneration(),
                request.getPart(),
                request.getPosition()
        );
        memberRepository.updateByName(name, updatedStaff);
        return updatedStaff;
    }

    // ================= [ 3. 조회 및 삭제 로직 ] =================
    public Member getMemberByName(String name) {
        return memberRepository.findByName(name);
    }

    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    public boolean deleteMember(String name) {
        return memberRepository.deleteByName(name);
    }
}