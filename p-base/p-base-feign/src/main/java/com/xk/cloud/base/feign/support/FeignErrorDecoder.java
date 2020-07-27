package com.xk.cloud.base.feign.support;


import feign.Response;
import feign.codec.ErrorDecoder;

/**
 * feign错误解码器
 */
public class FeignErrorDecoder implements ErrorDecoder {

    @Override
    public Exception decode(String methodKey, Response response) {
        return new MyFeignErrorException(methodKey, response);
    }
}