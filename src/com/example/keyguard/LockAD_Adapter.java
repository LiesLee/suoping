package com.example.keyguard;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.example.activity.common.Activity_OnlySubmit;
import com.example.activity.common.DialogClick;
import com.example.activity.common.EnumOnlySubmit;
import com.example.activity.reg.LoginActivity;
import com.example.entity.LockADList_Entity;
import com.example.entity.respose.BaseResponse;
import com.example.entity.respose.Code;
import com.example.http.HttpCallBack;
import com.example.http.Protocol;
import com.example.keyguard.R;
import com.example.util.PublicUtil;
import com.example.util.SharedPreferenceUtil;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.bitmap.BitmapDisplayConfig;
import com.lidroid.xutils.bitmap.callback.BitmapLoadCallBack;
import com.lidroid.xutils.bitmap.callback.BitmapLoadFrom;
import com.lidroid.xutils.bitmap.callback.DefaultBitmapLoadCallBack;

/**
 * @Description 我的账号的adapter
 * @author Created by qinxianyuzou on 2014-12-25.
 */
public class LockAD_Adapter extends BaseAdapter {
	private Activity activity;
	private LayoutInflater listContainer;
	/** 数据源 */
	private List<LockADList_Entity> listData = new ArrayList<>();
	/** 图片加载工具 */
	private BitmapUtils bitmapUtils;
	/** 图片加载回调 */
	private BitmapLoadCallBack<ImageView> bitmapLoadCallBack;
	/** 退出标记 */
	private long logout;

	public LockAD_Adapter(Activity context, BitmapUtils bitmapUtils) {
		activity = context;
		listContainer = LayoutInflater.from(activity);
		this.bitmapUtils = bitmapUtils;
		bitmapLoadCallBack = new DefaultBitmapLoadCallBack<ImageView>() {

			@Override
			public void onLoadFailed(ImageView container, String uri, Drawable drawable) {
				// TODO Auto-generated method stub
				super.onLoadFailed(container, uri, drawable);
				container.setImageResource(R.drawable.launch_bg);
			}

			@Override
			public void onLoadCompleted(ImageView container, String uri, Bitmap bitmap, BitmapDisplayConfig config,
					BitmapLoadFrom from) {
				// TODO Auto-generated method stub
				super.onLoadCompleted(container, uri, bitmap, config, from);
			}
		};
		// ViewUtils.inject((Activity) mContext);
	}

	public void setData(List<LockADList_Entity> data) {
		listData = data;
		notifyDataSetChanged();
	}

	@Override
	public int getCount() {
		return listData.size();
		// return 15;
	}

	@Override
	public Object getItem(int position) {
		return listData.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// 实例化viewholader
		final HolderView listItemView;

		if (convertView == null) {
			listItemView = new HolderView();
			//
			convertView = listContainer.inflate(R.layout.activity_lock_img, null);
			listItemView.iv_lock_img = (ImageView) convertView.findViewById(R.id.iv_lock_img);
			//
			// 设置标记
			convertView.setTag(listItemView);
		} else {
			listItemView = (HolderView) convertView.getTag();
		}
		bitmapUtils.display(listItemView.iv_lock_img, listData.get(position).getHp_url(), bitmapLoadCallBack);
		return convertView;
	}

	private class HolderView {
		private ImageView iv_lock_img;
	}

}
