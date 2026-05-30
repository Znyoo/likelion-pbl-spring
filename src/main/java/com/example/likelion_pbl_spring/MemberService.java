package com.example.likelion_pbl_spring;

import com.example.likelion_pbl_spring.role.Member;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service // 👈 자동 주입을 위해 빈 등록 어노테이션 추가
public class MemberService {
    // 제약조건 준수: 인터페이스에만 의존하며 final로 고정하여 변경 방지
    private final MemberRepository memberRepository;

    // 제약조건 준수: 생성자를 통해 외부에서 구현체를 주입받음 (DI)
    @Autowired // 👈 스프링이 컨테이너에서 알맞은 Repository 빈을 자동으로 꽂아줍니다.
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public boolean registerMember(String name, Member member) {
        if (memberRepository.findByName(name) != null) {
            System.out.println("\n❌ [오류] 이미 존재하는 이름입니다: " + name);
            return false;
        }
        memberRepository.save(name, member);
        return true;
    }

    public Member getMemberByName(String name) {
        return memberRepository.findByName(name);
    }

    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }
}