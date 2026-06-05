package com.example.likelion_pbl_spring;

import com.example.likelion_pbl_spring.repository.MemberRepository;
import com.example.likelion_pbl_spring.repository.MemoryMemberRepository; // 👈 이동한 저장소 패키지 임포트!
import com.example.likelion_pbl_spring.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }
}