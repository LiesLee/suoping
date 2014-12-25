package com.example.activity.shop;

import java.util.ArrayList;
import java.util.List;

import com.android.volley.VolleyError;
import com.example.activity.common.BaseActivity;
import com.example.keyguard.R;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.bitmap.PauseOnScrollListener;
import com.lidroid.xutils.view.annotation.ViewInject;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONObject;

/**
 * @Description 兑换
 * @author Created by qinxianyuzou on 2014-12-25.
 */
public class Activity_shop extends BaseActivity {
	/** 标题栏 */
	@ViewInject(R.id.tv_public_top_title)
	private TextView tv_public_top_title;
	/** 列表 */
	@ViewInject(R.id.lv_shop_body)
	private ListView lv_shop_body;

	private Shop_Adapter adapter;
	/** 图片加载工具 */
	private BitmapUtils bitmapUtils;
	/** 保存数据源 */
	private List<String> dataList = new ArrayList<String>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tab_shop);
		ViewUtils.inject(this);
		initUI();
		initData();
	}

	@Override
	protected void initUI() {
		tv_public_top_title.setText("兑换");
		bitmapUtils = new BitmapUtils(activity);
		adapter = new Shop_Adapter(activity, bitmapUtils);
		// 滑动时停止加载图片
		lv_shop_body.setOnScrollListener(new PauseOnScrollListener(bitmapUtils, false, true));
		lv_shop_body.setAdapter(adapter);
	}

	@Override
	protected void initData() {
		for (int i = 0; i < 20; i++) {
			dataList.add("");
		}
		adapter.setData(dataList);
	}

	@Override
	public <T> void onHttpSuccess(long flag, JSONObject jsonString, T response) {

	}

	@Override
	public void onHttpError(long flag, VolleyError error) {

	}

	@Override
	public String setTag() {
		return Activity_shop.class.getSimpleName();
	}

}
