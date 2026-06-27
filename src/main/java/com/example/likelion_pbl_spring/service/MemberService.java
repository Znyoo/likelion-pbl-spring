package com.example.likelion_pbl_spring.service;

import com.example.likelion_pbl_spring.Member;
import com.example.likelion_pbl_spring.RoleType;
import com.example.likelion_pbl_spring.dto.*;
import com.example.likelion_pbl_spring.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true) // 🌟 데이터 조회 시 성능을 최적화합니다.
public class MemberService {

    private final MemberRepository memberRepository;

    // 생성자 주입을 통해 스프링이 자동으로 레포지토리를 연결해 줍니다.
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    // ================= [ 1. 회원 등록 로직 ] =================

    @Transactional // 🌟 데이터를 저장/수정하므로 영속성 컨텍스트 트랜잭션을 걸어줍니다.
    public Member createLion(LionCreateRequest request) {
        // 이름 중복 검증
        if (memberRepository.existsByName(request.getName())) {
            return null; // 이미 존재하는 이름이면 null 반환 (또는 예외 처리)
        }

        // Member 엔티티의 아기사자 생성 메서드 호출 후 저장
        Member lion = Member.createLion(
                request.getName(),
                request.getMajor(),
                request.getGeneration(),
                request.getPart(),
                request.getStudentId()
        );
        return memberRepository.save(lion);
    }

    @Transactional
    public Member createStaff(StaffCreateRequest request) {
        // 이름 중복 검증
        if (memberRepository.existsByName(request.getName())) {
            return null;
        }

        // Member 엔티티의 운영진 생성 메서드 호출 후 저장
        Member staff = Member.createStaff(
                request.getName(),
                request.getMajor(),
                request.getPart(),
                request.getPosition()
        );
        return memberRepository.save(staff);
    }

    // ================= [ 2. 회원 수정 로직 ] =================

    @Transactional
    public Member updateLion(String name, LionUpdateRequest request) {
        // 데이터베이스에서 기존 회원을 꺼내옵니다.
        Member member = memberRepository.findByName(name).orElse(null);

        // 아기사자가 맞는지 확인 후 수정 (더티 체킹 기능으로 자동 업데이트됩니다)
        if (member != null && member.getRoleType() == RoleType.LION) {
            member.updateLion(
                    request.getMajor(),
                    request.getGeneration(),
                    request.getPart(),
                    request.getStudentId()
            );
            return member;
        }
        return null;
    }

    @Transactional
    public Member updateStaff(String name, StaffUpdateRequest request) {
        Member member = memberRepository.findByName(name).orElse(null);

        // 운영진이 맞는지 확인 후 수정
        if (member != null && member.getRoleType() == RoleType.STAFF) {
            member.updateStaff(
                    request.getMajor(),
                    request.getPart(),
                    request.getPosition()
            );
            return member;
        }
        return null;
    }

    // ================= [ 3. 조회 및 삭제 로직 ] =================

    public Member getMemberByName(String name) {
        // Optional로 감싸져 나오므로 없으면 null을 반환하도록 설정합니다.
        return memberRepository.findByName(name).orElse(null);
    }

    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    @Transactional
    public boolean deleteMember(String name) {
        Member member = memberRepository.findByName(name).orElse(null);
        if (member != null) {
            memberRepository.delete(member); // 데이터베이스에서 삭제 명령 실행
            return true;
        }
        return false;
    }
}