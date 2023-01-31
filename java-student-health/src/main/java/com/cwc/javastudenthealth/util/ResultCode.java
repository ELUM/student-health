package com.cwc.javastudenthealth.util;

public enum ResultCode {
    SUCCESS("成功", 200),
    FAILED("失败", 500),
    NO_LOGIN("没有登陆", 101),
    ACCOUNT_PWD_NOT_EXIST("帐号或密码不存在", 102),
    ACCOUNT_EXIST("用户已存在", 103),
    ACCOUNT_ERROR("账号或密码错误", 104),
    PARAMS_ERROR("参数有误", 105),
    TOKEN_ERROR("令牌验证失败", 400),
    TOKEN_NOT_FOUND("未携带令牌", 401);


    private String msg;
    private Integer code;

    ResultCode(String msg, Integer code) {
        this.msg = msg;
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
