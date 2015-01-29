package com.example.activity.reg;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.example.activity.common.BaseActivity;
import com.example.activity.common.KeyGuardActivityManager;
import com.example.activity.earnings.Activity_earnings;
import com.example.activity.main.MainActivity;
import com.example.http.base.Code;
import com.example.http.base.Protocol;
import com.example.http.respose.ResponseUserInfo;
import com.example.keyguard.R;
import com.example.util.LogUtil;
import com.example.util.SharedPreferenceUtil;
import com.example.util.StringUtils;
import com.example.util.UIHelper;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import org.json.JSONObject;

public class LoginActivity extends BaseActivity implements View.OnClickListener {
	/** 图片 **/
	@ViewInject(R.id.iv_login_pic)
	private ImageView iv_login_pic;
	/** 账号（手机号） **/
	@ViewInject(R.id.et_cellphone)
	private EditText et_cellphone;
	/** 密码 **/
	@ViewInject(R.id.et_password)
	private EditText et_password;
	/** 忘记密码 **/
	@ViewInject(R.id.tv_forgot_password)
	private TextView tv_forgot_password;
	/** 登录按钮 **/
	@ViewInject(R.id.btn_lg)
	private Button btn_lg;
	/** 注册 **/
	@ViewInject(R.id.tv_reg)
	private TextView tv_reg;

	private String password;
	private String cellphoneNumber;
	private long loginFlag;

	public static void luanch(Activity activity) {
		Intent intent = new Intent(activity, LoginActivity.class);
		KeyGuardActivityManager.getInstance().goTo(activity, intent);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		ViewUtils.inject(this);
		initUI();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		// 点击图片
		case R.id.iv_login_pic:

			break;
		// 忘记密码
		case R.id.tv_forgot_password:

			break;
		// 登录按钮
		case R.id.btn_lg:
			if (check()) {
				loginFlag = Protocol.login(this, setTag(), cellphoneNumber, password);
				UIHelper.showMsgProgressDialog(this, "正在登录...");
			}
			break;
		// 注册
		case R.id.tv_reg:
			startActivity(new Intent(activity, Activity_Reg.class));
			break;
		default:
			break;
		}
	}

	boolean check() {
		cellphoneNumber = et_cellphone.getText().toString();
		password = et_password.getText().toString();

		Animation shake = AnimationUtils.loadAnimation(this, R.anim.shake);
		if (StringUtils.isEmpty(cellphoneNumber)) {
			et_cellphone.startAnimation(shake);
			et_cellphone.requestFocus();
			showToast("请输入您的手机号码");
			return false;
		} else if (!StringUtils.phoneNumberValid(cellphoneNumber)) {
			et_cellphone.startAnimation(shake);
			et_cellphone.requestFocus();
			showToast("请输入正确手机号码");
			return false;
		} else if (StringUtils.isEmpty(password)) {
			et_password.startAnimation(shake);
			et_password.requestFocus();
			showToast("请输入您的密码");
			return false;
		}
		return true;
	}

	@Override
	public <T> void onHttpSuccess(long flag, JSONObject jsonString, T response) {
		if (flag == loginFlag) {
			UIHelper.cancelProgressDialog();
			ResponseUserInfo msg = (ResponseUserInfo) response;
			if (msg.getCode().equals(Code.CODE_SUCCESS)) {
				LogUtil.i("=====UserInfo====", msg.getMsg().toString());
				SharedPreferenceUtil.getInstance(activity).putString(SharedPreferenceUtil.OLD_ACCOUNT, cellphoneNumber);
				SharedPreferenceUtil.getInstance(activity).putString(SharedPreferenceUtil.OLD_PASSWORD, password);
				SharedPreferenceUtil.getInstance(activity).putString(SharedPreferenceUtil.USERINFO,
						msg.getMsg().toString());
				startActivity(new Intent(activity, MainActivity.class));
                this.finish();
			} else {
				showToast(msg.getMsg());
				LogUtil.i("=====login erre=====", jsonString.toString());
			}
		}
	}

	@Override
	public void onHttpError(long flag, VolleyError error) {

	}

	@Override
	protected void initUI() {
		iv_login_pic.setOnClickListener(this);
		tv_forgot_password.setOnClickListener(this);
		btn_lg.setOnClickListener(this);
		tv_reg.setOnClickListener(this);
	}

	@Override
	protected void initData() {
		et_cellphone.setText(SharedPreferenceUtil.getInstance(activity).getString(SharedPreferenceUtil.OLD_ACCOUNT));
	}

	@Override
	public String setTag() {
		return this.getClass().getSimpleName();
	}

}
