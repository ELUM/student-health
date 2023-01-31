package com.cwc.javastudenthealth.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cwc.javastudenthealth.entity.Student;
import com.cwc.javastudenthealth.entity.vo.StudentVO;

public interface StudentService {
    /**
     * 添加学生
     * @param student
     * @return
     */
    int addStudent(Student student);

    /**
     * 获取学生
     * @param pageNum
     * @param pageSize
     * @param student
     * @return
     */
    IPage<StudentVO> getStudents(int pageNum, int pageSize, Student student);

    /**
     * 更新学生
     * @param student
     * @return
     */
    int updateStudent(Student student);

    /**
     * 删除学生
     * @param id
     * @return
     */
    int deleteStudent(Integer id);
}
