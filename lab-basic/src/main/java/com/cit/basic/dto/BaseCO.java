package com.cit.basic.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @Description: 基础对象
 * @Author: Richard
 * @CreateDate: 2023/9/11 23:02
 * @UpdateUser: Richard
 * @UpdateDate: 2023/9/11 23:02
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
@Data
public class BaseCO implements Serializable {
    private static final long serialVersionUID = -1795513974091592874L;
    /**
     * id
     */
    private Long id;
    /**
     * 创建时间
     */
    private Long inputTime;
    /**
     * 更新时间
     */
    private Long updateTime;
    /**
     * 删除标志位 0：删除， 1：在使用
     */
    private Integer delFlag;
}
