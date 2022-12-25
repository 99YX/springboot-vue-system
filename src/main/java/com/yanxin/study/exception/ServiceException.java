package com.yanxin.study.exception;

import lombok.Getter;

/**
 * 自定义异常
 */
@Getter
public class ServiceException extends RuntimeException {
    private String code;

    public ServiceException(String code, String msg) {
        //调用父亲的RuntimeException构造方法
        super(msg);
        System.out.println("<-------------code-------------->"+code);
        this.code = code;
    }

}
