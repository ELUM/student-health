package com.cwc.javastudenthealth.service.imlp;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cwc.javastudenthealth.entity.StudentHealth;
import com.cwc.javastudenthealth.mapper.StudentHealthMapper;
import com.cwc.javastudenthealth.service.StudentHealthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Service
public class StudentHealthServiceImpl implements StudentHealthService {
    @Autowired
    StudentHealthMapper studentHealthMapper;

    @Override
    public int addStudentHealth(StudentHealth studentHealth) {
        return studentHealthMapper.insert(studentHealth);
    }

    @Override
    public Page<StudentHealth> getStudentHealths(int pageNum, int pageSize, StudentHealth studentHealth) {
        Page<StudentHealth> page = new Page<>(pageNum, pageSize);
        if (!ObjectUtils.isEmpty(studentHealth)) {
            return studentHealthMapper.selectPage(page, new QueryWrapper<StudentHealth>()
                    .like("sid", studentHealth.getSid())
            );
        }
        return studentHealthMapper.selectPage(page, null);
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
