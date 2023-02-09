package com.cwc.javastudenthealth.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cwc.javastudenthealth.entity.StudentHealth;
import com.cwc.javastudenthealth.entity.vo.StudentHealthVO;

public interface StudentHealthService {
    int addStudentHealth(StudentHealth studentHealth);

    IPage<StudentHealthVO> getStudentHealths(int pageNum, int pageSize, String name);

    int updateStudentHealth(StudentHealth studentHealth);

    int deleteStudentHealth(Integer id);
}
