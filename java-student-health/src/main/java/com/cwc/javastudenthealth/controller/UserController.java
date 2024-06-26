package com.cwc.javastudenthealth.controller;

import com.cwc.javastudenthealth.entity.User;
import com.cwc.javastudenthealth.handler.Authorization;
import com.cwc.javastudenthealth.service.UserService;
import com.cwc.javastudenthealth.util.Result;
import com.cwc.javastudenthealth.util.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    UserService userService;

    /**
     * 添加用户
     *
     * @param user
     */
    @Authorization
    @PutMapping("/user")
    public Result addUser(@RequestBody User user) {
        int i = userService.addUser(user);
        if (i == 2) {
            return Result.base(ResultCode.ACCOUNT_EXIST.getCode(), ResultCode.ACCOUNT_EXIST.getMsg());
        }
        if (i == 1) {
            return Result.success(null, ResultCode.SUCCESS.getMsg());
        } else {
            return Result.failed(null, ResultCode.PARAMS_ERROR.getMsg());
        }
    }

    /**
     * 查询所有用户
     *
     * @param pageNum
     * @param pageSize
     */
    @Authorization
    @PostMapping("/users/{pageNum}/{pageSize}")
    public Result getUsers(@PathVariable int pageNum, @PathVariable int pageSize, @RequestBody(required = false) User user) {
        return Result.success(userService.getUsers(pageNum, pageSize, user), ResultCode.SUCCESS.getMsg());
    }

    /**
     * 删除用户
     *
     * @param id
     */
    @Authorization
    @DeleteMapping("/user/{id}")
    public Result deleteUserById(@PathVariable Integer id) {
        if (userService.deleteUserById(id) == 1) {
            return Result.success(null, ResultCode.SUCCESS.getMsg());
        } else {
            return Result.failed(null, ResultCode.FAILED.getMsg());
        }
    }

    /**
     * 更新用户
     *
     * @param user
     */
    @Authorization
    @PatchMapping("/user")
    public Result updateUser(@RequestBody User user) {
        int i = userService.updateUser(user);
        if (i == 2) {
            return Result.base(ResultCode.ACCOUNT_EXIST.getCode(), ResultCode.ACCOUNT_EXIST.getMsg());
        }
        if (i == 1) {
            return Result.success(null, ResultCode.SUCCESS.getMsg());
        } else {
            return Result.failed(null, ResultCode.FAILED.getMsg());
        }
    }
}
