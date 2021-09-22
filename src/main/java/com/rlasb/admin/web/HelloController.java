package com.rlasb.admin.web;

import com.rlasb.admin.web.dto.HelloResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
//컨트롤러를 JSON을 반환하는 컨트롤러를 만듬
//@ResponseBody를 각 메소드마다 선언했던 것을 한번에 사용할 수 있게 해줌.
public class HelloController {
    @GetMapping("/hello/dto")
    //HTTP Method인 Get의 요청을 받을 수 있는 API를 만듬.
    public HelloResponseDto helloDto(@RequestParam("name") String name,
                                     @RequestParam("amount") int amount){
        return new HelloResponseDto(name, amount);
    }
}
