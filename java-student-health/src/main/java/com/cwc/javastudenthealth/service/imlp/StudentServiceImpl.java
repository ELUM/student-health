package com.cwc.javastudenthealth.service.imlp;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cwc.javastudenthealth.entity.Student;
import com.cwc.javastudenthealth.mapper.StudentMapper;
import com.cwc.javastudenthealth.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    StudentMapper studentMapper;

    @Override
    public int addStudent(Student student) {
        return studentMapper.insert(student);
    }

    @Override
    public Page<Student> getStudents(int pageNum, int pageSize, Student student) {
        Page<Student> page = new Page<>(pageNum, pageSize);
        if (!ObjectUtils.isEmpty(student)) {
            return studentMapper.selectPage(page, new QueryWrapper<Student>().like("name", student.getName()));
        }
        return studentMapper.selectPage(page, null);
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
