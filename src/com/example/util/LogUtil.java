/**
 * LogUtil.java
 * com.huyeal.util
 *
 * Function： TODO 
 *
 *   ver     date      		author
 * ──────────────────────────────────
 *   		 2012-2-10 		turlet@163.com
 *
 * Copyright (c) 2012,  All Rights Reserved.
 */

package com.example.util;

import android.util.Log;

import com.example.http.base.Config;
/**
 * 全局日志管理类<BR>
 * [功能详细描述]
 */
public class LogUtil {

	public static final String TAG = LogUtil.class.getSimpleName();

	public static void d(String tag, String msg) {
		if (Config.DEBUG) {
			Log.d(tag, msg);
		}
	}

	public static void i(String tag, String msg) {
		if (Config.DEBUG) {
			Log.i(tag, msg);
		}
	}

	public static void w(String tag, String msg) {
		if (Config.DEBUG) {
			Log.w(tag, msg);
		}
	}

	public static void e(String tag, String msg) {
		if (Config.DEBUG) {
			Log.e(tag, msg);
		}
	}

	public static void v(String tag, String msg) {
		if (Config.DEBUG) {
			Log.v(tag, msg);
		}
	}

	public static void log(Throwable t) {
		if (Config.DEBUG && t != null) {
			t.printStackTrace();
		}
	}

}
