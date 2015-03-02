package com.example.fragments;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

import com.android.volley.VolleyError;
import com.example.activity.common.ShopInterface;
import com.example.activity.common.UpDate_Interface;
import com.example.activity.shop.Activity_ShopInfo;
import com.example.activity.shop.Activity_ShopInfoWeb;
import com.example.activity.shop.Shop_Adapter;
import com.example.entity.shop.EXProduct_Entity;
import com.example.http.base.Code;
import com.example.http.base.Config;
import com.example.http.base.Protocol;
import com.example.http.respose.ResponseEXProduct;
import com.example.keyguard.R;
import com.example.ui.astuetz.PagerSlidingTabStrip;
import com.example.ui.pull.RefleshListView;
import com.example.util.LogUtil;
import com.example.util.UIHelper;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.bitmap.PauseOnScrollListener;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.umeng.analytics.MobclickAgent;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

/**
 * A simple {@link Fragment} subclass. Activities that contain this fragment
 * must implement the {@link Fragment_Shop_Cash.OnFragmentInteractionListener}
 * interface to handle interaction events. Use the
 * {@link Fragment_Shop_Cash#newInstance} factory method to create an instance
 * of this fragment.
 */
public class Fragment_Shop_Cash extends BaseFragment implements UpDate_Interface {
	private static final String SHOP_TYPE = "shop_type";

	private String shop_type;
	@ViewInject(R.id.rListView)
	private RefleshListView rListView;

	private ShopInterface shopCoordinator;
	// private BitmapUtils bitmapUtils;
	private Shop_Adapter adapter;
	/** 保存数据源 */
	private ArrayList<EXProduct_Entity> dataList = new ArrayList<EXProduct_Entity>();
	// private static String ptype = "";
	/** 列表接口标识 */
	private long exproductFlag;
	public static UpDate_Interface upDate_Interface;

	public static Fragment_Shop_Cash newInstance(String shop_type) {
		Fragment_Shop_Cash fragment = new Fragment_Shop_Cash();
		Bundle args = new Bundle();
		args.putString(SHOP_TYPE, shop_type);
		// ptype = shop_type;
		fragment.setArguments(args);
		return fragment;
	}

	public Fragment_Shop_Cash() {
		// Required empty public constructor
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (getArguments() != null) {
			shop_type = getArguments().getString(SHOP_TYPE);
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_shop, container, false);
		ViewUtils.inject(this, view);
		return view;
	}

	@Override
	public void initUI() {
		upDate_Interface=this;
		adapter = new Shop_Adapter(activity, shop_type);
		rListView.setAdapter(adapter);
	}

	@Override
	public void initData() {
		exproductFlag = Protocol.get_ex_product(activity, setTag(), shop_type);
	}

	@Override
	public String setTag() {
		return Fragment_Shop_Cash.class.getSimpleName();
	}

	@Override
	public <T> void onHttpSuccess(long flag, JSONObject json, T response) {
		super.onHttpSuccess(flag, json, response);
		if (flag == exproductFlag) {
			ResponseEXProduct responseEXProduct = (ResponseEXProduct) response;
			if (responseEXProduct.getCode().equals(Code.CODE_SUCCESS)) {
				dataList = responseEXProduct.getData();
				adapter.setData(dataList);
			}
		}
	}

	@Override
	public void onHttpError(long flag, VolleyError e) {
		super.onHttpError(flag, e);
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		try {
			shopCoordinator = (ShopInterface) activity;
		} catch (ClassCastException e) {
			throw new ClassCastException(activity.toString() + " must implement OnFragmentInteractionListener");
		}
	}

	@Override
	public void onDetach() {
		super.onDetach();
		shopCoordinator = null;
	}

	/**
	 * This interface must be implemented by activities that contain this
	 * fragment to allow an interaction in this fragment to be communicated to
	 * the activity and potentially other fragments contained in that activity.
	 * <p/>
	 * See the Android Training lesson <a href=
	 * "http://developer.android.com/training/basics/fragments/communicating.html"
	 * >Communicating with Other Fragments</a> for more information.
	 */
	public interface OnFragmentInteractionListener {
		public void onFragmentInteraction(Uri uri);
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		initData();
	}

}
