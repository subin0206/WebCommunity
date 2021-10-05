package com.rlasb.admin.config.auth;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.PARAMETER)
//이 어노테이션이 생성될 수 있는 위치를 저장
//PARAMETER로 지정하거나 메소드의 파라미터로 선언된 객체에서만 사용할 수 있음.
//클래스 선언문에 쓸 수 있는 TYPE 등이 있음.
@Retention(RetentionPolicy.RUNTIME)

public @interface LoginUser {
    //interface -> 이 파일을 어노테이션 클래스로 지정함.
    //LoginUser라는 이름을 가진 어노테이션이 생성됨.
}
