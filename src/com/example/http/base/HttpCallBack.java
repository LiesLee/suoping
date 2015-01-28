package com.example.http.base;

import org.json.JSONObject;

import com.android.volley.VolleyError;

/**
 * @Description 请求回调
 * @author Created by qinxianyuzou on 2014-12-23.
 */
public interface HttpCallBack {
	<T> void onHttpSuccess(long flag, JSONObject jsonString, T response);

	void onHttpError(long flag, VolleyError error);
}
