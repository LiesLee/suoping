package com.example.http;

import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.content.Context;

import com.example.entity.UserInfo;
import com.example.entity.respose.BaseResponse;
import com.example.entity.respose.ResponseEPDetail;
import com.example.entity.respose.ResponseEXProduct;
import com.example.entity.respose.ResponseUserInfo;

/**
 * @Description 接口类
 * @author Created by qinxianyuzou on 2014-12-24.
 */
public class Protocol {

	// /**
	// * @Description 获取用户物流信息
	// * @author Created by qinxianyuzou on 2014-12-24.
	// * @param mContext
	// * @param tag
	// * @return
	// */
	// public static long get_logistics_list(Context mContext, String tag) {
	// ArrayList<NameValuePair> requestParam = new ArrayList<NameValuePair>();
	// return ConnectorManage.getInstance(mContext).PostHttpRequest(mContext,
	// Config.GET_LOGISTICS_LIST, tag,
	// requestParam, Get_logistics_list_entity.class, null);
	// }

	/**
	 * @Description 获取用户详细信息
	 * @author Created by qinxianyuzou on 2014-12-29.
	 * @param mContext
	 * @param tag
	 * @return
	 */
	public static long get_user_info(Context mContext, String tag) {
		ArrayList<NameValuePair> requestParam = new ArrayList<NameValuePair>();
		return ConnectorManage.getInstance(mContext).GetHttpRequest(mContext, Config.GET_USER_INFO, tag, requestParam,
				ResponseUserInfo.class, null);
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
				BaseResponse.class, null);
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
				BaseResponse.class, null);
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
		return ConnectorManage.getInstance(context).PostHttpRequest(context, Config.LOGIN, tag, requestParam,
				ResponseUserInfo.class, null);
	}

	/**
	 * @Description 注销
	 * @author Created by qinxianyuzou on 2015-1-1.
	 * @param context
	 * @param tag
	 * @param httpCallBack
	 * @return
	 */
	public static long logout(Context context, String tag, HttpCallBack httpCallBack) {
		ArrayList<NameValuePair> requestParam = new ArrayList<NameValuePair>();
		return ConnectorManage.getInstance(context).GetHttpRequest(context, Config.LOGOUT, tag, requestParam,
				BaseResponse.class, httpCallBack);
	}

	/**
	 * @Description 兑换商品列表
	 * @author Created by qinxianyuzou on 2015-1-3.
	 * @param context
	 * @param tag
	 * @param ptype
	 * @return
	 */
	public static long get_ex_product(Context context, String tag, String ptype) {
		ArrayList<NameValuePair> requestParam = new ArrayList<NameValuePair>();
		requestParam.add(new BasicNameValuePair("ptype", ptype));
		return ConnectorManage.getInstance(context).GetHttpRequest(context, Config.GET_EX_PRODUCT, tag, requestParam,
				ResponseEXProduct.class, null);
	}

	/**
	 * @Description 详情接口
	 * @author Created by qinxianyuzou on 2015-1-3.
	 * @param context
	 * @param tag
	 * @param epid
	 * @return
	 */
	public static long get_ep_detail(Context context, String tag, String epid) {
		ArrayList<NameValuePair> requestParam = new ArrayList<NameValuePair>();
		requestParam.add(new BasicNameValuePair("epid", epid));
		return ConnectorManage.getInstance(context).GetHttpRequest(context, Config.GET_EP_DETAIL, tag, requestParam,
				ResponseEPDetail.class, null);
	}

	/**
	 * @Description 兑换接口
	 * @author Created by qinxianyuzou on 2015-1-4.
	 * @param context
	 * @param tag
	 * @param epid
	 * @return
	 */
	public static long exchange_product(Context context, String tag, String epid) {
		ArrayList<NameValuePair> requestParam = new ArrayList<NameValuePair>();
		requestParam.add(new BasicNameValuePair("epid", epid));
		return ConnectorManage.getInstance(context).GetHttpRequest(context, Config.EXCHANGE_PRODUCT, tag, requestParam,
				ResponseEPDetail.class, null);
	}
}
