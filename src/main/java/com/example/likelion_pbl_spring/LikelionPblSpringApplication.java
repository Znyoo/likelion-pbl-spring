package com.example.likelion_pbl_spring;

import com.example.likelion_pbl_spring.service.MemberService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class LikelionPblSpringApplication {

	public static void main(String[] args) {
		// 1. 스프링 부트를 실행하고 컨테이너(ac)를 받아옵니다.
		ApplicationContext ac = SpringApplication.run(LikelionPblSpringApplication.class, args);

		// 2. 우리가 AppConfig에 등록해둔 MemberService가 잘 들어있는지 쏙 꺼내옵니다.
		MemberService memberService = ac.getBean(MemberService.class);;

		// 3. 콘솔창에 성공 메시지와 객체 주소값이 잘 찍히는지 확인합니다.
		System.out.println("=========================================");
		System.out.println("스프링 빈 조회 성공!! -> " + memberService);
		System.out.println("=========================================");
	}
}