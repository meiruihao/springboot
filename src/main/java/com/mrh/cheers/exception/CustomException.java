package com.mrh.cheers.exception;

/**
 * 自定义异常
 * @author user
 */
public class CustomException extends RuntimeException {


    private static final long serialVersionUID = 4564124491192825748L;

    private int code;

    private String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public CustomException(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public CustomException() {
        super();
    }
}
