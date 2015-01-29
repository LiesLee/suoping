package com.example.activity.common;

import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.example.activity.more.Activity_AddressList_Choose;
import com.example.entity.more.Logistics_Entity;
import com.example.http.base.BaseResponse;
import com.example.http.base.Code;
import com.example.http.base.Protocol;
import com.example.keyguard.R;
import com.example.util.PublicUtil;
import com.example.util.SharedPreferenceUtil;
import com.example.util.StringUtils;
import com.example.util.UIHelper;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

/**
 * @Description 单内容提交
 * @author Created by qinxianyuzou on 2014-12-29.
 */
public class Activity_Submit extends BaseActivity {
	private static int mShopType;
	/** 标题栏 */
	@ViewInject(R.id.tv_public_top_title)
	private TextView tv_public_top_title;
	/** 返回按钮 */
	@ViewInject(R.id.rl_public_back)
	private RelativeLayout rl_public_back;
	/** 单价 */
	@ViewInject(R.id.tv_submit_order_danjia)
	private TextView tv_submit_order_danjia;
	/** 总价 */
	@ViewInject(R.id.tv_submit_order_1)
	private TextView tv_submit_order_1;
	/** 余额 */
	@ViewInject(R.id.tv_submit_order_2)
	private TextView tv_submit_order_2;
	/** 提交按钮 */
	@ViewInject(R.id.but_submit_order_submit)
	private Button but_submit_order_submit;
	private static String title = "";
	private static String mId = "";
	/** 单价 */
	private static String mDanJia = "";
	private long exchange_productFalg;
	/** 支付宝号 **/
	@ViewInject(R.id.v_line_alipay)
	private View v_line_alipay;
	@ViewInject(R.id.ll_aliplay)
	private LinearLayout ll_aliplay;
	@ViewInject(R.id.et_aliplay)
	private EditText et_aliplay;
	/** 确认支付宝号 **/
	@ViewInject(R.id.v_line_alipay2)
	private View v_line_alipay2;
	@ViewInject(R.id.ll_aliplay2)
	private LinearLayout ll_aliplay2;
	@ViewInject(R.id.et_aliplay2)
	private EditText et_aliplay2;

	/** 手机号 **/
	@ViewInject(R.id.v_line_phone)
	private View v_line_phone;
	@ViewInject(R.id.ll_phone)
	private LinearLayout ll_phone;
	@ViewInject(R.id.et_phone)
	private EditText et_phone;

	/** 确认手机号 **/
	@ViewInject(R.id.v_line_phone2)
	private View v_line_phone2;
	@ViewInject(R.id.ll_phone2)
	private LinearLayout ll_phone2;
	@ViewInject(R.id.et_phone2)
	private EditText et_phone2;

	/** 收货地址 **/
	@ViewInject(R.id.v_line_address)
	private View v_line_address;
	@ViewInject(R.id.ll_address)
	private LinearLayout ll_address;
	@ViewInject(R.id.tv_address)
	private TextView tv_address;

	private Logistics_Entity address;

