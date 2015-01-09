package com.example.http;

import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.content.Context;

import com.example.entity.respose.BaseResponse;
import com.example.entity.respose.ResponseDownAPP;
import com.example.entity.respose.ResponseEPDetail;
import com.example.entity.respose.ResponseEXProduct;
import com.example.entity.respose.ResponseInviteDetail;
import com.example.entity.respose.ResponseLockADList;
import com.example.entity.respose.ResponseLogistics;
import com.example.entity.respose.ResponseUpdate;
import com.example.entity.respose.ResponseUserInfo;
import com.example.util.StringUtils;

/**
 * @Description 接口类
 * @author Created by qinxianyuzou on 2014-12-24.
 */
public class Protocol {

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

	/**
	 * @Description 获取物流信息列表
	 * @author Created by qinxianyuzou on 2015-1-4.
	 * @param context
	 * @param tag
	 * @return
	 */
	public static long get_logistics_list(Context context, String tag) {
		ArrayList<NameValuePair> requestParam = new ArrayList<NameValuePair>();
		return ConnectorManage.getInstance(context).GetHttpRequest(context, Config.GET_LOGISTICS_LIST, tag,
				requestParam, ResponseLogistics.class, null);
	}

	/**
	 * @Description 更新实体
	 * @author Created by qinxianyuzou on 2015-1-5.
	 * @param context
	 * @param tag
	 * @return
	 */
	public static long check_update(Context context, String tag) {
		ArrayList<NameValuePair> requestParam = new ArrayList<NameValuePair>();
		return ConnectorManage.getInstance(context).GetHttpRequest(context, Config.CHECK_UPDATE, tag, requestParam,
				ResponseUpdate.class, null);
	}

	/**
	 * @Description 提交邀请码
	 * @author Created by qinxianyuzou on 2015-1-4.
	 * @param context
	 * @param tag
	 * @param no
	 * @return
	 */
	public static long invite(Context context, String tag, String no) {
		ArrayList<NameValuePair> requestParam = new ArrayList<NameValuePair>();
		requestParam.add(new BasicNameValuePair("no", no));
		return ConnectorManage.getInstance(context).PostHttpRequest(context, Config.INVITE, tag, requestParam,
				ResponseEPDetail.class, null);
	}

	/**
	 * @Description 添加地址
	 * @author Created by qinxianyuzou on 2015-1-5.
	 * @param context
	 * @param tag
	 * @param default_addr
	 * @param to_who
	 * @param to_where
	 * @param post_no
	 * @return
	 */
	public static long add_logistics(Context context, String tag, String default_addr, String to_who, String to_where,
			String post_no) {
		ArrayList<NameValuePair> requestParam = new ArrayList<NameValuePair>();
		requestParam.add(new BasicNameValuePair("default_addr", default_addr));
		requestParam.add(new BasicNameValuePair("to_who", to_who));
		requestParam.add(new BasicNameValuePair("to_where", to_where));
		requestParam.add(new BasicNameValuePair("post_no", post_no));
		return ConnectorManage.getInstance(context).PostHttpRequest(context, Config.ADD_LOGISTICS, tag, requestParam,
				ResponseLogistics.class, null);
	}

	/**
	 * @Description 下载APP
	 * @author Created by qinxianyuzou on 2015-1-8.
	 * @param context
	 * @param tag
	 * @param eaid
	 * @return
	 */
	public static long get_earn_downloadurl(Context context, String tag, String eaid) {
		ArrayList<NameValuePair> requestParam = new ArrayList<NameValuePair>();
		requestParam.add(new BasicNameValuePair("eaid", eaid));
		return ConnectorManage.getInstance(context).GetHttpRequest(context, Config.GET_EARN_DOWNLOADURL, tag,
				requestParam, ResponseDownAPP.class, null);
	}

	/**
	 * @Description 解锁
	 * @author Created by qinxianyuzou on 2015-1-8.
	 * @param context
	 * @param tag
	 * @param code
	 *            000000
	 * @param data
	 *            编辑性别传0或1，编辑生日传日期2015/01/008
	 * @return
	 */
	public static long lock_earn(Context context, String tag) {
		ArrayList<NameValuePair> requestParam = new ArrayList<NameValuePair>();
		requestParam.add(new BasicNameValuePair("code", "000000"));
		return ConnectorManage.getInstance(context).GetHttpRequest(context, Config.LOCK_EARN, tag, requestParam,
				BaseResponse.class, null);
	}

	/**
	 * @Description
	 * @author Created by qinxianyuzou on 2015-1-8.
	 * @param context
	 * @param tag
	 * @param data
	 *            编辑性别传0或1
	 * @return
	 */
	public static long edit_sex(Context context, String tag, int data) {
		ArrayList<NameValuePair> requestParam = new ArrayList<NameValuePair>();
		requestParam.add(new BasicNameValuePair("code", "000001"));
		requestParam.add(new BasicNameValuePair("data", data + ""));
		return ConnectorManage.getInstance(context).GetHttpRequest(context, Config.LOCK_EARN, tag, requestParam,
				BaseResponse.class, null);
	}

	/**
	 * @Description
	 * @author Created by qinxianyuzou on 2015-1-8.
	 * @param context
	 * @param tag
	 * @param data
	 *            编辑生日传日期2015/01/008
	 * @return
	 */
	public static long edit_birthday(Context context, String tag, String data) {
		ArrayList<NameValuePair> requestParam = new ArrayList<NameValuePair>();
		requestParam.add(new BasicNameValuePair("code", "000002"));
		if (!StringUtils.isEmpty(data)) {
			requestParam.add(new BasicNameValuePair("data", data));
		}
		return ConnectorManage.getInstance(context).GetHttpRequest(context, Config.LOCK_EARN, tag, requestParam,
				BaseResponse.class, null);
	}

	/**
	 * @Description 获取积分广告列表
	 * @author Created by qinxianyuzou on 2015-1-8.
	 * @param context
	 * @param tag
	 * @return
	 */
	public static long get_earn_list(Context context, String tag) {
		ArrayList<NameValuePair> requestParam = new ArrayList<NameValuePair>();
		return ConnectorManage.getInstance(context).GetHttpRequest(context, Config.GET_EARN_LIST, tag, requestParam,
				ResponseLockADList.class, null);
	}

	/**
	 * @Description 获取邀请详情
	 * @author Created by qinxianyuzou on 2015-1-9.
	 * @param context
	 * @param tag
	 * @return
	 */
	public static long get_invite_detail(Context context, String tag) {
		ArrayList<NameValuePair> requestParam = new ArrayList<NameValuePair>();
		return ConnectorManage.getInstance(context).GetHttpRequest(context, Config.GET_INVITE_DETAIL, tag,
				requestParam, ResponseInviteDetail.class, null);
	}
}
