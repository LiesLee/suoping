package com.example.keyguard;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.example.ui.VerticalViewPager;

/**
 * @Description 我的账号的adapter
 * @author Created by qinxianyuzou on 2014-12-25.
 */
public class LockAD_PagerAdapter extends PagerAdapter {
	private Activity activity;
	/** 数据源 */
	private List<View> listData = new ArrayList<>();

	public LockAD_PagerAdapter(Activity context) {
		activity = context;
	}

	public void setData(List<View> data) {
		listData = data;
		notifyDataSetChanged();
	}

	@Override
	public int getCount() {
		return listData.size();
	}

	@Override
	public void destroyItem(View arg0, int arg1, Object arg2) {
		// TODO 删除页卡
		((VerticalViewPager) arg0).removeView(listData.get(arg1));
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		// TODO 这个方法用来实例化页卡
		return arg0 == (arg1);
	}

	@Override
	public Object instantiateItem(View arg0, int arg1) {
		((VerticalViewPager) arg0).addView(listData.get(arg1), 0);
		return listData.get(arg1);
	}

}
