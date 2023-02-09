package com.cwc.javastudenthealth.service.imlp;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cwc.javastudenthealth.entity.Clazz;
import com.cwc.javastudenthealth.entity.Student;
import com.cwc.javastudenthealth.entity.StudentHealth;
import com.cwc.javastudenthealth.entity.vo.StudentVO;
import com.cwc.javastudenthealth.mapper.ClazzMapper;
import com.cwc.javastudenthealth.mapper.StudentHealthMapper;
import com.cwc.javastudenthealth.mapper.StudentMapper;
import com.cwc.javastudenthealth.service.StudentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Collections;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    StudentMapper studentMapper;
    @Autowired
    StudentHealthMapper studentHealthMapper;

    @Autowired
    ClazzMapper clazzMapper;

    @Override
    public int addStudent(Student student) {
        return studentMapper.insert(student);
    }

    @Override
    public IPage<StudentVO> getStudents(int pageNum, int pageSize, Student student) {
        Page<Student> page = new Page<>(pageNum, pageSize);
        Page<Student> students = null;
        QueryWrapper<Student> studentQueryWrapper = new QueryWrapper<>();
        if (!ObjectUtils.isEmpty(student)) {
            if (!ObjectUtils.isEmpty(student.getName())) {
                studentQueryWrapper.like("name", student.getName());
            }
            if (!ObjectUtils.isEmpty(student.getCid())) {
                studentQueryWrapper.eq("cid", student.getCid());
            }
            students = studentMapper.selectPage(page, studentQueryWrapper);
        } else {
            students = studentMapper.selectPage(page, null);
        }

        // 组合
        IPage<StudentVO> convertPage = students.convert(result -> {
            StudentVO vo = new StudentVO();
            BeanUtils.copyProperties(result, vo);
            Clazz clazz = clazzMapper.selectById(result.getCid());
            if (ObjectUtils.isEmpty(clazz)) {
                vo.setClazzName("无分配班级");
            } else {
                String name = clazz.getName();
                vo.setClazzName(name);
            }
            vo.setHealths(studentHealthMapper.selectList(new QueryWrapper<StudentHealth>()
                    .eq("sid", result.getId())
                    .orderByDesc("create_time")
                    .last("limit 10"))
            );
            Collections.reverse(vo.getHealths());
            return vo;
        });
        return convertPage;
    }

    @Override
    public int updateStudent(Student student) {
        return studentMapper.updateById(student);
    }

    @Override
    public int deleteStudent(Integer id) {
        if (studentHealthMapper.selectList(new QueryWrapper<StudentHealth>().eq("sid", id)).size() == 0) {
            return studentMapper.deleteById(id);
        }
        if (studentMapper.deleteById(id) == 1 && studentHealthMapper.delete(new QueryWrapper<StudentHealth>().eq("sid", id)) == 1) {
            return 1;
        }
        return 0;
    }
}
