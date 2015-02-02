package com.example.util;

import android.content.Context;

import com.umeng.analytics.MobclickAgent;

/**
 * @Description 友盟事件统计
 * @author Created by qinxianyuzou on 2015-1-29.
 */
public class YouMengUtil {
	/** 打开登陆页面 */
	public final static String OPEN_LOGIN = "";
	/**  */
	public final static String OPEN_REG = "";
	/**  */
	public final static String REG_SUCCESS = "";
	/**  */
	public final static String OPEN_APP_DOWNLOAD = "";
	/**  */
	public final static String CLICK_DOWNLOAD = "";
	/**  */
	public final static String START_DOWNLOAD = "";
	/**  */
	public final static String DOWNLOAD_SUCCESS = "";

	public static void sendEvent(Context context, String lableID) {
		MobclickAgent.onEvent(context, lableID);
	}

	public static void sendEventLable(Context context, String lableID, String lable) {
		MobclickAgent.onEvent(context, lableID, lable);
	}

}
