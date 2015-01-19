package com.example.activity.shop;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidquery.AQuery;
import com.example.entity.shop.EXProduct_Entity;
import com.example.keyguard.R;
import com.example.util.LogUtil;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.bitmap.BitmapDisplayConfig;
import com.lidroid.xutils.bitmap.callback.BitmapLoadCallBack;
import com.lidroid.xutils.bitmap.callback.BitmapLoadFrom;
import com.lidroid.xutils.bitmap.callback.DefaultBitmapLoadCallBack;

/**
 * @Description 兑换的adapter
 * @author Created by qinxianyuzou on 2014-12-25.
 */
public class Shop_Adapter extends BaseAdapter {
	private Context mContext;
	private LayoutInflater listContainer;
	AQuery aq;
	/** 数据源 */
	private List<EXProduct_Entity> listData = new ArrayList<EXProduct_Entity>();

	public Shop_Adapter(Context context) {
		mContext = context;
		listContainer = LayoutInflater.from(mContext);
		aq = new AQuery(mContext);
	}

	public void setData(List<EXProduct_Entity> data) {
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
	public View getView(final int position, View convertView, ViewGroup parent) {
		// 实例化viewholader
		HolderView listItemView;

		if (convertView == null) {
			listItemView = new HolderView();
			convertView = listContainer.inflate(R.layout.item_shop, null);
			listItemView.iv_item_shop_icon = (ImageView) convertView.findViewById(R.id.iv_item_shop_icon);
			listItemView.tv_item_shop_name = (TextView) convertView.findViewById(R.id.tv_item_shop_name);
			listItemView.tv_item_shop_money = (TextView) convertView.findViewById(R.id.tv_item_shop_money);
			// 设置标记
			convertView.setTag(listItemView);
		} else {
			listItemView = (HolderView) convertView.getTag();
		}

		AQuery aq_ = aq.recycle(convertView);
		listItemView.tv_item_shop_name.setText(listData.get(position).getTitle());
		listItemView.tv_item_shop_money.setText("所需积分：" + listData.get(position).getNeed_jifen());
		aq_.id(listItemView.iv_item_shop_icon).image(listData.get(position).getHp_url());

		convertView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Activity_ShopInfoWeb.luanch((android.app.Activity) mContext, listData.get(position).getTitle(),
						listData.get(position).getNeed_jifen(), listData.get(position).getEp_id(), 0);
			}
		});
		return convertView;
	}

	private class HolderView {
		private ImageView iv_item_shop_icon;
		private TextView tv_item_shop_name;
		private TextView tv_item_shop_money;
	}

}
