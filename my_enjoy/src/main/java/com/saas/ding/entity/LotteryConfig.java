package com.saas.ding.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data@NoArgsConstructor@AllArgsConstructor
public class LotteryConfig {
    /**
     * ID
     */
    private String id;
    /**
     * 彩种 彩票名称
     */
    private String name;

    /**
     * 前区范围
     */
    private Integer frontSectionMax;
    /**
     * 前区元素是否可以重复
     */
    private Integer frontSectionRepeatable;
    /**
     * 前区元素个数
     */
    private Integer frontSectionNumber;


    /**
     * 后区范围
     */
    private Integer backAreaMax;
    /**
     * 后区元素是否已经重复
     */
    private Integer backAreaRepeatable;
    /**
     * 后区元素个数
     */
    private Integer backAreaNumber;


}
