package com.cwc.javastudenthealth.util;

import lombok.Data;

@Data
public class Result {
    private int code;
    private String msg;
    private Object data;

    Result(Integer code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static Result success(Object data, String msg) {
        return new Result(ResultCode.SUCCESS.getCode(), msg, data);
    }

    public static Result failed(Object data, String msg) {
        return new Result(ResultCode.FAILED.getCode(), msg, data);
    }

    public static Result base(Integer code, String msg) {
        return new Result(code, msg, null);
    }
}
