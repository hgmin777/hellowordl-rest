package com.example.controller;

import com.example.domain.Hello;
import com.example.domain.Param;
import com.example.response.JSONResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


// 예제 참조 : https://easy-coding.tistory.com/2?category=717044


@RestController
@RequestMapping(value = "hello")
public class HelloController {
	
	
    // http://localhost:8080/hello
	
    @GetMapping()
    public Hello get() {
        return new Hello(0, "Hello Spring Boot!");
    }


    
    // http://localhost:8080/hello/args?msg=m1&msg2=m2
    
    @GetMapping("args")
    public Hello getArgs(
            @RequestParam(value = "msg")String msg,
            @RequestParam(value = "msg2")String msg2) {
        Hello hello = new Hello(0, msg);
        hello.setMsg(hello.getMsg() + ", " + msg2);

        return hello;
    }
    
    // http://localhost:8080/hello/argsDefault?msg=m1    

    @GetMapping("argsDefault")
    public Hello getArgsDefault(
            @RequestParam(value = "msg")String msg,
            @RequestParam(value = "msg2", required = false, defaultValue = "msg2Default")String msg2) {
        Hello hello = new Hello(0, msg);
        hello.setMsg(hello.getMsg() + ", " + msg2);

        return hello;
    }
    
    // Postman 활용
    

    @PostMapping()
    public Hello post(@RequestBody Param param) {
        String msg = "Data1 : " + param.getData1() + ", Data2 : " + param.getData2();
        Hello hello = new Hello(0, msg);
        return hello;
    }

    
    // Postman 활용
    
    @PutMapping()
    public Hello put(@RequestParam(value = "param1")String param1) {
        Hello hello = new Hello(0, "Put Request : " + param1);
        return hello;
    }

    @GetMapping("jsonResponse")
    public JSONResponse<Hello> getJsonResponse(
            @RequestParam(value = "msg")String msg) {
        Hello hello = new Hello(0, msg);

        JSONResponse<Hello> response = new JSONResponse<Hello>();
        response.setCode(0);
        response.setMsg("성공");
        response.setData(hello);

        return response;
    }
}