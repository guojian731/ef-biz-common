package com.eastfair.platform.common.vo;

import lombok.Data;

/**
 * @author guojian
 * @description 分页查询通用类
 * @date 2019-12-11 18:43
 **/
@Data
public class PageQuery<T> {

    private T query;

    private Long size;

    private Long current;

}
