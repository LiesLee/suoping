package com.example.activity.reg;

import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.example.activity.common.BaseActivity;
import com.example.activity.common.KeyGuardActivityManager;
import com.example.http.base.BaseResponse;
import com.example.http.base.Code;
import com.example.http.base.Protocol;
import com.example.keyguard.R;
import com.example.util.StringUtils;
import com.example.util.UIHelper;
import com.example.util.YouMengUtil;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

/**
 * 注册页面2 Created by LiesLee on 2014/12/28. Email: LiesLee@foxmail.com
 */
public class Activity_Reg_Authentication extends BaseActivity implements View.OnClickListener {

	private static final String CELLPHOME_NUMBER = "cellphone_number";
	/** 头部文字 **/
	@ViewInject(R.id.tv_reg_head)
	private TextView tv_reg_head;
	/** 输入提示 **/
	@ViewInject(R.id.tv_input_tips)
	private TextView tv_input_tips;
	/** 输入信息 **/
	@ViewInject(R.id.et_reg)
	private EditText et_reg;
	/** 下一步 **/
	@ViewInject(R.id.btn_reg_next)
	private TextView btn_reg_next;
	/** 返回按钮 **/
	@ViewInject(R.id.iv_back_left)
	private ImageView iv_back_left;
	/** 登录按钮 **/
	@ViewInject(R.id.but_reg_login)
	private Button but_reg_login;

	private String cellphoneNumber;
	private long sendCodeFlag;

	public static void luanch(Activity activity, String cellphoneNumber) {
		Intent intent = new Intent(activity, Activity_Reg_Authentication.class);
		intent.putExtra(CELLPHOME_NUMBER, cellphoneNumber);
		KeyGuardActivityManager.getInstance().goTo(activity, intent);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_reg);
		ViewUtils.inject(this);
		initUI();
		initData();
	}

	@Override
	protected void initUI() {
		tv_reg_head.setText("输入验证码");
		tv_input_tips.setText("出入验证码：");
		et_reg.setHint("");
		cellphoneNumber = getIntent().getStringExtra(CELLPHOME_NUMBER);

		btn_reg_next.setOnClickListener(this);
		iv_back_left.setOnClickListener(this);
		but_reg_login.setOnClickListener(this);
	}

	@Override
	protected void initData() {
		UIHelper.showMsgProgressDialog(this, "正在提交...");
		sendCodeFlag = Protocol.MsgAuthentication(this, cellphoneNumber, setTag());
	}

	@Override
	public String setTag() {
		return this.getClass().getSimpleName();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_reg_next:
			if (StringUtils.isEmpty(et_reg.getText().toString())) {
				UIHelper.showShakeAnim(this, et_reg, "请输入验证码");
				et_reg.requestFocus();
			} else {
				Activity_Reg_Password.luanch(this, cellphoneNumber, et_reg.getText().toString());
				finish();
			}
			break;
		case R.id.iv_back_left:
			this.finish();
		case R.id.but_reg_login:
			YouMengUtil.onEvent(activity, YouMengUtil.OPEN_LOGIN);
			LoginActivity.luanch(activity);
			finish();
		default:
			break;
		}
	}

	@Override
	public <T> void onHttpSuccess(long flag, JSONObject jsonString, T response) {
		if (flag == sendCodeFlag) {
			UIHelper.cancelProgressDialog();
			BaseResponse msg = (BaseResponse) response;
			if (msg.getCode().equals(Code.CODE_SUCCESS)) {
				showToast(msg.getMsg());
			} else {
				showToast(msg.getMsg());
			}
		}
	}

	@Override
	public void onHttpError(long flag, VolleyError error) {
		if (flag == sendCodeFlag) {
			UIHelper.cancelProgressDialog();
			showToast(StringUtils.ERROR_TOAST);
		}
	}
}
