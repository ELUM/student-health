package com.cwc.javastudenthealth.controller;

import com.cwc.javastudenthealth.handler.Authorization;
import com.cwc.javastudenthealth.service.EchartsService;
import com.cwc.javastudenthealth.util.Result;
import com.cwc.javastudenthealth.util.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/count")
public class EchartsController {

    @Autowired
    EchartsService echartsService;

    @Authorization
    @GetMapping("/user")
    public Result getUserCount() {
        return Result.success(echartsService.getUserCount(), ResultCode.SUCCESS.getMsg());
    }

    @Authorization
    @GetMapping("/student")
    public Result getStudentCount() {
        return Result.success(echartsService.getStudentCount(), ResultCode.SUCCESS.getMsg());
    }

    @Authorization
    @GetMapping("/clazz")
    public Result getClazzCount() {
        return Result.success(echartsService.getClazzCount(), ResultCode.SUCCESS.getMsg());
    }

    @Authorization
    @GetMapping("/health")
    public Result getHealthCount() {
        return Result.success(echartsService.getHealthCount(), ResultCode.SUCCESS.getMsg());
    }

    @Authorization
    @GetMapping("/sex")
    public Result getSexCount() {
        return Result.success(echartsService.getSexCount(), ResultCode.SUCCESS.getMsg());
    }

    @Authorization
    @GetMapping("/date")
    public Result getDateCount() {
        return Result.success(echartsService.getDateCount(), ResultCode.SUCCESS.getMsg());
    }
}
