package com.example.activity.invitation;

import org.json.JSONObject;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.example.activity.common.BaseActivity;
import com.example.activity.common.DialogClick;
import com.example.activity.common.KeyGuardActivityManager;
import com.example.entity.respose.Code;
import com.example.entity.respose.ResponseInviteDetail;
import com.example.entity.respose.ResponseShare;
import com.example.http.Protocol;
import com.example.keyguard.R;
import com.example.util.LogUtil;
import com.example.util.PublicUtil;
import com.example.util.ShareListener;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.bean.SocializeConfig;
import com.umeng.socialize.bean.SocializeEntity;
import com.umeng.socialize.controller.UMServiceFactory;
import com.umeng.socialize.controller.UMSocialService;
import com.umeng.socialize.controller.listener.SocializeListeners.SnsPostListener;
import com.umeng.socialize.media.SinaShareContent;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.sso.SinaSsoHandler;
import com.umeng.socialize.sso.TencentWBSsoHandler;
import com.umeng.socialize.sso.UMSsoHandler;

/**
 * @Description 页面模板
 * @author Created by qinxianyuzou on 2014-12-30.
 */
public class Activity_invitation extends BaseActivity {

	/** 标题栏 */
	@ViewInject(R.id.tv_public_top_title)
	private TextView tv_public_top_title;
	/** 返回按钮 */
	@ViewInject(R.id.rl_public_back)
	private RelativeLayout rl_public_back;
	/** 已邀请列表 */
	@ViewInject(R.id.lay_activated)
	private LinearLayout lay_activated;
	/** 分享邀请码 */
	@ViewInject(R.id.btn_invitation)
	private Button btn_invitation;
	/** 一键获取邀请码 */
	@ViewInject(R.id.btn_invitation_number)
	private Button btn_invitation_number;
	/** 当天获得价格 */
	@ViewInject(R.id.tv_invitation_rmb)
	private TextView tv_invitation_rmb;
	/** 累计收益 */
	@ViewInject(R.id.tv_all_invitation_earning)
	private TextView tv_all_invitation_earning;
	/** 邀请人数 */
	@ViewInject(R.id.tv_invited)
	private TextView tv_invited;
	/** 标题 */
	private static String mTitle = "邀请";
	private long inviteDetailFlag;
	private long fshareFlag;
	private String fshareMSG = "";
	private String fshareURL = "";
	// 首先在您的Activity中添加如下成员变量
	final UMSocialService mController = UMServiceFactory.getUMSocialService("com.umeng.share");

	/**
	 * @Description 不设置标题
	 * @author Created by qinxianyuzou on 2014-12-30.
	 * @param activity
	 */
	public static void luanch(Activity activity) {
		Intent intent = new Intent(activity, Activity_invitation.class);
		KeyGuardActivityManager.getInstance().goFoResult(activity, intent, KeyGuardActivityManager.MAIN_CODE);
	}

