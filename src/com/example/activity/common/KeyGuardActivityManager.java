package com.example.activity.common;

import android.app.Activity;
import android.content.Intent;

import com.example.keyguard.R;

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

    public void goTo(Activity self, Class<? extends Activity> cls) {
        goTo(self, cls, R.anim.activity_in, R.anim.activity_out);
    }

    public void goTo(Activity self, Intent it) {
        goTo(self, it, R.anim.activity_in, R.anim.activity_out);
    }

    public void goToBottom(Activity self, Class<? extends Activity> cls) {
        goTo(self, cls, R.anim.iphone_ui_in, R.anim.iphone_ui_out);
    }

    public void goToBottom(Activity self, Intent it) {
        goTo(self, it, R.anim.iphone_ui_in, R.anim.iphone_ui_out);
    }

    public void goFoResultBottom(Activity self, Class<? extends Activity> cls, int RequestCode) {
        Intent it = new Intent(self, cls);
        goFoResultBottom(self, it, RequestCode);
    }

    public void goFoResultBottom(Activity self, Intent it, int RequestCode) {
        self.startActivityForResult(it, RequestCode);
        self.overridePendingTransition(R.anim.iphone_ui_in, R.anim.iphone_ui_out);
    }

    public void goTo(Activity self, Class<? extends Activity> cls, int anim_in, int animout) {
        Intent it = new Intent(self, cls);
        goTo(self, it, anim_in, animout);
    }

    public void goTo(Activity self, Intent it, int anim_in, int animout) {
        self.startActivity(it);
        self.overridePendingTransition(anim_in, animout);
    }

    public void goFoResult(Activity self, Class<? extends Activity> cls, int RequestCode) {
        Intent it = new Intent(self, cls);
        goFoResult(self, it, RequestCode);
    }

    public void goFoResult(Activity self, Intent it, int RequestCode) {
        self.startActivityForResult(it, RequestCode);
        self.overridePendingTransition(R.anim.activity_in, R.anim.activity_out);
    }

    public void addActivity(Activity act) {
        acts.add(act);
    }

    public void removeActivity(Activity act) {
        if (acts != null && acts.indexOf(act) >= 0) {
            acts.remove(act);
        }
    }

    public void cleanActivity() {
        while (acts.size() != 0) {
            Activity act = acts.poll();
            act.finish();
        }
    }

    public Activity getTopActivity() {
        return (acts == null || acts.size() <= 0) ? null : acts.get(acts.size() - 1);
    }

}
