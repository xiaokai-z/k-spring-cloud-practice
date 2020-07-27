
package com.xk.cloud.base.feign.support;

/**
 * 远程接口调用出现的业务异常
 */

public class ApiServiceException extends RuntimeException {

	/**
	 * 错误编码
	 */
	private Integer code;

	/**
	 * 报错的类名
	 */
	private String exceptionClazz;

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getExceptionClazz() {
		return exceptionClazz;
	}

	public void setExceptionClazz(String exceptionClazz) {
		this.exceptionClazz = exceptionClazz;
	}

	public ApiServiceException(Integer code, String message) {
		super(message);
		this.code = code;
	}

}
