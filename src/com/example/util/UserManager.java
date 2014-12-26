package com.example.util;

import com.example.entity.UserInfo_Entity;

public class UserManager {
	/** 系统当前登录用户(仅一个) */
	private static UserInfo_Entity mLoginUser;
	public boolean mLoginFlag = false; // 账户登陆状态
	private static String mHash = ""; // API访问安全验证码串
	// 账户管理器单实例对象
	private static UserManager mInstance = null;
	private static int mRefCount = 0;

	public UserManager() {

	}

	/**
	 * 获取账户管理器单件实例对象
	 */
	static public synchronized UserManager getInstance() {
		if (null == mInstance) {
			mInstance = new UserManager();
		}
		mRefCount++;
		return mInstance;
	}

	/**
	 * 账户管理器实例引用释放，当引用计数为0销毁实例对象
	 */
	public static void release() {
		mRefCount--;
		if (mRefCount == 0) {
			// 清除相关变量
			mLoginUser = null;
			// mFriendList.clear();
			// mAvatarCacheMap.clear();
			// mLoginFlag = false;
			mInstance = null;
		}
	}

	public static UserInfo_Entity GetLoginUserInfo() {
		// if (mLoginFlag)
		return mLoginUser;
		// return null;
	}

	public static void SetLoginUserInfo(UserInfo_Entity user_Info) {
		mLoginUser = user_Info;
	}

	public boolean GetLoginFlag() {
		return mLoginFlag;
	}

	public void SetLoginFlag(boolean flag) {
		mLoginFlag = flag;
	}

	public static String getHash() {
		return mHash;
	}

	public static void setHash(String hash) {
		UserManager.mHash = hash;
	}

}
