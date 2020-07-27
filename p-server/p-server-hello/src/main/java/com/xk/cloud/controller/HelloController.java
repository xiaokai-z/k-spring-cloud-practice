package com.xk.cloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping("/hello")
@RestController
public class HelloController {
    @GetMapping("/")
    public String hello(@RequestParam(defaultValue = "") String name) {
        
        log.info("info 日志");
        log.error("error 日志");
        log.debug("debug 日志");

        if (log.isDebugEnabled()) {
            log.error("debug error 日志");
        }

        return "hello " + name;
    }
}
