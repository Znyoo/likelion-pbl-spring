package com.example.likelion_pbl_spring.member.service;

import com.example.likelion_pbl_spring.member.domain.Member;
import com.example.likelion_pbl_spring.member.domain.RoleType;
import com.example.likelion_pbl_spring.member.dto.LionCreateRequest;
import com.example.likelion_pbl_spring.member.dto.LionUpdateRequest;
import com.example.likelion_pbl_spring.member.dto.StaffCreateRequest;
import com.example.likelion_pbl_spring.member.dto.StaffUpdateRequest;
import com.example.likelion_pbl_spring.member.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true) // 🌟 과제 목표: 조회 성능 최적화를 위한 readOnly 적용
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Transactional // 🌟 과제 목표: 쓰기 작업에는 별도 트랜잭션 보장
    public Member createLion(LionCreateRequest request) {
        if (memberRepository.findByName(request.getName()).isPresent()) {
            return null;
        }
        Member lion = new Member(request.getName(), request.getMajor(), request.getPart(), request.getGeneration(), RoleType.LION, request.getStudentId(), null);
        return memberRepository.save(lion);
    }

    @Transactional
    public Member createStaff(StaffCreateRequest request) {
        if (memberRepository.findByName(request.getName()).isPresent()) {
            return null;
        }
        Member staff = new Member(request.getName(), request.getMajor(), request.getPart(), request.getGeneration(), RoleType.STAFF, null, request.getPosition());
        return memberRepository.save(staff);
    }

    @Transactional
    public Member updateLion(Long id, LionUpdateRequest request) {
        Member lion = memberRepository.findById(id).orElse(null);
        if (lion == null || lion.getRoleType() != RoleType.LION) return null;

        lion.updateInfo(request.getMajor(), request.getGeneration(), request.getPart());
        lion.updateStudentId(request.getStudentId());
        return memberRepository.save(lion);
    }

    @Transactional
    public Member updateStaff(Long id, StaffUpdateRequest request) {
        Member staff = memberRepository.findById(id).orElse(null);
        if (staff == null || staff.getRoleType() != RoleType.STAFF) return null;

        staff.updateInfo(request.getMajor(), request.getGeneration(), request.getPart());
        staff.updatePosition(request.getPosition());
        return memberRepository.save(staff);
    }

    public Member getMemberById(Long id) {
        return memberRepository.findById(id).orElse(null);
    }

    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    @Transactional
    public boolean deleteMember(Long id) {
        if (!memberRepository.existsById(id)) {
            return false;
        }
        memberRepository.deleteById(id);
        return true;
    }
}