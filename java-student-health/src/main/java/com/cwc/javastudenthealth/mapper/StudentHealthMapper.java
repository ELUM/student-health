package com.cwc.javastudenthealth.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cwc.javastudenthealth.entity.StudentHealth;
import com.cwc.javastudenthealth.entity.vo.StudentHealthVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface StudentHealthMapper extends BaseMapper<StudentHealth> {

    @Select("SELECT sh.*,s.* FROM student s left join student_health sh on sh.sid = s.id WHERE s.name like CONCAT('%',#{name},'%') AND sh.id is not null")
    IPage<StudentHealthVO> getStudentHealth(Page page, @Param("name") String name);

}
