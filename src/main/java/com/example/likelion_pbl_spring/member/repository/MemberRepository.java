package com.example.likelion_pbl_spring.member.repository;

import com.example.likelion_pbl_spring.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

// 🌟 JpaRepository<Member, Long>을 상속받으면 save, findAll, delete 등의 기능이 자동으로 구현됩니다!
public interface MemberRepository extends JpaRepository<Member, Long> {

    // 🌟 이름을 기준으로 회원을 찾는 기능만 추가로 정의해 줍니다.
    Optional<Member> findByName(String name);

    // 🌟 이름이 이미 존재하는지 확인하는 기능도 JPA 규칙에 맞게 선언합니다.
    boolean existsByName(String name);
}