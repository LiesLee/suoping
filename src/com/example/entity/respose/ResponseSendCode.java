package com.example.entity.respose;

import com.example.util.StringUtils;

/**
 * Created by LiesLee on 2014/12/28.
 * Email: LiesLee@foxmail.com
 */
public class ResponseSendCode extends BaseResponse{
    /** 请求返回信息提示 **/
    String msg = StringUtils.EMPTY;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "ResponseSendCode{" +
                "msg='" + msg + '\'' +
                '}';
    }
}