	public static void luanch(Activity activity, String title, String danjia, String id, int shopType) {
		Activity_Submit.title = title;
		mShopType = shopType;
		mId = id;
		mDanJia = danjia;
		Intent intent = new Intent(activity, Activity_Submit.class);
		KeyGuardActivityManager.getInstance().goFoResult(activity, intent, KeyGuardActivityManager.MAIN_CODE);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_submit_order);
		ViewUtils.inject(activity);
		initUI();
		initData();
	}

	@Override
	protected void initUI() {
		// TODO Auto-generated method stub
		tv_public_top_title.setText(title);
		rl_public_back.setVisibility(View.VISIBLE);
		rl_public_back.setOnClickListener(this);
		but_submit_order_submit.setOnClickListener(this);
		tv_submit_order_danjia.setText(mDanJia);
		tv_submit_order_1.setText(mDanJia);
		tv_submit_order_2.setText("" + PublicUtil.getUserInfo(activity).getSum_earn());
		switch (mShopType) {
		case 1: // 不显示手机、支付宝、收货地址
			v_line_address.setVisibility(View.GONE);
			ll_address.setVisibility(View.GONE);

			v_line_alipay.setVisibility(View.GONE);
			ll_aliplay.setVisibility(View.GONE);
			v_line_alipay2.setVisibility(View.GONE);
			ll_aliplay2.setVisibility(View.GONE);

			v_line_phone.setVisibility(View.GONE);
			ll_phone.setVisibility(View.GONE);
			v_line_phone2.setVisibility(View.GONE);
			ll_phone2.setVisibility(View.GONE);

			break;

		case 2: // 只显示手机号
			v_line_address.setVisibility(View.GONE);
			ll_address.setVisibility(View.GONE);

			v_line_alipay.setVisibility(View.GONE);
			ll_aliplay.setVisibility(View.GONE);
			v_line_alipay2.setVisibility(View.GONE);
			ll_aliplay2.setVisibility(View.GONE);

			v_line_phone.setVisibility(View.VISIBLE);
			ll_phone.setVisibility(View.VISIBLE);
			v_line_phone2.setVisibility(View.VISIBLE);
			ll_phone2.setVisibility(View.VISIBLE);

			break;

		case 3: // 只显示支付宝
			v_line_address.setVisibility(View.GONE);
			ll_address.setVisibility(View.GONE);

			v_line_alipay.setVisibility(View.VISIBLE);
			ll_aliplay.setVisibility(View.VISIBLE);
			v_line_alipay2.setVisibility(View.VISIBLE);
			ll_aliplay2.setVisibility(View.VISIBLE);

			v_line_phone.setVisibility(View.GONE);
			ll_phone.setVisibility(View.GONE);
			v_line_phone2.setVisibility(View.GONE);
			ll_phone2.setVisibility(View.GONE);

			break;

		case 4: // 只显示收货地址
			v_line_address.setVisibility(View.VISIBLE);
			ll_address.setVisibility(View.VISIBLE);

			v_line_alipay.setVisibility(View.GONE);
			ll_aliplay.setVisibility(View.GONE);
			v_line_alipay2.setVisibility(View.GONE);
			ll_aliplay2.setVisibility(View.GONE);

			v_line_phone.setVisibility(View.GONE);
			ll_phone.setVisibility(View.GONE);
			v_line_phone2.setVisibility(View.GONE);
			ll_phone2.setVisibility(View.GONE);

			break;

		default:
			break;
		}

		ll_address.setOnClickListener(this);

	}

	@Override
	protected void initData() {
		// TODO Auto-generated method stub

	}

	@Override
	public <T> void onHttpSuccess(long flag, JSONObject jsonString, T response) {
		// TODO Auto-generated method stub
		if (flag == exchange_productFalg) {
			UIHelper.cancelProgressDialog();
			BaseResponse baseResponse = (BaseResponse) response;
			if (baseResponse.getCode().equals(Code.CODE_SUCCESS)) {
				showToast(baseResponse.getMsg());
				finish();
			} else {
				showToast(baseResponse.getMsg());
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
		return Activity_Submit.class.getSimpleName();
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.rl_public_back:
			finish();
			break;
		case R.id.but_submit_order_submit:
			submitOrder(mShopType);
			break;
		// 选择地址
		case R.id.ll_address:
			Activity_AddressList_Choose.luanch(activity, "选择地址");
			break;

		default:
			break;
		}
	}

	/**
	 * 提交订单
	 * 
	 * @param type
	 *            类型
	 */
	public void submitOrder(int type) {
		switch (type) {
		case 1:
			UIHelper.showMsgProgressDialog(activity, "正在加载...");
			exchange_productFalg = Protocol.exchange_product(activity, setTag(), mId, null, null, null, null);
			break;
		case 2:
			if (StringUtils.isEmpty(et_phone.getText().toString())
					&& StringUtils.isEmpty(et_phone2.getText().toString())) {
				UIHelper.showShakeAnim(this, et_phone2, "手机号码不能为空");
				UIHelper.showShakeAnim(this, et_phone, "手机号码不能为空");

			} else {
				if (!StringUtils.phoneNumberValid(et_phone.getText().toString())
						&& !StringUtils.phoneNumberValid(et_phone2.getText().toString())) {
					UIHelper.showShakeAnim(this, et_phone, "请输入正确手机号码");
					UIHelper.showShakeAnim(this, et_phone2, "请输入正确手机号码");
				} else {
					if (et_phone2.getText().toString().equals(et_phone.getText().toString())) {
						UIHelper.showMsgProgressDialog(activity, "正在加载...");
						exchange_productFalg = Protocol.exchange_product(activity, setTag(), mId, null, null, et_phone2
								.getText().toString(), null);
					} else {
						showToast("确认手机号码不一致");
					}
				}
			}
			break;
		case 3:
			if (StringUtils.isEmpty(et_aliplay.getText().toString())
					&& StringUtils.isEmpty(et_aliplay2.getText().toString())) {
				UIHelper.showShakeAnim(this, et_aliplay, "支付宝账号不能为空");
				UIHelper.showShakeAnim(this, et_aliplay2, "支付宝名字不能为空");
			} else {
				UIHelper.showMsgProgressDialog(activity, "正在加载...");
				exchange_productFalg = Protocol.exchange_product(activity, setTag(), mId, et_aliplay.getText()
						.toString(), et_aliplay2.getText().toString(), null, null);
			}
			break;
		case 4:
			if (StringUtils.isEmpty(tv_address.getText().toString())) {
				UIHelper.showShakeAnim(this, tv_address, "请选择收货地址");
			} else {
				if (address != null) {
					UIHelper.showMsgProgressDialog(activity, "正在加载...");
					exchange_productFalg = Protocol.exchange_product(activity, setTag(), mId, null, null, null,
							address.getLogistics_id());
				}
			}
			break;
		default:
			break;
		}

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == Activity_AddressList_Choose.OK) {
			address = (Logistics_Entity) data.getSerializableExtra("address");
			if (address != null) {
				tv_address.setText(address.getTo_who() + "   " + address.getTo_phone());
			}
		}
	}
}
