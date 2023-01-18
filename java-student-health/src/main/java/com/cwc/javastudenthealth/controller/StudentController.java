package com.cwc.javastudenthealth.controller;

import com.cwc.javastudenthealth.entity.Student;
import com.cwc.javastudenthealth.service.StudentService;
import com.cwc.javastudenthealth.util.Result;
import com.cwc.javastudenthealth.util.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class StudentController {
    @Autowired
    StudentService studentService;

    /**
     * 添加学生
     *
     * @param student
     * @return
     */
    @PutMapping("/student")
    public Result addStudent(@RequestBody Student student) {
        if (studentService.addStudent(student) == 1) {
            return Result.success(null, ResultCode.SUCCESS.getMsg());
        }
        return Result.failed(null, ResultCode.FAILED.getMsg());
    }

    /**
     * 获取学生
     *
     * @param pageNum
     * @param pageSize
     * @param student
     * @return
     */
    @PostMapping("/student/{pageNum}/{pageSize}")
    public Result getStudents(@PathVariable int pageNum, @PathVariable int pageSize, @RequestBody(required = false) Student student) {
        return Result.success(studentService.getStudents(pageNum, pageSize, student), ResultCode.SUCCESS.getMsg());
    }

    /**
     * 更新学生
     *
     * @param student
     * @return
     */
    @PatchMapping("/student")
    public Result updateStudent(@RequestBody Student student) {
        if (studentService.updateStudent(student) == 1) {
            return Result.success(null, ResultCode.SUCCESS.getMsg());
        }
        return Result.failed(null, ResultCode.FAILED.getMsg());
    }

    /**
     * 删除学生
     *
     * @param id
     * @return
     */
    @DeleteMapping("/student/{id}")
    public Result deleteStudent(@PathVariable Integer id) {
        if (studentService.deleteStudent(id) == 1) {
            return Result.success(null, ResultCode.SUCCESS.getMsg());
        }
        return Result.failed(null, ResultCode.FAILED.getMsg());
    }

}
