package com.cwc.javastudenthealth.controller;

import com.cwc.javastudenthealth.entity.Student;
import com.cwc.javastudenthealth.entity.StudentHealth;
import com.cwc.javastudenthealth.handler.Authorization;
import com.cwc.javastudenthealth.service.StudentHealthService;
import com.cwc.javastudenthealth.util.Result;
import com.cwc.javastudenthealth.util.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class StudentHealthController {
    @Autowired
    StudentHealthService studentHealthService;

    @Authorization
    @PutMapping("/studenthealth")
    public Result addStudentHealthService(@RequestBody StudentHealth studentHealth) {
        System.out.println(studentHealth);
        if (studentHealthService.addStudentHealth(studentHealth) == 1) {
            return Result.success(null, ResultCode.SUCCESS.getMsg());
        }
        return Result.failed(null, ResultCode.FAILED.getMsg());
    }

    @Authorization
    @PostMapping("/studenthealth/{pageNum}/{pageSize}")
    public Result getStudentHealth(@PathVariable int pageNum, @PathVariable int pageSize, @RequestBody(required = false) Student student) {
        if (student != null) {
            return Result.success(studentHealthService.getStudentHealths(pageNum, pageSize, student.getName()), ResultCode.SUCCESS.getMsg());
        }
        return Result.success(studentHealthService.getStudentHealths(pageNum, pageSize, ""), ResultCode.SUCCESS.getMsg());
    }

    @Authorization
    @PatchMapping("/studenthealth")
    public Result updateStudentHealth(@RequestBody StudentHealth studentHealth) {
        if (studentHealthService.updateStudentHealth(studentHealth) == 1) {
            return Result.success(null, ResultCode.SUCCESS.getMsg());
        }
        return Result.failed(null, ResultCode.FAILED.getMsg());
    }

    @Authorization
    @DeleteMapping("/studenthealth/{id}")
    public Result deleteStudentHealth(@PathVariable Integer id) {
        if (studentHealthService.deleteStudentHealth(id) == 1) {
            return Result.success(null, ResultCode.SUCCESS.getMsg());
        }
        return Result.failed(null, ResultCode.FAILED.getMsg());
    }
}
