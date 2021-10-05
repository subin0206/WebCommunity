package com.rlasb.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication //스프링 부트의 자동 설정, 스프링 Bean 읽기와 생성을 모두 자동으로 설정.
public class Application {
    //main 메소드에서 실행하는 SpringApplication.run으로 내장 WAS
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
