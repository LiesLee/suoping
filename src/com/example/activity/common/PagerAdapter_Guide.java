package com.example.activity.common;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

/**
 * @Description 引导页适配器
 * @author Created by qinxianyuzou on 2014-12-26.
 */
public class PagerAdapter_Guide extends PagerAdapter {
	private Context mContext;
	private List<View> mListViews = new ArrayList<View>();

	public PagerAdapter_Guide(Context mContext) {
		this.mContext = mContext;
	}

	public void setShowView(List<View> data) {
		mListViews = data;
		notifyDataSetChanged();
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mListViews.size();
	}

	@Override
	public void destroyItem(View arg0, int arg1, Object arg2) {
		// TODO 删除页卡
		((ViewPager) arg0).removeView(mListViews.get(arg1));
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		// TODO 这个方法用来实例化页卡
		return arg0 == (arg1);
	}

	@Override
	public Object instantiateItem(View arg0, int arg1) {
		((ViewPager) arg0).addView(mListViews.get(arg1), 0);
		return mListViews.get(arg1);
	}
}
