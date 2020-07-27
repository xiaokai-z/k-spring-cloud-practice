package com.xk.cloud.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.hystrix.exception.HystrixBadRequestException;
import com.xk.cloud.vo.R;
import feign.Response;
import feign.Util;
import feign.codec.ErrorDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * feign 服务异常不进入熔断
 * 不进入熔断，需要把异常封装成HystrixBadRequestException，对于HystrixBadRequestException，Feign会直接抛出，不进入熔断方法
 */
public class NotBreakerConfiguration {
    @Bean
    public ErrorDecoder errorDecoder() {
        return new UserErrorDecoder();
    }

    /**
     * 自定义错误
     */
    public class UserErrorDecoder implements ErrorDecoder {
        private Logger logger = LoggerFactory.getLogger(getClass());
        ObjectMapper objectMapper = new ObjectMapper();

        @Override
        public Exception decode(String methodKey, Response response) {
            Exception exception = null;
            try {
                String json = Util.toString(response.body().asReader(StandardCharsets.UTF_8));
                R result = objectMapper.readValue(json, R.class);
                // 业务异常包装成 HystrixBadRequestException，不进入熔断逻辑
                if (result.getCode() != null && result.getCode() != 0) {
                    exception = new HystrixBadRequestException(result.getMsg());
                } else {
                    exception = new RuntimeException(json);
                }
            } catch (IOException ex) {
                logger.error("feign.IOException", ex);
            }
            return exception;
        }
    }
}
