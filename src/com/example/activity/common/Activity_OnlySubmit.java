package com.example.activity.common;

import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.example.activity.more.Activity_Address;
import com.example.activity.more.Activity_EditAddress;
import com.example.http.base.Protocol;
import com.example.keyguard.R;
import com.example.util.StringUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

/**
 * @Description 单项提交公共页
 * @author Created by qinxianyuzou on 2014-12-30.
 */
public class Activity_OnlySubmit extends BaseActivity {

	/** 标题栏 */
	@ViewInject(R.id.tv_public_top_title)
	private TextView tv_public_top_title;
	/** 返回按钮 */
	@ViewInject(R.id.rl_public_back)
	private RelativeLayout rl_public_back;
	/** 内容输入框 */
	@ViewInject(R.id.et_nickname_text)
	private EditText et_nickname_text;
	/** 提交按钮 */
	@ViewInject(R.id.but_nickname_submit)
	private Button but_nickname_submit;
	/** 标题 */
	private static String mTitle = "";
	/** 地址要编辑的内容类型 */
	private static int mType;
	private static EnumOnlySubmit mEnumOnlySubmit = null;

	/**
	 * @Description 不设置标题
	 * @author Created by qinxianyuzou on 2014-12-30.
	 * @param activity
	 */
	public static void luanch(Activity activity) {
		Intent intent = new Intent(activity, Activity_OnlySubmit.class);
		KeyGuardActivityManager.getInstance().goFoResult(activity, intent, KeyGuardActivityManager.MAIN_CODE);
	}

	/**
	 * @Description 设置标题
	 * @author Created by qinxianyuzou on 2014-12-30.
	 * @param activity
	 * @param title
	 */
	public static void luanch(Activity activity, String title, EnumOnlySubmit enumOnlySubmit) {
		mTitle = title;
		mEnumOnlySubmit = enumOnlySubmit;
		Intent intent = new Intent(activity, Activity_OnlySubmit.class);
		KeyGuardActivityManager.getInstance().goFoResult(activity, intent, KeyGuardActivityManager.MAIN_CODE);
	}

	public static void luanch(Activity activity, String title, EnumOnlySubmit enumOnlySubmit, int type) {
		mTitle = title;
		mEnumOnlySubmit = enumOnlySubmit;
		mType = type;
		Intent intent = new Intent(activity, Activity_OnlySubmit.class);
		KeyGuardActivityManager.getInstance().goFoResult(activity, intent, KeyGuardActivityManager.MAIN_CODE);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_nickname);
		ViewUtils.inject(activity);
		initUI();
		initData();
	}

	@Override
	protected void initUI() {
		// TODO Auto-generated method stub
		tv_public_top_title.setText(mTitle);
		rl_public_back.setVisibility(View.VISIBLE);
		rl_public_back.setOnClickListener(this);
		but_nickname_submit.setOnClickListener(this);
		if (mEnumOnlySubmit == EnumOnlySubmit.LOGISTICS) {
			if (mType == 3 || mType == 2) {
				et_nickname_text.setInputType(InputType.TYPE_CLASS_NUMBER);
			}
		}
	}

	@Override
	protected void initData() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.rl_public_back:
			finish();
			break;
		case R.id.but_nickname_submit:
			switch (mEnumOnlySubmit) {
			case INVITE_NO:
				if (StringUtils.isEmpty(et_nickname_text.getText().toString())) {
					showToast("请填写邀请码！");
					return;
				}
				Protocol.invite(activity, setTag(), et_nickname_text.getText().toString());
				break;
			case LOGISTICS:
				switch (mType) {
				case 0:
					Activity_Address.logistics_Entity.setTo_where(et_nickname_text.getText().toString());
					break;
				case 1:
					Activity_Address.logistics_Entity.setTo_who(et_nickname_text.getText().toString());
					break;
				case 2:
					Activity_Address.logistics_Entity.setTo_phone(et_nickname_text.getText().toString());
					break;
				case 3:
					Activity_Address.logistics_Entity.setPost_no(et_nickname_text.getText().toString());
					break;

				default:
					break;
				}
				finish();
				break;
			case EDIT_LOGISTICS:
				switch (mType) {
				case 0:
					Activity_EditAddress.logistics_Entity.setTo_where(et_nickname_text.getText().toString());
					break;
				case 1:
					Activity_EditAddress.logistics_Entity.setTo_who(et_nickname_text.getText().toString());
					break;
				case 2:
					Activity_EditAddress.logistics_Entity.setTo_phone(et_nickname_text.getText().toString());
					break;
				case 3:
					Activity_EditAddress.logistics_Entity.setPost_no(et_nickname_text.getText().toString());
					break;

				default:
					break;
				}
				finish();
				break;

			default:
				break;
			}
			break;

		default:
			break;
		}
	}

	@Override
	public <T> void onHttpSuccess(long flag, JSONObject jsonString, T response) {
		// TODO Auto-generated method stub

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
