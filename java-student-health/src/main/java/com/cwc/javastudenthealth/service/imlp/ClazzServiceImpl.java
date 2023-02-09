package com.cwc.javastudenthealth.service.imlp;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cwc.javastudenthealth.entity.Clazz;
import com.cwc.javastudenthealth.mapper.ClazzMapper;
import com.cwc.javastudenthealth.service.ClazzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Service
public class ClazzServiceImpl implements ClazzService {

    @Autowired
    ClazzMapper clazzMapper;

    @Override
    public int addClazz(Clazz clazz) {
        return clazzMapper.insert(clazz);
    }

    @Override
    public Page<Clazz> getClazzs(int pageNum, int pageSize, Clazz clazz) {
        Page<Clazz> clazzPage = new Page<>(pageNum, pageSize);
        if (!ObjectUtils.isEmpty(clazz)) {
            return clazzMapper.selectPage(clazzPage, new QueryWrapper<Clazz>()
                    .like("name", clazz.getName())
            );
        }
        return clazzMapper.selectPage(clazzPage, null);
    }

    @Override
    public int updateClazz(Clazz clazz) {
        return clazzMapper.updateById(clazz);
    }

    @Override
    public int deleteClazz(Integer id) {
        return clazzMapper.deleteById(id);
    }

    @Override
    public List<Clazz> getClazzByName(String name) {
        return clazzMapper.selectList(new QueryWrapper<Clazz>().like("name",name));
    }


}
