package com.example.activity.common;

import android.app.Activity;
import android.content.Intent;

import java.util.LinkedList;

/**
 * Activity管理器 Created by LiesLee on 2014/12/28. Email: LiesLee@foxmail.com
 */
public class KeyGuardActivityManager {

	private static KeyGuardActivityManager instance = null;
	public final static int MAIN_CODE = 1010;
	/** Activity栈 **/
	private LinkedList<Activity> acts;

	private KeyGuardActivityManager() {
		acts = new LinkedList<Activity>();
	};

	/**
	 * 获取Activity管理器（单例）
	 * 
	 * @return
	 */
	public static KeyGuardActivityManager getInstance() {
		if (instance == null) {
			instance = new KeyGuardActivityManager();
		}
		return instance;
	}

	/**
	 * 添加一个Activity
	 * 
	 * @param act
	 */
	public void addActivity(Activity act) {
		acts.add(act);
	}

	/**
	 * 删除一个Activity
	 * 
	 * @param act
	 */
	public void removeActivity(Activity act) {
		if (acts != null && acts.indexOf(act) >= 0) {
			acts.remove(act);
		}
	}

	/** 清理所有activity **/
	public void cleanActivity() {
		while (acts.size() != 0) {
			Activity act = acts.poll();
			act.finish();
		}
	}

	/**
	 * 获取最后一个添加到集合里面的Activity
	 * 
	 * @return Activity
	 */
	public Activity getTopActivity() {
		return (acts == null || acts.size() <= 0) ? null : acts.get(acts.size() - 1);
	}

	public void goTo(Activity activity, Intent it) {
		activity.startActivity(it);
	}

	public void goFoResult(Activity self, Intent it, int RequestCode) {
		self.startActivityForResult(it, RequestCode);
	}

}
