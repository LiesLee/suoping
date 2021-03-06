package com.example.activity.more;

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
import com.example.activity.common.DialogSex;
import com.example.activity.common.EnumOnlySubmit;
import com.example.activity.reg.LoginActivity;
import com.example.http.base.BaseResponse;
import com.example.http.base.Code;
import com.example.http.base.HttpCallBack;
import com.example.http.base.Protocol;
import com.example.keyguard.R;
import com.example.util.LogUtil;
import com.example.util.PublicUtil;
import com.example.util.SharedPreferenceUtil;
import com.example.util.UIHelper;
import com.example.util.YouMengUtil;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.bitmap.BitmapDisplayConfig;
import com.lidroid.xutils.bitmap.callback.BitmapLoadCallBack;
import com.lidroid.xutils.bitmap.callback.BitmapLoadFrom;
import com.lidroid.xutils.bitmap.callback.DefaultBitmapLoadCallBack;

/**
 * @Description 我的账号的adapter
 * @author Created by qinxianyuzou on 2014-12-25.
 */
public class MyInfo_Adapter extends BaseAdapter {
	private Activity activity;
	private LayoutInflater listContainer;
	/** 数据源 */
	private List<NameValuePair> listData = new ArrayList<NameValuePair>();
	/** 图片加载工具 */
	private BitmapUtils bitmapUtils;
	/** 图片加载回调 */
	private BitmapLoadCallBack<ImageView> bitmapLoadCallBack;
	/** 退出标记 */
	private long logout;

