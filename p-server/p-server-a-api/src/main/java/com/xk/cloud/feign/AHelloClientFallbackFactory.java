package com.xk.cloud.feign;

import com.xk.cloud.base.feign.support.ApiServiceException;
import com.xk.cloud.vo.R;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Slf4j
@Component
public class AHelloClientFallbackFactory implements FallbackFactory<IAHelloClient> {

    @Override
    public IAHelloClient create(Throwable throwable) {
        if (throwable instanceof ApiServiceException) {
            ApiServiceException ex = (ApiServiceException) throwable;
            throw ex;
        }

        String msg = throwable == null ? "" : throwable.getMessage();
        if (!StringUtils.isEmpty(msg)) {
            log.error("feign fallback:{}", msg);
        }
        return new IAHelloClient() {
            @Override
            public R<String> hello(String name) {
                return R.failed("hello 方法报错");
            }

            @Override
            public R<String> hei(String name) {

                return R.failed("hei 方法报错" + throwable.getLocalizedMessage());
            }
        };
    }
}
