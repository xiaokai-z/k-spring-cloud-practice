package com.xk.cloud.feign;

import com.xk.cloud.config.KeepErrMsgConfiguration;
import com.xk.cloud.vo.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Server A Hello Feign接口类
 */
@FeignClient(contextId = "aUserClient",
        name = "a",
        url = "http://localhost:9012/server-a",
        path = IAUserClient.API_PREFIX,
        configuration = KeepErrMsgConfiguration.class)
public interface IAUserClient {

    String API_PREFIX = "/a/user";

    @GetMapping("/info")
    R<String> info();

}
