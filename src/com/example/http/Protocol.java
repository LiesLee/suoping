package com.example.http;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.android.volley.Request;
import com.example.activity.reg.Activity_Reg;
import com.example.entity.Get_logistics_list_entity;
import com.example.entity.respose.ResponseSendCode;
import com.example.entity.respose.ResponseUserInfo;

import android.content.Context;

/**
 * @Description 接口类
 * @author Created by qinxianyuzou on 2014-12-24.
 */
public class Protocol {

	/**
	 * @Description 获取用户物流信息
	 * @author Created by qinxianyuzou on 2014-12-24.
	 * @param mContext
	 * @param tag
	 * @return
	 */
	public static long get_logistics_list(Context mContext, String tag) {
		ArrayList<NameValuePair> requestParam = new ArrayList<NameValuePair>();
		return ConnectorManage.getInstance(mContext).PostHttpRequest(mContext, Config.GET_LOGISTICS_LIST, tag,
				requestParam, Get_logistics_list_entity.class, null);
	}

	/**
	 * 获取验证码
	 * 
	 * @param context
	 * @param send_code
	 * @param tag
	 * @return
	 */
	public static long MsgAuthentication(Context context, String send_code, String tag) {
		ArrayList<NameValuePair> requestParam = new ArrayList<NameValuePair>();
		requestParam.add(new BasicNameValuePair("username", send_code));
		return ConnectorManage.getInstance(context).PostHttpRequest(context, Config.SEND_CODE, tag, requestParam,
				ResponseSendCode.class, null);
	}

	/**
	 * 注册
	 * 
	 * @param context
	 * @param tag
	 * @param cellphoneNumber
	 * @param code
	 * @param password
	 * @return
	 */
	public static long register(Context context, String tag, String cellphoneNumber, String code, String password) {
		ArrayList<NameValuePair> requestParam = new ArrayList<NameValuePair>();
		requestParam.add(new BasicNameValuePair("username", cellphoneNumber));
		requestParam.add(new BasicNameValuePair("password", password));
		requestParam.add(new BasicNameValuePair("auth_code", code));
		return ConnectorManage.getInstance(context).PostHttpRequest(context, Config.REGISTER, tag, requestParam,
				ResponseSendCode.class, null);
	}

	/**
	 * 登录
	 * 
	 * @param context
	 * @param tag
	 * @param cellphoneNumber
	 * @param password
	 * @return
	 */
	public static long login(Context context, String tag, String cellphoneNumber, String password) {
		ArrayList<NameValuePair> requestParam = new ArrayList<NameValuePair>();
		requestParam.add(new BasicNameValuePair("username", cellphoneNumber));
		requestParam.add(new BasicNameValuePair("password", password));
		return ConnectorManage.getInstance(context).PostHttpRequest(context, Config.REGISTER, tag, requestParam,
				ResponseUserInfo.class, null);
	}
}
