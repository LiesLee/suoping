package com.example.activity.earnings;

import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;

import com.android.volley.VolleyError;
import com.example.activity.common.BaseActivity;
import com.example.activity.common.KeyGuardActivityManager;
import com.example.entity.respose.Code;
import com.example.entity.respose.ResponseUserInfo;
import com.example.http.Protocol;
import com.example.keyguard.R;
import com.example.util.LogUtil;
import com.example.util.UIHelper;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

/**
 * @Description 收益
 * @author Created by qinxianyuzou on 2014-12-26.
 */
public class Activity_earnings extends BaseActivity {
	private static final String CELLPHOME_NUMBER = "cellphone_number";
	private static final String PASSEORD = "password";
	private static final String REGISTER_LOGIN = "register_login";
	/** 收益 */
	@ViewInject(R.id.rl_earnings_earnings)
	private RelativeLayout rl_earnings_earnings;
	/** 活动 */
	@ViewInject(R.id.rl_earnings_action)
	private RelativeLayout rl_earnings_action;
	/** 明细 */
	@ViewInject(R.id.rl_earnings_details)
	private RelativeLayout rl_earnings_details;
	/** 兑换 */
	@ViewInject(R.id.rl_earnings_exchange)
	private RelativeLayout rl_earnings_exchange;
	/** 公告 */
	@ViewInject(R.id.rl_earnings_announcement)
	private RelativeLayout rl_earnings_announcement;

	private String cellphoneNumber;
	private String password;
	/** 是否注册登录 **/
	private boolean registerlogin;
	private long loginFlag;

	public static void luanch(Activity activity, String cellphoneNumber, String password, boolean registerlogin) {
		Intent intent = new Intent(activity, Activity_earnings.class);
		intent.putExtra(CELLPHOME_NUMBER, cellphoneNumber);
		intent.putExtra(PASSEORD, password);
		intent.putExtra(REGISTER_LOGIN, registerlogin);
		KeyGuardActivityManager.getInstance().goTo(activity, intent);
	}

	public static void luanch(Activity activity) {
		Intent intent = new Intent(activity, Activity_earnings.class);
		KeyGuardActivityManager.getInstance().goTo(activity, intent);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tab_earnings);
		ViewUtils.inject(activity);
		initUI();
		initData();

	}

	@Override
	public <T> void onHttpSuccess(long flag, JSONObject jsonString, T response) {
		if (flag == loginFlag) {
			UIHelper.cancelProgressDialog();
			ResponseUserInfo msg = (ResponseUserInfo) response;
			if (msg.getCode() == Code.CODE_SUCCESS) {
				LogUtil.i("=====UserInfo====", msg.getMsg().toString());
			} else {
				showToast("加载失败，请重新登录");
				LogUtil.i("======加载失败======", jsonString.toString());
			}
		}

	}

	@Override
	public void onHttpError(long flag, VolleyError error) {

	}

	@Override
	protected void initUI() {

		cellphoneNumber = getIntent().getStringExtra(CELLPHOME_NUMBER);
		password = getIntent().getStringExtra(password);
		registerlogin = getIntent().getBooleanExtra(REGISTER_LOGIN, false);

		rl_earnings_earnings.setOnClickListener(this);
		rl_earnings_action.setOnClickListener(this);
		rl_earnings_details.setOnClickListener(this);
		rl_earnings_exchange.setOnClickListener(this);
		rl_earnings_announcement.setOnClickListener(this);

	}

	@Override
	protected void initData() {
		if (registerlogin) {
			loginFlag = Protocol.login(this, setTag(), cellphoneNumber, password);
			UIHelper.showMsgProgressDialog(this, "加载中...");
		}
	}

	@Override
	public String setTag() {
		return this.getClass().getSimpleName();
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.rl_earnings_earnings:

			break;
		case R.id.rl_earnings_action:

            Activity_newcomer.luanch(this);

			break;
		case R.id.rl_earnings_details:

			break;
		case R.id.rl_earnings_exchange:

			break;
		case R.id.rl_earnings_announcement:

			break;

		default:
			break;
		}
	}
}
