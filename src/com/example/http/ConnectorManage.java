package com.example.http;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import org.json.JSONObject;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.VolleyError;

public class ConnectorManage implements HttpCallBack {

	private final String TAG = ConnectorManage.class.getSimpleName();
	private static ConnectorManage core;
	private AtomicLong httpCount = new AtomicLong(0);
	private Context context;
	@SuppressWarnings("rawtypes")
	private HashMap<Long, Request> mapHttp = new HashMap<Long, Request>();

    private List<HttpCallBack> fragmentCallBacks = new ArrayList<HttpCallBack>();

	/** 页面请求返回 */
	private HttpCallBack activityCallBack;

	public static ConnectorManage getInstance(Context context) {
		if (core == null) {
			core = new ConnectorManage();
		}
		core.context = context;
		return core;
	}

    // 添加一个回调
    public void addFragmentCallBack(HttpCallBack callBack) {
        if (!fragmentCallBacks.contains(callBack))
            fragmentCallBacks.add(callBack);
    }

    // 取消一个回调
    public void removeFragmentCallBack(HttpCallBack callBack) {
        if (fragmentCallBacks.contains(callBack))
            fragmentCallBacks.remove(callBack);
    }

	/**
	 * @Description
	 * @author Created by qinxianyuzou on 2014-12-24.
	 * @see #activityCallBack
	 * @return the activityCallBack
	 */
	public HttpCallBack getActivityCallBack() {
		return activityCallBack;
	}

	/**
	 * @Description
	 * @author Created by qinxianyuzou on 2014-12-24.
	 * @see #activityCallBack
	 * @param activityCallBack
	 *            the activityCallBack to set
	 */
	public void setActivityCallBack(HttpCallBack activityCallBack) {
		this.activityCallBack = activityCallBack;
	}

	/**
	 * @Description 
	 * @author Created by qinxianyuzou on 2014-12-24.
	 * @param context
	 * @param method
	 * @param url
	 * @param tag
	 * @param requestParam
	 * @param responseClass
	 * @param httpCallBack
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public <T> long sentHttpRequest(Context context, int method, String url, String tag,
			Map<String, String> requestParam, Class<T> responseClass, HttpCallBack httpCallBack) {
		final long flag = httpCount.incrementAndGet();
		BaseRequest request = new BaseRequest(context, method, flag, url, tag, requestParam, responseClass,
				httpCallBack);
		mapHttp.put(flag, request.getRequest());
		return flag;
	}

	@Override
	public <T> void onHttpSuccess(long flag, JSONObject jsonString, T response) {
		// TODO Auto-generated method stub
		if (mapHttp.containsKey(flag)) {
			mapHttp.remove(flag);
		}

		if (activityCallBack != null) {
			activityCallBack.onHttpSuccess(flag, jsonString, response);
		}
	}

	@Override
	public void onHttpError(long flag, VolleyError error) {
		// TODO Auto-generated method stub
		if (mapHttp.containsKey(flag)) {
			mapHttp.remove(flag);
		}
		if (activityCallBack != null) {
			activityCallBack.onHttpError(flag, error);
		}
	}
}
