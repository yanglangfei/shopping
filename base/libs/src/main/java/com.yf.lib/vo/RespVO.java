package com.yf.lib.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RespVO<T> implements Serializable{
    private String code;
    private String msg;
    private T data;
}
