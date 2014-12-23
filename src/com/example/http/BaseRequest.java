package com.example.http;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.net.Uri;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request.Method;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.istudy.utils.LogUtil;
import com.istudy.utils.StringUtils;

public class BaseRequest<T> implements Listener<String>, ErrorListener {
	private final String TAG = BaseRequest.class.getSimpleName();
	private int method = Method.POST;
	private Class<T> responseClass;
	private ConnectorManage conManager;
	private HttpCallBack httpCallBack;
	private StringRequest request;
	private long flag;

	public BaseRequest(Context context, int method, long flag, String url, String tag,
			Map<String, String> requestParam, Class<T> responseClass, HttpCallBack httpCallBack) {
		this.method = method;
		init(context, flag, url, tag, requestParam, responseClass, httpCallBack);
	}

	private void init(Context context, long flag, String url, String tag, Map<String, String> requestParam,
			Class<T> responseClass, HttpCallBack httpCallBack) {
		this.responseClass = responseClass;
		this.httpCallBack = httpCallBack;
		this.flag = flag;
		conManager = ConnectorManage.getInstance(context);
		request = getRequest(url, requestParam);
		request.setRetryPolicy(new DefaultRetryPolicy());
		request.setTag(tag);
		RequestManager.getInstance(context).getmRequestQueue().add(request);
	}

	/**
	 * @Description 获取请求体
	 * @author Created by qinxianyuzou on 2014-12-24.
	 * @see #request
	 * @return the request
	 */
	public StringRequest getRequest() {
		return request;
	}

	/**
	 * @Description 发起请求
	 * @author Created by qinxianyuzou on 2014-12-24.
	 * @param url
	 * @param requestParam
	 * @return
	 */
	private StringRequest getRequest(String url, final Map<String, String> requestParam) {
		switch (method) {
		case Method.POST:
		default:
			return new StringRequest(method, url, this, this) {

				@Override
				public Map<String, String> getHeaders() throws AuthFailureError {
					return getRequestHeader();
				}

				@Override
				protected Map<String, String> getParams() throws com.android.volley.AuthFailureError {
					return requestParam;
				};

			};
		case Method.GET:
			Uri.Builder uriBuilder = Uri.parse(url).buildUpon();
			if (requestParam != null) {
				for (String key : requestParam.keySet()) {
					uriBuilder.appendQueryParameter(key, requestParam.get(key));
				}
			}
			return new StringRequest(uriBuilder.toString(), this, this) {
				@Override
				public Map<String, String> getHeaders() throws AuthFailureError {
					return getRequestHeader();
				}
			};
		}
	}

	/**
	 * @Description 头部放置的信息
	 * @author Created by qinxianyuzou on 2014-12-24.
	 * @return
	 */
	protected Map<String, String> getRequestHeader() {
		Map<String, String> headParams = new HashMap<String, String>();
		headParams.put("content-type", "application/json");
		headParams.put("charset", "UTF-8");
		return headParams;
	}

	@Override
	public void onErrorResponse(VolleyError error) {
		// TODO Auto-generated method stub
		conManager.onHttpError(flag, error);
		if (httpCallBack != null)
			httpCallBack.onHttpError(flag, error);
	}

	@Override
	public void onResponse(String response) {
		// TODO Auto-generated method stub
		try {
			if (!StringUtils.isEmpty(response)) {
				LogUtil.d(TAG, "onHttpSuccess:" + flag + response.toString());
				JSONObject json = new JSONObject(response);
				T responseEntity = new Gson().fromJson(response, responseClass);
				conManager.onHttpSuccess(flag, json, responseEntity);
				if (httpCallBack != null) {
					httpCallBack.onHttpSuccess(flag, json, responseEntity);
				}
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

}
