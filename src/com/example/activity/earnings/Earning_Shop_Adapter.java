package com.example.activity.earnings;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidquery.AQuery;
import com.example.activity.shop.Activity_ShopInfoWeb;
import com.example.entity.Exchange_detail;
import com.example.entity.shop.EXProduct_Entity;
import com.example.keyguard.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description 兑换的adapter
 * @author Created by qinxianyuzou on 2014-12-25.
 */
public class Earning_Shop_Adapter extends BaseAdapter {
	private Context mContext;
	private LayoutInflater listContainer;
    AQuery aq;
	/** 数据源 */
	private List<Exchange_detail> listData = new ArrayList<Exchange_detail>();

	public Earning_Shop_Adapter(Context context) {
		mContext = context;
		listContainer = LayoutInflater.from(mContext);
        aq = new AQuery(mContext);
	}

	public void setData(List<Exchange_detail> data) {
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
			convertView = listContainer.inflate(R.layout.item_earning_shop, null);
			//listItemView.iv_item_shop_icon = (ImageView) convertView.findViewById(R.id.iv_item_shop_icon);
			listItemView.tv_item_shop_name = (TextView) convertView.findViewById(R.id.tv_item_shop_name);
			listItemView.tv_item_shop_money = (TextView) convertView.findViewById(R.id.tv_item_shop_money);
			// 设置标记
			convertView.setTag(listItemView);
		} else {
			listItemView = (HolderView) convertView.getTag();
		}

        /*AQuery aq_ = aq.recycle(convertView);*/
		listItemView.tv_item_shop_name.setText(listData.get(position).getEp_name());
		listItemView.tv_item_shop_money.setText(listData.get(position).getEp_jifen()+"");
        //aq_.id(listItemView.iv_item_shop_icon).image(listData.get(position).getHp_url());

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Activity_ShopInfoWeb
                        .luanch((android.app.Activity) mContext, listData.get(position).getEp_name(), listData.get(position).getEpid());
            }
        });
		return convertView;
	}

	private class HolderView {
		//private ImageView iv_item_shop_icon;
		private TextView tv_item_shop_name;
		private TextView tv_item_shop_money;
	}

}
