package com.example.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.example.activity.common.BaseActivity;
import com.example.activity.common.KeyGuardApplication;
import com.example.http.ConnectorManage;
import com.example.http.HttpCallBack;
import com.example.http.RequestManager;
import com.example.util.StringUtils;
import com.example.util.UIHelper;

import org.json.JSONObject;

public abstract class BaseFragment extends Fragment implements HttpCallBack {
	public KeyGuardApplication application;
	public BaseActivity activity;
	protected ConnectorManage connectorManage;
	protected String Tag = StringUtils.EMPTY;
	private Toast toast = null;
	public int pageNo = 1;
	public int pageSize = 10;
	public boolean isfrist = true;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		activity = (BaseActivity)getActivity();
		application = (KeyGuardApplication) getActivity().getApplication();
		connectorManage = ConnectorManage.getInstance(activity);
		setTag();
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		initUI();
        initData();
		isfrist = false;
	}

	@Override
	public void onResume() {
		super.onResume();
		connectorManage.addFragmentCallBack(this);
	}

	// Fragment不一样
	@Override
	public void onDestroy() {
		super.onDestroy();
		connectorManage.removeFragmentCallBack(this);
		RequestManager.getInstance(activity).getmRequestQueue().cancelAll(Tag);
	}

	public abstract void initUI();

	public abstract void initData();

	public abstract String setTag();

	@Override
	public <T> void onHttpSuccess(long flag, JSONObject json, T response) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onHttpError(long flag, VolleyError e) {
		// TODO Auto-generated method stub

	}


	/**
	 * 默认时间LENGTH_LONG
	 */
	public void showToast(String msg) {
		UIHelper.showToast(activity, toast, msg);
	}

	/**
	 * @param msg
	 * @param length
	 *            显示时间
	 */
	protected void showToast(String msg, int length) {
		UIHelper.showToast(activity, toast, msg);
	}

}
