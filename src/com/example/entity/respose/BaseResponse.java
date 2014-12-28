package com.example.entity.respose;

import com.example.util.StringUtils;

import java.io.Serializable;

/**
 * Created by LiesLee on 2014/12/28.
 * Email: LiesLee@foxmail.com
 */
public class BaseResponse implements Serializable {
    /** 状态编码 **/
    String code = StringUtils.EMPTY;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "BaseResponse{" +
                "code='" + code + '\'' +
                '}';
    }
}
