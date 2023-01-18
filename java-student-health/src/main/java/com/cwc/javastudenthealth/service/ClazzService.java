package com.cwc.javastudenthealth.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cwc.javastudenthealth.entity.Clazz;

public interface ClazzService {
    /**
     * 添加班级
     * @param clazz
     * @return
     */
    int addClazz(Clazz clazz);

    /**
     * 获取班级
     * @param pageNum
     * @param pageSize
     * @param clazz
     * @return
     */
    Page<Clazz> getClazzs(int pageNum, int pageSize, Clazz clazz);

    /**
     * 更新班级
     * @param clazz
     * @return
     */
    int updateClazz(Clazz clazz);

    /**
     * 删除班级
     * @param id
     * @return
     */
    int deleteClazz(Integer id);
}
