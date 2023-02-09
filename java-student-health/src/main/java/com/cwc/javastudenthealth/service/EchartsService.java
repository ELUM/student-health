package com.cwc.javastudenthealth.service;

import com.cwc.javastudenthealth.entity.vo.DateCountVO;
import com.cwc.javastudenthealth.entity.vo.SexCountVO;

import java.util.List;

public interface EchartsService {
    Long getUserCount();

    Long getStudentCount();

    Long getClazzCount();

    Long getHealthCount();

    List<SexCountVO> getSexCount();

    List<DateCountVO> getDateCount();
}
