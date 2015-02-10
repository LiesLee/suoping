package com.example.http.base;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.net.Uri;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request.Method;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.example.keyguard.R.id;
import com.example.util.LogUtil;
import com.example.util.PublicUtil;
import com.example.util.SharedPreferenceUtil;
import com.example.util.StringUtils;
import com.google.gson.Gson;
import com.lidroid.xutils.util.LogUtils;

public class BaseRequest<T> implements Listener<String>, ErrorListener {
	private final String TAG = BaseRequest.class.getSimpleName();
	private int method = Method.POST;
	private Class<T> responseClass;
	private ConnectorManage conManager;
	private HttpCallBack httpCallBack;
	private StringRequest request;
	private long flag;
	private Context mContext;

	public BaseRequest(Context context, int method, long flag, String url, String tag,
			ArrayList<NameValuePair> requestParam, Class<T> responseClass, HttpCallBack httpCallBack) {
		this.method = method;
		mContext = context;
		Map<String, String> tempMap = new HashMap<String, String>();
		for (int i = 0; i < requestParam.size(); i++) {
			if (requestParam.get(i).getValue() == null) {
				LogUtil.d(TAG, "HttpRequest:" + flag + "===>\n" + requestParam.get(i).getName() + "参数为空指针，请重新检查参数");
				return;
			} else {
				tempMap.put(requestParam.get(i).getName(), requestParam.get(i).getValue());
			}
		}
		init(context, flag, url, tag, tempMap, responseClass, httpCallBack);
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
			if (requestParam.size() < 1) {
				LogUtil.d(TAG, "PostHttpRequest:" + flag + "===>" + url + StringUtils.EMPTY);
			} else {
				LogUtil.d(TAG, "PostHttpRequest:" + flag + "===>" + url + requestParam.toString());
			}
			return new StringRequest(method, url, this, this) {

				@Override
				public Map<String, String> getHeaders() throws AuthFailureError {
					// TODO Auto-generated method stub
					return getRequestHeader();
				}

				@Override
				protected Map<String, String> getParams() throws AuthFailureError {
					// TODO Auto-generated method stub
					return requestParam;
				}

				@Override
				protected Response<String> parseNetworkResponse(NetworkResponse response) {
					// TODO Auto-generated method stub
					try {
						Map<String, String> responseHeaders = response.headers;
						String rawCookies = responseHeaders.get("Set-Cookie");
						PublicUtil.setCookies(mContext, rawCookies);
						String dataString = new String(response.data, "UTF-8");
						return Response.success(dataString, HttpHeaderParser.parseCacheHeaders(response));
					} catch (UnsupportedEncodingException e) {
						return Response.error(new ParseError(e));
					}
				}

			};
		case Method.GET:
			if (requestParam.size() < 1) {
				LogUtil.d(TAG, "GetHttpRequest:" + flag + "===>" + url + StringUtils.EMPTY);
			} else {
				LogUtil.d(TAG, "GetHttpRequest:" + flag + "===>" + url + requestParam.toString());
			}
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

				@Override
				protected Response<String> parseNetworkResponse(NetworkResponse response) {
					// TODO Auto-generated method stub
					try {
						Map<String, String> responseHeaders = response.headers;
						String rawCookies = responseHeaders.get("Set-Cookie");
						PublicUtil.setCookies(mContext, rawCookies);
						String dataString = new String(response.data, "UTF-8");
						return Response.success(dataString, HttpHeaderParser.parseCacheHeaders(response));
					} catch (UnsupportedEncodingException e) {
						return Response.error(new ParseError(e));
					}
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
		headParams.put("content-type", "application/json; charset=utf-8");
		headParams.put("charset", "UTF-8");
		headParams.put("Cookie", PublicUtil.getCookies(mContext));
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
				JSONObject json = new JSONObject(response);
				LogUtil.d(TAG, "onHttpSuccess:" + flag + json.toString());
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
