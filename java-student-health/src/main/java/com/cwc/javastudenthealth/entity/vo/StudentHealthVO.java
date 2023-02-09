package com.cwc.javastudenthealth.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentHealthVO {
    private Integer id;
    private String name;
    private Integer sex;
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
    private String height;
    private String weight;
    private String bmi;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
