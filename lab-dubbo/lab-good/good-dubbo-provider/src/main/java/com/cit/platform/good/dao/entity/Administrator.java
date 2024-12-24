package com.cit.platform.good.dao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @Author: Richard
 * @Description: 仓库管理员
 * @CreateDate: 2024/12/24 19:56
 * @UpdateUser: zhouli
 * @UpdateDate: 2024/12/24 19:56
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
@Data
@TableName(value = "administrator")
public class Administrator {
    @TableId(type= IdType.AUTO)
    private Long id;

    private String name;
}
