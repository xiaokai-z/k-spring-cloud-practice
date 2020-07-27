package com.xk.cloud.config;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.xk.cloud.base.feign.support.ApiServiceException;
import com.xk.cloud.vo.R;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import feign.Response;
import feign.Util;
import feign.codec.ErrorDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class ToApiExceptionConfiguration {

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
                    exception = new ApiServiceException(result.getCode(), result.getMsg());
                } else {
                    exception = new ApiServiceException(result.getCode(), json);
                }
            } catch (IOException ex) {
                logger.error("feign.IOException", ex);
            }
            return exception;
        }
    }

    @Bean
    public RequestInterceptor feignHeaderProcessInterceptor() {
        return new GwssiRequestInterceptor();
    }


    public class GwssiRequestInterceptor implements RequestInterceptor {
        private Logger logger = LoggerFactory.getLogger(getClass());

        @Override
        public void apply(RequestTemplate requestTemplate) {
            logger.info("处理 header");
        }
    }
}