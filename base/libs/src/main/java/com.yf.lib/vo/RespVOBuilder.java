package com.yf.lib.vo;

import com.yf.lib.consts.RespConsts;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;

public class RespVOBuilder {

    /**
     * 是否成功
     * @param respVO
     * @return
     */
    public static boolean isSuccess(RespVO respVO) {
        if(respVO == null || !RespConsts.SUCCESS.equals(respVO.getCode())) {
            return false;
        }
        return true;
    }

    /**
     * 成功
     *
     * @param <T>
     * @return
     */
    public static <T> RespVO<T> success() {
        return new RespVO<T>(RespConsts.SUCCESS, "", (T) new HashMap(0));
    }

    /**
     * 成功
     *
     * @param data
     * @param <T> POJO
     * @return
     */
    public static <T> RespVO<T> success(T data) {
        return new RespVO<T>(RespConsts.SUCCESS, "", data);
    }

    /**
     * 成功
     * @param data
     * @param <T> List
     * @return
     */
    public static <T> RespVO<RespDataVO<T>> success(List<T> data) {
        return new RespVO<RespDataVO<T>>(RespConsts.SUCCESS, "", new RespDataVO<T>(data));
    }

    /**
     * 失败
     *
     * @param <T>
     * @return
     */
    public static <T> RespVO<T> failure() {
        return failure(RespConsts.Failure.class);
    }

    /**
     * 失败
     *
     * @param msg
     * @param <T>
     * @return
     */
    public static <T> RespVO<T> failure(String msg) {
        return new RespVO<T>(RespConsts.FAILURE, msg, (T) new HashMap(0));
    }

    /**
     * 失败
     *
     * @param code
     * @param msg
     * @param <T>
     * @return
     */
    public static <T> RespVO<T> failure(String code, String msg) {
        return new RespVO<T>(code, msg, (T) new HashMap<>(0));
    }

    /**
     * 失败并返回数据
     * @param msg
     * @param data
     * @param <T>
     * @return
     */
    public static <T> RespVO<T> failureData(String msg, T data) {
        return new RespVO<T>(RespConsts.FAILURE, msg, data);
    }

    /**
     * 失败
     *
     * @param clazz *Consts中定义的接口
     * @param <T>
     * @return
     */
    public static <T> RespVO<T> failure(Class clazz) {
        return failure(clazz, (T) new HashMap<>(0));
    }

    /**
     * 失败
     *
     * @param clazz *Consts中定义的接口
     * @param data  需要返回的业务数据
     * @param <T>
     * @return
     */
    public static <T> RespVO<T> failure(Class clazz, T data) {
        String code = null;
        String msg = null;
        Field[] fields = clazz.getDeclaredFields();
        try {
            for (Field field : fields) {
                if (field.getName().equals("code")) {
                    code = String.valueOf(field.get(clazz));
                }
                if (field.getName().equals("msg")) {
                    msg = String.valueOf(field.get(clazz));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new RespVO<T>(code, msg, data);
    }
}
