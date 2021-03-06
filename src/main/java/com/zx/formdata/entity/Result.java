package com.zx.formdata.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Result<T> implements Serializable {

    private static final long serialVersionUID = 6940343036324206794L;
    /**
     * 是否成功
     */
    private Boolean success;

    /**
     * 服务器当前时间戳
     */
    private Long timeStamp = System.currentTimeMillis()/1000;

    /**
     * 成功数据
     */
    private T data;
    /**
     * 错误码
     */
    private Integer code;

    /**
     * 错误描述
     */
    private String msg;
    /**
     * token
     */
    private String token;

    public static Result ofSuccess() {
        Result result = new Result();
        result.success = true;
        result.setCode(200);
        result.setMsg("success");
        return result;
    }

    public static Result ofSuccess(Object data) {
        Result result = new Result();
        result.success = true;
        result.setData(data);
        result.setCode(200);
        result.setMsg("success");
        return result;
    }
    public static Result ofSuccess(Object data,String token) {
        Result result = new Result();
        result.success = true;
        result.setData(data);
        result.setCode(200);
        result.setToken(token);
        result.setMsg("success");
        return result;
    }

    public static Result ofSuccess(String token){
        Result result = new Result();
        result.success = true;
        result.setToken(token);
        result.setCode(200);
        result.setMsg("success");
        return result;
    }

    public static Result ofFail(Integer code, String msg) {
        Result result = new Result();
        result.success = false;
        result.code = code;
        result.msg = msg;
        return result;
    }

    public static Result ofFail(Integer code, String msg, Object data) {
        Result result = new Result();
        result.success = false;
        result.code = code;
        result.msg = msg;
        result.setData(data);
        return result;
    }



}
