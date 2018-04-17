package com.yf.lib.mybatis;


import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 定义数据源类型
 */
@AllArgsConstructor
@Getter
public enum DataSourceType {
    read("read", "从库"), write("write", "主库");

    private String type;
    private String name;
}
