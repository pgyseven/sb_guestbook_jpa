package com.kgy.sb_guestbook_jpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing // AuditingEntityListener.class 활성화시키는 annotation
public class SbGuestbookJpaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SbGuestbookJpaApplication.class, args);
	}

}
