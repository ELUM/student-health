package com.cwc.javastudenthealth.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cwc.javastudenthealth.entity.StudentHealth;

public interface StudentHealthService {
    int addStudentHealth(StudentHealth studentHealth);

    Page<StudentHealth> getStudentHealths(int pageNum, int pageSize, StudentHealth studentHealth);

    int updateStudentHealth(StudentHealth studentHealth);

    int deleteStudentHealth(Integer id);
}
