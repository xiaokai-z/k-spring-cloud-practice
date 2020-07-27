package com.xk.cloud.feign;

import com.xk.cloud.vo.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequestMapping("/a")
@RestController
public class AHelloClient implements IAHelloClient {

    @Override
    @GetMapping("/hello")
    public R<String> hello(@RequestParam(defaultValue = "") String name) {
        log.info("A Hello receive:{}", name);
        return R.ok("a hello " + name);
    }

    @Override
    @GetMapping("/hei")
    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    public R<String> hei(@RequestParam(defaultValue = "") String name) {
        log.info("A hei receive:{}", name);
        return R.failed("a hei " + name);
    }
}
