package com.xk.cloud.config;

import com.xk.cloud.base.feign.support.ApiServiceException;
import com.xk.cloud.vo.R;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理器（拦截所有的控制器）（带有@RequestMapping注解的方法上都会拦截）
 *
 * @ControllerAdvice注解将作用在所有注解了@RequestMapping的控制器的方法上
 * @ExceptionHandler： 用于全局处理控制器里的异常。
 */
@RestControllerAdvice
public class DefaultExceptionHandler {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    /**
     * 拦截各个服务的具体异常
     */
    @ExceptionHandler(ApiServiceException.class)
    public R apiService(ApiServiceException e) {
        log.error("调用服务接口异常:{}", e.getLocalizedMessage());
        return R.failed(e.getLocalizedMessage());
    }
}