	public MyInfo_Adapter(Activity context, BitmapUtils bitmapUtils) {
		activity = context;
		listContainer = LayoutInflater.from(activity);
		this.bitmapUtils = bitmapUtils;
		bitmapLoadCallBack = new DefaultBitmapLoadCallBack<ImageView>() {

			@Override
			public void onLoadFailed(ImageView container, String uri, Drawable drawable) {
				// TODO Auto-generated method stub
				super.onLoadFailed(container, uri, drawable);
				container.setImageResource(R.drawable.item_icon);
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

	public void setData(List<NameValuePair> data) {
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
			convertView = listContainer.inflate(R.layout.item_myinfo, null);
			listItemView.ll_item_myifno_btn = (LinearLayout) convertView.findViewById(R.id.ll_item_myifno_btn);
			listItemView.tv_item_myifno_name = (TextView) convertView.findViewById(R.id.tv_item_myifno_name);
			listItemView.tv_item_myifno_value = (TextView) convertView.findViewById(R.id.tv_item_myifno_value);
			//
			// 设置标记
			convertView.setTag(listItemView);
		} else {
			listItemView = (HolderView) convertView.getTag();
		}
		listItemView.tv_item_myifno_name.setText(listData.get(position).getName());
		listItemView.tv_item_myifno_value.setText(listData.get(position).getValue());
		listItemView.ll_item_myifno_btn.setOnClickListener(new onclick(position));
		return convertView;
	}

	class onclick implements OnClickListener {
		private int type = 1;

		public onclick(int type) {
			super();
			this.type = type;
		}

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (type) {
			case 0:
				Activity_AddressList.luanch(activity);
				break;
			// case 1:
			// Activity_OnlySubmit.luanch(activity,
			// listData.get(type).getName(), EnumOnlySubmit.INVITE_NO);
			// break;
			case 1:
				DialogSex dialogSex = new DialogSex(activity);
				dialogSex.requestWindowFeature(Window.FEATURE_NO_TITLE);
				dialogSex.show();
				break;
			case 2:
				DialogDate dialogDate = new DialogDate(activity);
				dialogDate.requestWindowFeature(Window.FEATURE_NO_TITLE);
				dialogDate.show();
				break;
			case 3:
				final DialogClick dialogClick1 = new DialogClick(activity);
				dialogClick1.requestWindowFeature(Window.FEATURE_NO_TITLE);
				dialogClick1.show();
				dialogClick1.setContent("温馨提示", "退出登录后，您将不能持续获得锁屏收益，确认退出吗？", "退出登录", "继续赚钱", new OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						dialogClick1.dismiss();
						UIHelper.showMsgProgressDialog(activity, "正在注销，请稍后...");
						Protocol.logout(activity, Activity_MyInfo.class.getSimpleName(), new LogoutCallBack());
					}
				}, new OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						dialogClick1.dismiss();
					}
				});
				break;
			default:
				break;
			}
			// switch (type) {
			// case 0:
			// case 3:
			// Activity_Submit.luanch(activity, listData.get(type).getName(),
			// "");
			// break;
			// case 1:
			// Activity_AddressList.luanch(activity);
			// break;
			// case 2:
			// Activity_MyInfoPhone.luanch(activity,
			// listData.get(type).getName(), "");
			// break;
			// case 4:
			// DialogSex dialogSex = new DialogSex(activity);
			// dialogSex.requestWindowFeature(Window.FEATURE_NO_TITLE);
			// dialogSex.show();
			// case 5:
			// DialogDate dialogDate = new DialogDate(activity);
			// dialogDate.requestWindowFeature(Window.FEATURE_NO_TITLE);
			// dialogDate.show();
			// break;
			// case 6:
			// final DialogClick dialogClick = new DialogClick(activity);
			// dialogClick.requestWindowFeature(Window.FEATURE_NO_TITLE);
			// dialogClick.show();
			// dialogClick.setContent("账户状态", "恭喜您！您的锁屏赚账户很安全，请继续保持！", "我知道了",
			// new OnClickListener() {
			//
			// @Override
			// public void onClick(View v) {
			// // TODO Auto-generated method stub
			// dialogClick.dismiss();
			// }
			// });
			// break;
			// case 7:
			// Activity_MyInfoPassWord.luanch(activity,
			// listData.get(type).getName());
			// break;
			// case 8:
			// final DialogClick dialogClick1 = new DialogClick(activity);
			// dialogClick1.requestWindowFeature(Window.FEATURE_NO_TITLE);
			// dialogClick1.show();
			// dialogClick1.setContent("温馨提示", "退出登录后，您将不能持续获得锁屏收益，确认退出吗？",
			// "退出登录", "继续赚钱", new OnClickListener() {
			//
			// @Override
			// public void onClick(View v) {
			// // TODO Auto-generated method stub
			// dialogClick1.dismiss();
			// Protocol.logout(activity, Activity_MyInfo.class.getSimpleName(),
			// new LogoutCallBack());
			// }
			// }, new OnClickListener() {
			//
			// @Override
			// public void onClick(View v) {
			// // TODO Auto-generated method stub
			// dialogClick1.dismiss();
			// }
			// });
			// break;
			//
			// default:
			// break;
			// }
		}
	}

	class LogoutCallBack implements HttpCallBack {

		@Override
		public <T> void onHttpSuccess(long flag, JSONObject jsonString, T response) {
			// TODO Auto-generated method stub
			UIHelper.cancelProgressDialog();
			BaseResponse msg = (BaseResponse) response;
			LogUtil.d("logout", msg.getMsg());
			// if (msg.getCode().equals(Code.CODE_SUCCESS)) {
			// } else {
			// PublicUtil.showToast(activity, msg.getMsg());
			// }
			PublicUtil.clearLoginMsg(activity);
			YouMengUtil.onEvent(activity, YouMengUtil.OPEN_LOGIN);
			activity.startActivity(new Intent(activity, LoginActivity.class));
			activity.finish();
		}

		@Override
		public void onHttpError(long flag, VolleyError error) {
			// TODO Auto-generated method stub

		}
	}

	private class HolderView {
		private LinearLayout ll_item_myifno_btn;
		private TextView tv_item_myifno_name;
		private TextView tv_item_myifno_value;
	}

}
