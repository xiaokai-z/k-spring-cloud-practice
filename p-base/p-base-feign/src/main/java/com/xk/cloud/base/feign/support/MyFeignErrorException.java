package com.xk.cloud.base.feign.support;

import feign.Response;

public class MyFeignErrorException extends RuntimeException {
    private final String methodKey;
    private Response response;


    public MyFeignErrorException(String methodKey, Response response) {
        this.methodKey = methodKey;
        this.response = response;
    }


    public Response getResponse() {
        return response;
    }

    public String getMethodKey() {
        return methodKey;
    }
}