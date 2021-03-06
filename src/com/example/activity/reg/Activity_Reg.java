package com.example.activity.reg;

import org.json.JSONObject;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.example.activity.common.BaseActivity;
import com.example.activity.common.KeyGuardActivityManager;
import com.example.keyguard.R;
import com.example.util.PublicUtil;
import com.example.util.StringUtils;
import com.example.util.UIHelper;
import com.example.util.YouMengUtil;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

/**
 * 注册页面1 Created by LiesLee on 2014/12/28. Email: LiesLee@foxmail.com
 */
public class Activity_Reg extends BaseActivity implements View.OnClickListener {
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
	/** 手机号 **/
	String cellphoneNumber;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_reg);
		ViewUtils.inject(this);
		initUI();
	}

	private long mExitTime;

	@Override
	public boolean dispatchKeyEvent(KeyEvent event) {
		if (event.getKeyCode() == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN
				&& event.getRepeatCount() == 0) {
			if ((System.currentTimeMillis() - mExitTime) > 2000) {
				mExitTime = System.currentTimeMillis();
				PublicUtil.showToast(activity, "再按一次退出");
			} else {
				KeyGuardActivityManager.getInstance().cleanActivity();
			}
			return true;
		}
		return super.dispatchKeyEvent(event);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_reg_next:
			cellphoneNumber = et_reg.getText().toString();
			if (check()) {
				Activity_Reg_Authentication.luanch(this, cellphoneNumber);
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

	/**
	 * @Description 检查电话号码
	 * @return
	 */
	boolean check() {
		if (StringUtils.isEmpty(cellphoneNumber)) {
			UIHelper.showShakeAnim(this, et_reg, "手机号码不能为空");
			et_reg.requestFocus();
			return false;
		} else if (!StringUtils.phoneNumberValid(cellphoneNumber)) {
			UIHelper.showShakeAnim(this, et_reg, "请输入正确手机号码");
			et_reg.requestFocus();
			return false;
		}
		return true;
	}

	@Override
	public <T> void onHttpSuccess(long flag, JSONObject jsonString, T response) {
	}

	@Override
	public void onHttpError(long flag, VolleyError error) {
	}

	@Override
	protected void initUI() {
		btn_reg_next.setOnClickListener(this);
		but_reg_login.setOnClickListener(this);
		iv_back_left.setVisibility(View.GONE);
	}

	@Override
	protected void initData() {

	}

	@Override
	public String setTag() {
		return this.getClass().getSimpleName();
	}

}
