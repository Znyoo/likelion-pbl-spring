package com.example.likelion_pbl_spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public MemberRepository memberRepository() {
        // 기존에 쓰시던 MemoryMemberRepository를 스프링 빈으로 등록합니다.
        return new MemoryMemberRepository();
    }

    @Bean
    public MemberService memberService() {
        // memberRepository()를 매개변수로 넣어서 의존성을 주입(DI)해줍니다.
        return new MemberService(memberRepository());
    }
}