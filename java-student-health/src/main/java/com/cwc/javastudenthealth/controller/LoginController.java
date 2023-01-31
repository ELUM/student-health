package com.cwc.javastudenthealth.controller;

import com.cwc.javastudenthealth.entity.User;
import com.cwc.javastudenthealth.handler.Authorization;
import com.cwc.javastudenthealth.service.LoginService;
import com.cwc.javastudenthealth.util.JwtUtils;
import com.cwc.javastudenthealth.util.Result;
import com.cwc.javastudenthealth.util.ResultCode;
import org.omg.CORBA.StringHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class LoginController {

    @Autowired
    LoginService loginService;

    @PostMapping("/login")
    public Result login(@RequestBody User user) {
        String token = loginService.login(user.getUsername(), user.getPassword());
        if (token != null) {
            return Result.success(token, ResultCode.SUCCESS.getMsg());
        }
        return Result.failed(null, ResultCode.ACCOUNT_ERROR.getMsg());
    }

    @Authorization
    @GetMapping("/info")
    public Result info(@RequestHeader("Authorization") String authorization) {
        User user = new User();
        user.setUsername(JwtUtils.verifyToken(authorization).get("userName").asString());
        return Result.success(user, ResultCode.SUCCESS.getMsg());
    }
}
