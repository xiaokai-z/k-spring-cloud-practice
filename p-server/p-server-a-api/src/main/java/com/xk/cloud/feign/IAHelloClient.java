package com.xk.cloud.feign;

import com.xk.cloud.config.ToApiExceptionConfiguration;
import com.xk.cloud.vo.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Server A Hello Feign接口类
 */
@FeignClient(contextId = "aHelloClient",
        name = "a",
        url = "http://localhost:9012/server-a",
        path = IAHelloClient.API_PREFIX,
        configuration = ToApiExceptionConfiguration.class)
public interface IAHelloClient {

    String API_PREFIX = "/a";

    @GetMapping("/hello")
    R<String> hello(@RequestParam(value = "name", defaultValue = "") String name);

    @GetMapping("/hei")
    R<String> hei(@RequestParam(value = "name", defaultValue = "") String name);

}
