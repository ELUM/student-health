package com.cwc.javastudenthealth.controller;

import com.cwc.javastudenthealth.entity.Clazz;
import com.cwc.javastudenthealth.service.ClazzService;
import com.cwc.javastudenthealth.util.Result;
import com.cwc.javastudenthealth.util.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ClazzController {

    @Autowired
    ClazzService clazzService;

    /**
     * 添加班级
     *
     * @param clazz
     */
    @PutMapping("/clazz")
    public Result addClazz(@RequestBody Clazz clazz) {
        if (clazzService.addClazz(clazz) == 1) {
            return Result.success(null, ResultCode.SUCCESS.getMsg());
        }
        return Result.failed(null, ResultCode.FAILED.getMsg());
    }

    /**
     * 获取班级信息
     *
     * @param pageNum
     * @param pageSize
     * @param clazz
     * @return
     */
    @PostMapping("/clazz/{pageNum}/{pageSize}")
    public Result getClazz(@PathVariable int pageNum, @PathVariable int pageSize, @RequestBody(required = false) Clazz clazz) {
        return Result.success(clazzService.getClazzs(pageNum, pageSize, clazz), ResultCode.SUCCESS.getMsg());
    }

    /**
     * 更新班级信息
     *
     * @param clazz
     * @return
     */
    @PatchMapping("/clazz")
    public Result updateClazz(@RequestBody Clazz clazz) {
        if (clazzService.updateClazz(clazz) == 1) {
            return Result.success(null, ResultCode.SUCCESS.getMsg());
        }
        return Result.failed(null, ResultCode.FAILED.getMsg());
    }

    /**
     * 删除班级
     *
     * @param id
     * @return
     */
    @DeleteMapping("/clazz/{id}")
    public Result deleteClazz(@PathVariable Integer id) {
        if (clazzService.deleteClazz(id) == 1) {
            return Result.success(null, ResultCode.SUCCESS.getMsg());
        }
        return Result.failed(null, ResultCode.FAILED.getMsg());
    }
}
