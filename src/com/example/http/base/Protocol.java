package com.example.http.base;

import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import android.content.Context;

import com.android.volley.VolleyError;
import com.example.http.respose.ResponseDownAPP;
import com.example.http.respose.ResponseEPDetail;
import com.example.http.respose.ResponseEXProduct;
import com.example.http.respose.ResponseExchangeDetail;
import com.example.http.respose.ResponseFreshmanAward;
import com.example.http.respose.ResponseInviteDetail;
import com.example.http.respose.ResponseLockADList;
import com.example.http.respose.ResponseLogistics;
import com.example.http.respose.ResponseNewcomerAbout;
import com.example.http.respose.ResponseShare;
import com.example.http.respose.ResponseSignIn;
import com.example.http.respose.ResponseUpdate;
import com.example.http.respose.ResponseUserInfo;
import com.example.util.PublicUtil;
import com.example.util.StringUtils;
import com.example.util.YouMengUtil;

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
	 * @author Created by qinxianyuzou on 2015-2-3.
	 * @param context
	 * @param tag
	 * @param epid
	 * @param alipaly
	 * @param alipalyName
	 * @param phone
	 * @param address
	 * @return
	 */
	public static long exchange_product(Context context, String tag, String epid, String alipaly, String alipalyName,
			String phone, String address) {
		ArrayList<NameValuePair> requestParam = new ArrayList<NameValuePair>();
		requestParam.add(new BasicNameValuePair("epid", epid));
		if (!StringUtils.isEmpty(alipaly)) {
			requestParam.add(new BasicNameValuePair("ali_acc", alipaly));
		}
		if (!StringUtils.isEmpty(alipaly)) {
			requestParam.add(new BasicNameValuePair("ali_name", alipalyName));
		}
		if (!StringUtils.isEmpty(phone)) {
			requestParam.add(new BasicNameValuePair("phone", phone));
		}

		if (!StringUtils.isEmpty(address)) {
			requestParam.add(new BasicNameValuePair("log_id", address));
		}

		return ConnectorManage.getInstance(context).PostHttpRequest(context, Config.EXCHANGE_PRODUCT, tag,
				requestParam, ResponseEPDetail.class, null);
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
	public static long invite(final Context context, String tag, String no) {
		ArrayList<NameValuePair> requestParam = new ArrayList<NameValuePair>();
		requestParam.add(new BasicNameValuePair("no", no));
		return ConnectorManage.getInstance(context).PostHttpRequest(context, Config.INVITE, tag, requestParam,
				ResponseEPDetail.class, new HttpCallBack() {

					@Override
					public <T> void onHttpSuccess(long flag, JSONObject jsonString, T response) {
						// TODO Auto-generated method stub
						YouMengUtil.onEvent(context, YouMengUtil.GET_REWARD);
					}

					@Override
					public void onHttpError(long flag, VolleyError error) {
						// TODO Auto-generated method stub

					}
				});
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
			String post_no, String phone) {
		ArrayList<NameValuePair> requestParam = new ArrayList<NameValuePair>();
		requestParam.add(new BasicNameValuePair("default_addr", default_addr));
		requestParam.add(new BasicNameValuePair("to_who", to_who));
		requestParam.add(new BasicNameValuePair("to_where", to_where));
		requestParam.add(new BasicNameValuePair("post_no", post_no));
		requestParam.add(new BasicNameValuePair("to_phone", phone));
		return ConnectorManage.getInstance(context).PostHttpRequest(context, Config.ADD_LOGISTICS, tag, requestParam,
				ResponseLogistics.class, null);
	}

	/**
	 * @Description 编辑物流地址
	 * @author Created by qinxianyuzou on 2015-1-28.
	 * @param context
	 * @param tag
	 * @param default_addr
	 * @param to_who
	 * @param to_where
	 * @param post_no
	 * @param phone
	 * @return
	 */
	public static long edit_logistics(Context context, String tag, String lid, String default_addr, String to_who,
			String to_where, String post_no, String phone) {
		ArrayList<NameValuePair> requestParam = new ArrayList<NameValuePair>();
		requestParam.add(new BasicNameValuePair("lid", lid));
		requestParam.add(new BasicNameValuePair("default_addr", default_addr));
		requestParam.add(new BasicNameValuePair("to_who", to_who));
		requestParam.add(new BasicNameValuePair("to_where", to_where));
		requestParam.add(new BasicNameValuePair("post_no", post_no));
		requestParam.add(new BasicNameValuePair("to_phone", phone));
		return ConnectorManage.getInstance(context).PostHttpRequest(context, Config.EDIT_LOGISTICS, tag, requestParam,
				BaseResponse.class, null);
	}

	/**
	 * @Description 删除物流地址
	 * @author Created by qinxianyuzou on 2015-1-28.
	 * @param context
	 * @param tag
	 * @param lid
	 * @return
	 */
	public static long del_logistics(Context context, String tag, String lid) {
		ArrayList<NameValuePair> requestParam = new ArrayList<NameValuePair>();
		requestParam.add(new BasicNameValuePair("lid", lid));
		return ConnectorManage.getInstance(context).PostHttpRequest(context, Config.DEL_LOGISTICS, tag, requestParam,
				BaseResponse.class, null);
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
	 * @Description 了解锁屏赚获得积分
	 * @author Created by qinxianyuzou on 2015-1-28.
	 * @param context
	 * @param tag
	 * @return
	 */
	public static long newcomerAbout(Context context, String tag) {
		ArrayList<NameValuePair> requestParam = new ArrayList<NameValuePair>();
		requestParam.add(new BasicNameValuePair("code", "000004"));
		return ConnectorManage.getInstance(context).GetHttpRequest(context, Config.LOCK_EARN, tag, requestParam,
				ResponseNewcomerAbout.class, null);
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
	public static long lock_earn(Context context, String tag, HttpCallBack httpCallBack) {
		ArrayList<NameValuePair> requestParam = new ArrayList<NameValuePair>();
		requestParam.add(new BasicNameValuePair("code", "000000"));
		return ConnectorManage.getInstance(context).GetHttpRequest(context, Config.LOCK_EARN, tag, requestParam,
				BaseResponse.class, httpCallBack);
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
	public static long get_earn_list(Context context, String tag, String w, String h) {
		ArrayList<NameValuePair> requestParam = new ArrayList<NameValuePair>();
		requestParam.add(new BasicNameValuePair("w", w));
		requestParam.add(new BasicNameValuePair("h", h));
		return ConnectorManage.getInstance(context).GetHttpRequest(context, Config.GET_EARN_LIST, tag, requestParam,
				ResponseLockADList.class, null);
	}

	/**
	 * 用户兑换商品详细信息
	 * 
	 * @param ctx
	 * @param tag
	 * @return
	 */
	public static long get_user_ex_detail(Context ctx, String tag) {
		ArrayList<NameValuePair> requestParam = new ArrayList<NameValuePair>();
		return ConnectorManage.getInstance(ctx).GetHttpRequest(ctx, Config.USER_EXCHANG_DETAIL, tag, requestParam,
				ResponseExchangeDetail.class, null);
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

	/**
	 * @Description 分享信息
	 * @author Created by qinxianyuzou on 2015-1-14.
	 * @param context
	 * @param tag
	 * @param code
	 * @return
	 */
	public static long fshare(Context context, String tag) {
		ArrayList<NameValuePair> requestParam = new ArrayList<NameValuePair>();
		return ConnectorManage.getInstance(context).GetHttpRequest(context, Config.FSHARE, tag, requestParam,
				ResponseShare.class, null);
	}

	/**
	 * @Description 待激活收益列表
	 * @author Created by qinxianyuzou on 2015-1-26.
	 * @param context
	 * @param tag
	 * @return
	 */
	public static long get_jihuo_earn(Context context, String tag) {
		ArrayList<NameValuePair> requestParam = new ArrayList<NameValuePair>();
		return ConnectorManage.getInstance(context).GetHttpRequest(context, Config.GET_JIHUO_EARN, tag, requestParam,
				ResponseSignIn.class, null);
	}

	/**
	 * @Description 签到成功接口
	 * @author Created by qinxianyuzou on 2015-1-26.
	 * @param context
	 * @param tag
	 * @return
	 */
	public static long signinSuccess(Context context, String tag, String url, HttpCallBack httpCallBack) {
		ArrayList<NameValuePair> requestParam = new ArrayList<NameValuePair>();
		return ConnectorManage.getInstance(context).GetHttpRequest(context, url, tag, requestParam, BaseResponse.class,
				httpCallBack);
	}

	/**
	 * @Description 锁屏页运行回调
	 * @author Created by qinxianyuzou on 2015-2-9.
	 * @param context
	 * @param tag
	 * @param eaid
	 * @param httpCallBack
	 * @return
	 */
	public static long APP_RETURN(Context context, String tag, String eaid, HttpCallBack httpCallBack) {
		ArrayList<NameValuePair> requestParam = new ArrayList<NameValuePair>();
		// requestParam.add(new BasicNameValuePair("eaid", eaid));
		// requestParam.add(new BasicNameValuePair("username",
		// PublicUtil.getUserInfo_Entity(context).getUsername()));
		return ConnectorManage.getInstance(context).GetHttpRequest(context,
				Config.APP_RETURN + eaid + "/" + PublicUtil.getUserInfo_Entity(context).getUsername(), tag,
				requestParam, BaseResponse.class, httpCallBack);
	}

	/**
	 * @Description 新手活动
	 * @author Created by qinxianyuzou on 2015-1-28.
	 * @param context
	 * @param tag
	 * @return
	 */
	public static long freshmanAward(Context context, String tag) {
		ArrayList<NameValuePair> requestParam = new ArrayList<NameValuePair>();
		return ConnectorManage.getInstance(context).GetHttpRequest(context, Config.FRESHMAN_AWARD, tag, requestParam,
				ResponseFreshmanAward.class, null);
	}

	/**
	 * @Description 提交信鸽token
	 * @author Created by qinxianyuzou on 2015-2-5.
	 * @param context
	 * @param tag
	 * @param token
	 * @return
	 */
	public static long send_utoken(Context context, String tag, String utoken) {
		ArrayList<NameValuePair> requestParam = new ArrayList<NameValuePair>();
		requestParam.add(new BasicNameValuePair("utoken", utoken));
		return ConnectorManage.getInstance(context).GetHttpRequest(context, Config.SEND_UTOKEN, tag, requestParam,
				BaseResponse.class, null);
	}
}
