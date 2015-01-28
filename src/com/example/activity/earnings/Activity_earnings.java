package com.example.activity.earnings;

import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.example.activity.common.Activity_PublicWeb;
import com.example.activity.common.BaseActivity;
import com.example.activity.common.KeyGuardActivityManager;
import com.example.http.base.Code;
import com.example.http.base.Protocol;
import com.example.http.respose.ResponseUserInfo;
import com.example.keyguard.R;
import com.example.util.LogUtil;
import com.example.util.PublicUtil;
import com.example.util.SharedPreferenceUtil;
import com.example.util.UIHelper;
import com.google.gson.Gson;
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
	/** 当前收益 */
	@ViewInject(R.id.tv_earning)
	private TextView tv_earning;
	/** 今日收益 */
	@ViewInject(R.id.tv_todayEarning)
	private TextView tv_todayEarning;
	/** 累计收益 */
	@ViewInject(R.id.tv_allEarning)
	private TextView tv_allEarning;

	private long userinfoFlag;
	public static Activity_earnings activity_earnings;

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
		LogUtil.d("onCreate", "onCreate");
		activity_earnings = this;
		initUI();
		initData();

	}

	@Override
	public <T> void onHttpSuccess(long flag, JSONObject jsonString, T response) {
		if (flag == userinfoFlag) {
			UIHelper.cancelProgressDialog();
			ResponseUserInfo msg = (ResponseUserInfo) response;
			if (msg.getCode().equals(Code.CODE_SUCCESS)) {
				SharedPreferenceUtil.getInstance(activity).putString(SharedPreferenceUtil.USERINFO,
						new Gson().toJson(msg.getData()));
				tv_allEarning.setText("" + PublicUtil.getUserInfo(activity).getSum_earn());
				tv_todayEarning.setText("" + PublicUtil.getUserInfo(activity).getToday_earn());
				tv_earning.setText("" + PublicUtil.getUserInfo(activity).getSum_earn());
			} else {
				showToast(msg.getMsg());
			}
		}

	}

	@Override
	public void onHttpError(long flag, VolleyError error) {
		UIHelper.cancelProgressDialog();
	}

	@Override
	protected void initUI() {

		rl_earnings_earnings.setOnClickListener(this);
		rl_earnings_action.setOnClickListener(this);
		rl_earnings_details.setOnClickListener(this);
		rl_earnings_exchange.setOnClickListener(this);
		rl_earnings_announcement.setOnClickListener(this);

	}

	@Override
	protected void initData() {
		// if
		// (SharedPreferenceUtil.getInstance(activity).getString(SharedPreferenceUtil.USERINFO).equals(""))
		// {
		userinfoFlag = Protocol.get_user_info(this, setTag());
		UIHelper.showMsgProgressDialog(this, "加载中...");
		// }
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
			Activity_NotActive.luanch(activity);
			break;
		case R.id.rl_earnings_action:
			Activity_newcomer.luanch(this);
			break;
		case R.id.rl_earnings_details:
			Activity_PublicWeb.luanch(activity, "锁屏赚收益明细", "http://www.baidu.com");
			break;
		case R.id.rl_earnings_exchange:
			Activity_EarningsShop.luanch(activity);
			break;
		case R.id.rl_earnings_announcement:
			Activity_PublicWeb.luanch(activity, "公告", "http://www.baidu.com");
			break;

		default:
			break;
		}
	}

	public void reloadData() {
		userinfoFlag = Protocol.get_user_info(this, setTag());
	}
}
