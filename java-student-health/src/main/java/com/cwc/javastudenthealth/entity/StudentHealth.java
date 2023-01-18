package com.cwc.javastudenthealth.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentHealth implements Serializable {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer sid;
    // 肺活量
    private Integer vitalCapacity;
    // 50米
    private String shortRun;
    // 立位体前屈
    private String bodyFlexion;
    // 立定跳远
    private String standJump;
    // 引体向上/仰卧起坐
    private Integer up;
    // 1000/800米
    private String longRun;
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    @TableField(fill = FieldFill.UPDATE)
    private LocalDateTime updateTime;
}
