package com.example.http;

import java.util.HashMap;
import java.util.Map;

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
		Map<String, String> requestParam = new HashMap<String, String>();
		return ConnectorManage.getInstance(mContext).sentHttpRequest(mContext, 0, Config.GET_LOGISTICS_LIST, tag,
				requestParam, Get_logistics_list_entity.class, null);
	}

    /**
     * 获取验证码
     * @param context
     * @param send_code
     * @param tag
     * @return
     */
    public static long MsgAuthentication(Context context, String send_code, String tag){
        Map<String, String> params = new HashMap<String, String>();
        params.put("username", send_code);
        return ConnectorManage.getInstance(context).sentHttpRequest(context, Request.Method.POST, Config.SEND_CODE, tag, params, ResponseSendCode.class, null);
    }

    /**
     * 注册
     * @param context
     * @param tag
     * @param cellphoneNumber
     * @param code
     * @param password
     * @return
     */
    public static long register(Context context, String tag, String cellphoneNumber, String code, String password){
        Map<String, String> params = new HashMap<String, String>();
        params.put("username", cellphoneNumber);
        params.put("password", password);
        params.put("auth_code", code);
        return ConnectorManage.getInstance(context).sentHttpRequest(context, Request.Method.POST, Config.REGISTER, tag, params,  ResponseSendCode.class, null);
    }

    /**
     * 登录
     * @param context
     * @param tag
     * @param cellphoneNumber
     * @param password
     * @return
     */
    public static long login(Context context, String tag, String cellphoneNumber, String password){
        Map<String, String> params = new HashMap<String, String>();
        params.put("username", cellphoneNumber);
        params.put("password", password);
        return ConnectorManage.getInstance(context).sentHttpRequest(context, Request.Method.POST, Config.REGISTER, tag, params,  ResponseUserInfo.class, null);
    }
}
