package com.example.activity.reg;

import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.example.activity.common.BaseActivity;
import com.example.activity.common.KeyGuardActivityManager;
import com.example.activity.main.MainActivity;
import com.example.http.base.BaseResponse;
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

/**
 * Created by LiesLee on 2014/12/28. Email: LiesLee@foxmail.com
 */
public class Activity_Reg_Password extends BaseActivity implements View.OnClickListener {

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

	private String cellphoneNumber;
	private String code;
	private String password;

	private static final String CELLPHOME_NUMBER = "cellphone_Number";
	private static final String CODE = "code";
	private long regFlag;
	/** 登录回调 */
	private long loginFlag;

	public static void luanch(Activity activity, String cellphoneNumber, String code) {
		Intent intent = new Intent(activity, Activity_Reg_Password.class);
		intent.putExtra(CELLPHOME_NUMBER, cellphoneNumber);
		intent.putExtra(CODE, code);
		KeyGuardActivityManager.getInstance().goTo(activity, intent);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_reg);
		ViewUtils.inject(this);
		initUI();
	}

	@Override
	protected void initUI() {
		cellphoneNumber = getIntent().getStringExtra(CELLPHOME_NUMBER);
		code = getIntent().getStringExtra(CODE);
		tv_reg_head.setText("填写密码");
		tv_input_tips.setText("填写密码：");
		btn_reg_next.setText("完成");
		et_reg.setHint("");
		iv_back_left.setOnClickListener(this);
		btn_reg_next.setOnClickListener(this);
		et_reg.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
	}

	@Override
	protected void initData() {

	}

	@Override
	public String setTag() {
		return this.getClass().getSimpleName();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_reg_next:
			if (check()) {
				regFlag = Protocol.register(this, setTag(), cellphoneNumber, code, password);
				UIHelper.showMsgProgressDialog(this, "正在提交...");
			}
			break;
		case R.id.iv_back_left:
			this.finish();
		default:
			break;
		}
	}

	boolean check() {
		if (StringUtils.isEmpty(et_reg.getText().toString()) || et_reg.getText().toString().length() < 6) {
			UIHelper.showShakeAnim(this, et_reg, "请输入6位以上密码");
			return false;
		}
		password = et_reg.getText().toString();
		return true;
	}

	@Override
	public <T> void onHttpSuccess(long flag, JSONObject jsonString, T response) {
		if (flag == regFlag) {
			BaseResponse msg = (BaseResponse) response;
			if (msg.getCode().equals(Code.CODE_SUCCESS)) {
				showToast(msg.getMsg() + "正在登录，请稍候...");
				loginFlag = Protocol.login(this, setTag(), cellphoneNumber, password);
			} else {
				showToast(msg.getMsg());
			}
		}
		if (flag == loginFlag) {
			UIHelper.cancelProgressDialog();
			ResponseUserInfo msg = (ResponseUserInfo) response;
			if (msg.getCode().equals(Code.CODE_SUCCESS)) {
				showToast(msg.getMsg());
				SharedPreferenceUtil.getInstance(activity).putString(SharedPreferenceUtil.OLD_ACCOUNT, cellphoneNumber);
				SharedPreferenceUtil.getInstance(activity).putString(SharedPreferenceUtil.OLD_PASSWORD, password);
				SharedPreferenceUtil.getInstance(activity).putString(SharedPreferenceUtil.USERINFO,
						msg.getMsg().toString());
				startActivity(new Intent(activity, MainActivity.class));
			} else {
				showToast(msg.getMsg());
				startActivity(new Intent(this, LoginActivity.class));
			}
			finish();
		}
	}

	@Override
	public void onHttpError(long flag, VolleyError error) {
		if (flag == regFlag) {
			UIHelper.cancelProgressDialog();
			LogUtil.d("onHttpError", error.toString());
			showToast(StringUtils.ERROR_TOAST);
		}
	}

}
