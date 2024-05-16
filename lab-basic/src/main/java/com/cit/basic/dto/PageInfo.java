package com.cit.basic.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: PageInfo
 * @Author: Richard
 * @CreateDate: 2024/5/11 21:56
 * @UpdateAuthor: Richard
 * @UpdateDate: 2024/5/11 21:56
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
@Data
public class PageInfo<T> {
    private List<T> list;

    private Long total;

    public PageInfo() {
    }

    public PageInfo(List<T> list, Long total) {
        this.list = list;
        this.total = total;
    }

    public PageInfo(Long total) {
        this.list = new ArrayList<>();
        this.total = total;
    }

    public static <T> PageInfo<T> empty() {
        return new PageInfo<>(0L);
    }

    public static <T> PageInfo<T> empty(Long total) {
        return new PageInfo<>(total);
    }
}
