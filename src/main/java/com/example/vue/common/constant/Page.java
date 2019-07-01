package com.example.vue.common.constant;

import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * @author: Tengfei Wang
 * @description:
 * @date: Created in 18:39 2019-06-13
 * @modified by:
 */
@Data
@ToString
public class Page<T> {
    private Integer pageIndex = 1;

    private Integer pageSize = 20;

    private Long total;

    private List<T> list;

    public Page() {
    }

    public Page(Long pageTotal, List<T> list) {
        this.total = pageTotal;
        this.list = list;
    }

    public Page(Integer pageIndex, Integer pageSize, Long total, List<T> list) {
        this.pageIndex = pageIndex;
        this.pageSize = pageSize;
        this.total = total;
        this.list = list;
    }
}
