package com.cwc.javastudenthealth.entity.vo;

import com.cwc.javastudenthealth.entity.StudentHealth;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentVO {
    private Integer id;
    private Integer cid;
    private String clazzName;
    private String name;
    private LocalDateTime birth;
    private Integer sex;
    private List<StudentHealth> healths;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
