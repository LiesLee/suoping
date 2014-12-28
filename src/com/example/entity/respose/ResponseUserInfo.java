package com.example.entity.respose;

import com.example.entity.UserInfo;

/**
 * Created by LiesLee on 2014/12/28.
 * Email: LiesLee@foxmail.com
 */
public class ResponseUserInfo extends BaseResponse {
    /** 用户信息 **/
    private UserInfo msg;

    public UserInfo getMsg() {
        return msg;
    }

    public void setMsg(UserInfo msg) {
        this.msg = msg;
    }
}
