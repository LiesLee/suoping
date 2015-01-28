package com.example.http.respose;

import com.example.entity.Exchange_detail;
import com.example.http.base.BaseResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LiesLee on 2015/1/9.
 * Email: LiesLee@foxmail.com
 */
public class ResponseExchangeDetail extends BaseResponse {
    List<Exchange_detail> data = new ArrayList<>();

    public List<Exchange_detail> getData() {
        return data;
    }

    public void setData(List<Exchange_detail> data) {
        this.data = data;
    }
}

