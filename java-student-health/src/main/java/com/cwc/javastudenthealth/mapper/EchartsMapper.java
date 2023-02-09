package com.cwc.javastudenthealth.mapper;

import com.cwc.javastudenthealth.entity.vo.DateCountVO;
import com.cwc.javastudenthealth.entity.vo.SexCountVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface EchartsMapper {

    @Select("select DATE_FORMAT(create_time,'%Y-%m') date,count(id) count from student_health GROUP BY DATE_FORMAT( create_time,'%Y-%m') ORDER BY date DESC limit 12")
    List<DateCountVO> getDateCount();

    @Select("select sex as sex, count(*) as count from student GROUP BY sex")
    List<SexCountVO> getSexCount();
}
