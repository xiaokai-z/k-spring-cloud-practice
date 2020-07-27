package com.xk.cloud.controller;

import com.xk.cloud.feign.IAHelloClient;
import com.xk.cloud.feign.IAUserClient;
import com.xk.cloud.vo.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Slf4j
@RequestMapping("/b")
@RestController
public class BHelloController {

    @Resource
    private IAHelloClient aHelloClient;

    @Resource
    private IAUserClient aUserClient;

    @GetMapping("/hello")
    public String hello(@RequestParam(defaultValue = "") String name) {
        return "b hello " + name;
    }

    @GetMapping("/hello2a")
    public String hello2a(@RequestParam(defaultValue = "") String name) {

        R<String> hello = aHelloClient.hello(name);
        log.info("receive a response:{}", hello);
        return hello.getData();
    }

    @GetMapping("/hei2a")
    public String hei2a(@RequestParam(defaultValue = "") String name) {

        R<String> hello = aHelloClient.hei(name);
        log.info("receive a response:{}", hello);
        return hello.getMsg();
    }

    @GetMapping("/user")
    public String user() {
        R<String> user = aUserClient.info();
        log.info("receive a response:{}", user);
        return user.getData();
    }
}
