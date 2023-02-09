package com.cwc.javastudenthealth.service.imlp;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cwc.javastudenthealth.entity.StudentHealth;
import com.cwc.javastudenthealth.entity.vo.StudentHealthVO;
import com.cwc.javastudenthealth.mapper.StudentHealthMapper;
import com.cwc.javastudenthealth.service.StudentHealthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentHealthServiceImpl implements StudentHealthService {
    @Autowired
    StudentHealthMapper studentHealthMapper;

    @Override
    public int addStudentHealth(StudentHealth studentHealth) {
        return studentHealthMapper.insert(studentHealth);
    }

    @Override
    public IPage<StudentHealthVO> getStudentHealths(int pageNum, int pageSize, String name) {
        Page<StudentHealth> studentHealthPage = new Page<>(pageNum, pageSize);
        return studentHealthMapper.getStudentHealth(studentHealthPage,name);
    }

    @Override
    public int updateStudentHealth(StudentHealth studentHealth) {
        return studentHealthMapper.updateById(studentHealth);
    }

    @Override
    public int deleteStudentHealth(Integer id) {
        return studentHealthMapper.deleteById(id);
    }
}
