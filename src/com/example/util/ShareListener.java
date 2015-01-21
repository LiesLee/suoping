package com.example.util;

import android.app.Activity;

import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.bean.SocializeEntity;
import com.umeng.socialize.controller.listener.SocializeListeners.SnsPostListener;

/**
 * @Description 分享回调监听
 * @author Created by qinxianyuzou on 2015-1-20.
 */
public class ShareListener implements SnsPostListener {

	private Activity activity;

	public ShareListener(Activity activity) {
		super();
		this.activity = activity;
	}

	@Override
	public void onStart() {
		PublicUtil.showToast(activity, "开始分享.");
	}

	@Override
	public void onComplete(SHARE_MEDIA platform, int eCode, SocializeEntity entity) {
		if (eCode == 200) {
			PublicUtil.showToast(activity, "分享成功.");
		} else {
			String eMsg = "";
			if (eCode == -101) {
				eMsg = "没有授权";
			}
			PublicUtil.showToast(activity, "分享失败[" + eCode + "] " + eMsg);
		}
	}

}
