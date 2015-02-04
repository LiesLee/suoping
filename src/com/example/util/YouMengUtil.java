package com.example.util;

import java.util.HashMap;

import android.content.Context;

import com.umeng.analytics.MobclickAgent;

/**
 * @Description 友盟事件统计
 * @author Created by qinxianyuzou on 2015-1-29.
 */
public class YouMengUtil {
	/** 打开登陆页面 */
	public final static String OPEN_LOGIN = "OPEN_LOGIN";
	/** 打开注册页面 */
	public final static String OPEN_REG = "OPEN_REG";
	/** 登陆失败 */
	public final static String LOGIN_FAILURE = "LOGIN_FAILURE";
	/** 登陆没有响应 */
	public final static String LOGIN_NO_RESPONSE = "LOGIN_NO_RESPONSE";
	/** 注册成功 */
	public final static String REG_SUCCESS = "REG_SUCCESS";
	/** 注册失败 */
	public final static String REG_FAILURE = "REG_FAILURE";
	/** 注册没有响应 */
	public final static String REG_NO_RESPONSE = "REG_NO_RESPONSE";
	/** 打开下载页 */
	public final static String OPEN_APP_DOWNLOAD = "OPEN_APP_DOWNLOAD";
	/** 点击下载 */
	public final static String CLICK_DOWNLOAD = "CLICK_DOWNLOAD";
	/** 开始下载 */
	public final static String START_DOWNLOAD = "START_DOWNLOAD";
	/** 下载成功 */
	public final static String DOWNLOAD_SUCCESS = "DOWNLOAD_SUCCESS";
	/** 下载失败 */
	public final static String DOWNLOAD_FAILURE = "DOWNLOAD_FAILURE";
	/** 安装成功 */
	public final static String INSTALL_SUCCESS = "INSTALL_SUCCESS";
	/** 启动时长 */
	public final static String APP_START_TIME_LONG = "APP_START_TIME_LONG";
	/** 获取奖励 */
	public final static String GET_REWARD = "GET_REWARD";
	/** 兑换商品 */
	public final static String EXCHANGE_COMMODITY = "EXCHANGE_COMMODITY";
	/** 兑换成功 */
	public final static String EXCHANGE_SUCCESS = "EXCHANGE_SUCCESS";
	/** 兑换失败 */
	public final static String EXCHANGE_FAILURE = "EXCHANGE_FAILURE";
	// /** 消费积分 */
	// public final static String EXCHANGE_MONEY = "EXCHANGE_MONEY";
	// /** 打开锁屏页次数 */
	// public final static String OPEN_LOCK = "OPEN_LOCK";
	/** 分享新浪 */
	public final static String SHARE_SINA = "SHARE_SINA";
	/** 分享腾讯围脖 */
	public final static String SHARE_TENCENTWB = "SHARE_TENCENTWB";
	/** 分享qq */
	public final static String SHARE_QQ = "SHARE_QQ";
	/** 分享qq空间 */
	public final static String SHARE_QZONE = "SHARE_QZONE";
	/** 分享微信 */
	public final static String SHARE_WX = "SHARE_WX";
	/** 分享朋友圈 */
	public final static String SHARE_WX_FRIEND = "SHARE_WX_FRIEND";
	/** 分享短信 */
	public final static String SHARE_SMS = "SHARE_SMS";
	//
	// 提交内容的key
	/** 错误原因 */
	public final static String KEY_REASON = "KEY_REASON";
	/** 商品名称 */
	public final static String KEY_COMMODITY_NAME = "KEY_COMMODITY_NAME";
	/** 商品积分 */
	public final static String KEY_COMMODITY_MONEY = "KEY_COMMODITY_MONEY";
	/** 分享标签 */
	public final static String KEY_SHARE = "KEY_SHARE";

	public static void onEvent(Context context, String lableID) {
		MobclickAgent.onEvent(context, lableID);
	}

	/**
	 * @Description 统计值
	 * @author Created by qinxianyuzou on 2015-2-3.
	 * @param context
	 * @param lableID
	 * @param time
	 *            时间
	 */
	public static void onEventValue(Context context, String lableID, int time) {
		MobclickAgent.onEventValue(context, lableID, null, time);
	}

	/**
	 * @Description
	 * @author Created by qinxianyuzou on 2015-2-4.
	 * @param context
	 * @param lableID
	 * @param map
	 * @param time
	 */
	public static void onEventValue(Context context, String lableID, HashMap<String, String> map, int time) {
		MobclickAgent.onEventValue(context, lableID, map, time);
	}

	/**
	 * @Description
	 * @author Created by qinxianyuzou on 2015-2-4.
	 * @param context
	 * @param lableID
	 * @param key
	 * @param value
	 */
	public static void onEvent(Context context, String lableID, String key, String value) {
		HashMap<String, String> map = new HashMap<>();
		map.put(key, value);
		MobclickAgent.onEvent(context, lableID, map);
	}

	/**
	 * @Description
	 * @author Created by qinxianyuzou on 2015-2-4.
	 * @param context
	 * @param lableID
	 * @param map
	 */
	public static void onEvent(Context context, String lableID, HashMap<String, String> map) {
		MobclickAgent.onEvent(context, lableID, map);
	}

	public static void sendEventLable(Context context, String lableID, String lable) {
		MobclickAgent.onEvent(context, lableID, lable);
	}

}
