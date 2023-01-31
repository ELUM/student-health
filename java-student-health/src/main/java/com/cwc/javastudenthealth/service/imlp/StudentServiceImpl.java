package com.cwc.javastudenthealth.service.imlp;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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
        if (!ObjectUtils.isEmpty(student)) {
            students = studentMapper.selectPage(page, new QueryWrapper<Student>().like("name", student.getName()));
        } else {
            students = studentMapper.selectPage(page, null);
        }
        // 组合
        IPage<StudentVO> convertPage = students.convert(result -> {
            StudentVO vo = new StudentVO();
            BeanUtils.copyProperties(result, vo);
            vo.setClazzName(clazzMapper.selectById(result.getCid()).getName());
            vo.setHealths(studentHealthMapper.selectList(new QueryWrapper<StudentHealth>()
                    .eq("sid", result.getId())
                    .orderByDesc("create_time")
                    .last("limit 0,10")));
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
        return studentMapper.deleteById(id);
    }
}
