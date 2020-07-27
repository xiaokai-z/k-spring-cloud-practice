package com.xk.cloud.config;

import com.fasterxml.jackson.databind.ObjectMapper;
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
 * 保留原始异常信息
 */
public class KeepErrMsgConfiguration {
    @Bean
    public ErrorDecoder errorDecoder() {
        return new UserErrorDecoder();
    }

    /**
     * 自定义错误
     */
    public class UserErrorDecoder implements ErrorDecoder {
        private Logger logger = LoggerFactory.getLogger(UserErrorDecoder.class);
        ObjectMapper objectMapper = new ObjectMapper();

        @Override
        public Exception decode(String methodKey, Response response) {
            Exception exception = null;
            try {
                // 获取原始的返回内容
                String json = Util.toString(response.body().asReader(StandardCharsets.UTF_8));
                // 将返回内容反序列化为Result，这里应根据自身项目作修改
                R result = objectMapper.readValue(json, R.class);
                // 业务异常抛出简单的 RuntimeException，保留原来错误信息
                if (result.getCode() != null && result.getCode() != 0) {
                    exception = new RuntimeException(result.getMsg());
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