	/**
	 * @Description 设置标题
	 * @author Created by qinxianyuzou on 2014-12-30.
	 * @param activity
	 * @param title
	 */
	public static void luanch(Activity activity, String title) {
		mTitle = title;
		Intent intent = new Intent(activity, Activity_invitation.class);
		KeyGuardActivityManager.getInstance().goFoResult(activity, intent, KeyGuardActivityManager.MAIN_CODE);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tab_invitation);
		ViewUtils.inject(activity);
		initUI();
		initData();
	}

	@Override
	protected void initUI() {
		// TODO Auto-generated method stub
		tv_public_top_title.setText(mTitle);
		rl_public_back.setOnClickListener(this);
		btn_invitation_number.setOnClickListener(this);
		lay_activated.setOnClickListener(this);
		btn_invitation.setOnClickListener(this);
		mController.getConfig().removePlatform(SHARE_MEDIA.RENREN, SHARE_MEDIA.DOUBAN);
	}

	@Override
	protected void initData() {
		// TODO Auto-generated method stub
		inviteDetailFlag = Protocol.get_invite_detail(activity, setTag());
		fshareFlag = Protocol.fshare(activity, setTag());
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		/** 使用SSO授权必须添加如下代码 */
		UMSsoHandler ssoHandler = mController.getConfig().getSsoHandler(requestCode);
		if (ssoHandler != null) {
			ssoHandler.authorizeCallBack(requestCode, resultCode, data);
		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.rl_public_back:
			finish();
			break;
		case R.id.lay_activated:
			// Activity_InvitationInfo.luanch(activity);
			break;
		case R.id.btn_invitation:
			// 是否只有已登录用户才能打开分享选择页
			PublicUtil.setShare(activity, mController, "锁屏赚，分享赚好礼！", fshareMSG, fshareURL);
			mController.getConfig().setSsoHandler(new SinaSsoHandler());
			mController.getConfig().setSsoHandler(new TencentWBSsoHandler());
			// mController.openShare(activity, false);
			// mController.openShare(activity, new ShareListener(activity));
			mController.openShare(activity, new SnsPostListener() {

				@Override
				public void onStart() {
					PublicUtil.showToast(activity, "开始分享.");
					LogUtil.d("", "");
				}

				@Override
				public void onComplete(SHARE_MEDIA platform, int eCode, SocializeEntity entity) {
					if (eCode == 200) {
						PublicUtil.showToast(activity, "分享成功.");
					} else {
						String eMsg = "";
						if (eCode == -101) {
							eMsg = "没有授权";
						} else if (eCode == 40000) {
							eMsg = "取消分享";
						} else {
							eMsg = "分享失败[" + eCode + "] ";
						}
						PublicUtil.showToast(activity, eMsg);
					}
					SocializeConfig.getSocializeConfig().cleanListeners();
				}
			});
			break;
		case R.id.btn_invitation_number:
			final DialogClick dialogClick1 = new DialogClick(activity);
			dialogClick1.requestWindowFeature(Window.FEATURE_NO_TITLE);
			dialogClick1.show();
			dialogClick1.setContent("您的邀请码", "" + PublicUtil.getUserInfo(activity).getInvite_no(), "一键复制",
					new OnClickListener() {

						@Override
						public void onClick(View v) {
							// TODO Auto-generated method stub
							dialogClick1.dismiss();
							if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.HONEYCOMB) {
								copyInviteNo_8();
							} else {
								copyInviteNo_11();
							}
							showToast("复制成功！");
						}
					});
			break;

		default:
			break;
		}
	}

	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	private void copyInviteNo_11() {
		ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
		ClipData clip = ClipData.newPlainText("simple text", PublicUtil.getUserInfo(activity).getInvite_no());
		clipboard.setPrimaryClip(clip);
	}

	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	@SuppressWarnings("deprecation")
	private void copyInviteNo_8() {
		ClipboardManager c = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
		c.setText(PublicUtil.getUserInfo(activity).getInvite_no());
	}

	@Override
	public <T> void onHttpSuccess(long flag, JSONObject jsonString, T response) {
		// TODO Auto-generated method stub
		if (inviteDetailFlag == flag) {
			ResponseInviteDetail msgInfo = (ResponseInviteDetail) response;
			if (msgInfo.getCode().equals(Code.CODE_SUCCESS)) {
				tv_all_invitation_earning.setText(msgInfo.getData().getSum_iearn());
				tv_invited.setText(msgInfo.getData().getSum_num());
				tv_invitation_rmb.setText(msgInfo.getData().getInvite_jifen());
			}
		}
		if (fshareFlag == flag) {
			ResponseShare msgInfo = (ResponseShare) response;
			if (msgInfo.isSuccess()) {
				fshareMSG = msgInfo.getData().getShare_msg();
				fshareURL = msgInfo.getData().getShare_url();
			}
		}
	}

	@Override
	public void onHttpError(long flag, VolleyError error) {
		// TODO Auto-generated method stub

	}

	@Override
	public String setTag() {
		// TODO Auto-generated method stub
		return this.getClass().getSimpleName();
	}

}
