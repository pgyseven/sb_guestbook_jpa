package com.kgy.sb_guestbook_jpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing // AuditingEntityListener.class 활성화시키는 annotation
public class SbGuestbookJpaApplication {

	// main() : 프로그램의 entry point (진입점 : 시작점)
	// OS(운영체제)에 의해서 이 프로그램을 실행 시키면 os -> 메인 함수 호출 -> tomcat 가동 -> spring boot 가동 ->소스코드 로딩(컨테이너 적재)  / Ex 실행 파일 exe를 클릭하면 메인함수를 먼저 호춯한거임
	public static void main(String[] args) {
		SpringApplication.run(SbGuestbookJpaApplication.class, args);
	}

}
