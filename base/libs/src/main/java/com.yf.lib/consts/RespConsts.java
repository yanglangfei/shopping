package com.yf.lib.consts;

public class RespConsts {

    /**
     * 失败
     */
    public static final String FAILURE = "0";

    /**
     * 成功
     */
    public static final String SUCCESS = "1";

    /**
     * 失败
     */
    public interface Failure {
        String code = FAILURE;
        String msg = "系统异常";
    }

    /**
     * 成功
     */
    public interface Success {
        String code = SUCCESS;
        String msg = "请求成功";
    }

}
