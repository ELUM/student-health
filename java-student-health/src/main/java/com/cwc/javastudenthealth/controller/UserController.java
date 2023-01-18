package com.cwc.javastudenthealth.controller;

import com.cwc.javastudenthealth.entity.User;
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
    @PutMapping("/user")
    public Result addUser(@RequestBody User user) {
        if (userService.addUser(user) == 1) {
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
    @PostMapping("/users/{pageNum}/{pageSize}")
    public Result getUsers(@PathVariable int pageNum, @PathVariable int pageSize, @RequestBody(required = false) User user) {
        return Result.success(userService.getUsers(pageNum, pageSize, user), ResultCode.SUCCESS.getMsg());
    }

    /**
     * 删除用户
     *
     * @param id
     */
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
    @PatchMapping("/user")
    public Result updateUser(@RequestBody User user) {
        if (userService.updateUser(user) == 1) {
            return Result.success(null, ResultCode.SUCCESS.getMsg());
        } else {
            return Result.failed(null, ResultCode.FAILED.getMsg());
        }
    }
}
