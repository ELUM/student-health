package com.cwc.javastudenthealth.service.imlp;

import com.cwc.javastudenthealth.entity.vo.DateCountVO;
import com.cwc.javastudenthealth.entity.vo.SexCountVO;
import com.cwc.javastudenthealth.mapper.*;
import com.cwc.javastudenthealth.service.EchartsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EchartsServiceImpl implements EchartsService {

    @Autowired
    StudentMapper studentMapper;

    @Autowired
    StudentHealthMapper studentHealthMapper;

    @Autowired
    UserMapper userMapper;

    @Autowired
    ClazzMapper clazzMapper;

    @Autowired
    EchartsMapper echartsMapper;

    @Override
    public Long getUserCount() {
        return userMapper.selectCount(null);
    }

    @Override
    public Long getStudentCount() {
        return studentMapper.selectCount(null);
    }

    @Override
    public Long getClazzCount() {
        return clazzMapper.selectCount(null);
    }

    @Override
    public Long getHealthCount() {
        return studentHealthMapper.selectCount(null);
    }

    @Override
    public List<SexCountVO> getSexCount() {
        return echartsMapper.getSexCount();
    }

    @Override
    public List<DateCountVO> getDateCount() {
        return echartsMapper.getDateCount();
    }
}
