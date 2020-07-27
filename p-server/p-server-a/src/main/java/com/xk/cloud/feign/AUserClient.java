package com.xk.cloud.feign;

import com.xk.cloud.vo.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping("/a/user")
@RestController
public class AUserClient implements IAUserClient {

    @Override
    @GetMapping("/info")
    public R<String> info() {
        return R.ok("我是 a");
    }
}
