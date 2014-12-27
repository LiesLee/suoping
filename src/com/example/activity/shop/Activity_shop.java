package com.example.activity.shop;

import java.util.ArrayList;
import java.util.List;

import com.android.volley.VolleyError;
import com.example.activity.common.BaseActivity;
import com.example.activity.common.ShopInterface;
import com.example.fragments.Fragment_shop;
import com.example.keyguard.R;
import com.example.ui.astuetz.PagerSlidingTabStrip;
import com.example.util.UIHelper;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import android.annotation.TargetApi;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONObject;

/**
 * @Description 兑换
 * @author Created by qinxianyuzou on 2014-12-25.
 */
public class Activity_shop extends BaseActivity implements ShopInterface{
	/** 标题栏 */
	@ViewInject(R.id.tv_public_top_title)
	private TextView tv_public_top_title;
	/** 列表 */
/*	@ViewInject(R.id.lv_shop_body)
	private ListView lv_shop_body;*/
    /** ViewPager **/
    @ViewInject(R.id.shop_pager)
    private ViewPager shop_pager;
    /** taps **/
    @ViewInject(R.id.shop_tabs)
    private PagerSlidingTabStrip shop_tabs;

	private Shop_Adapter adapter;
	/** 图片加载工具 */
	private BitmapUtils bitmapUtils;
	/** 保存数据源 */
	private List<String> dataList = new ArrayList<String>();

    private SectionsPagerAdapter mSectionsPagerAdapter;
    private LinearLayout mTabsLinearLayout;

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

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        shop_pager.setAdapter(mSectionsPagerAdapter);
        shop_tabs.setAllCaps(false);
        shop_tabs.setShouldExpand(true);
        //字体大小
        shop_tabs.setTextSize(UIHelper.sp2px(this, 16));
//        shop_tabs.setUnderlineColor(getResources().getColor(R.color.gray5));
        shop_tabs.setUnderlineHeight(UIHelper.dp2px(this, 1));
        //设置背景
        shop_tabs.setTabBackground(R.drawable.background_tab);
        //设置分割线颜色
        shop_tabs.setDividerColor(getResources().getColor(android.R.color.transparent));
        //滚动条颜色
        shop_tabs.setIndicatorColorResource(R.color.prevailing_red);
        //滚动条大小
        shop_tabs.setIndicatorHeight(UIHelper.dp2px(this, 4));
        //Viewpager监听器
        shop_tabs.setOnPageChangeListener(mTabsOnPageChangeListener);
        //设置Viewpager
        shop_tabs.setViewPager(shop_pager);

        // Set first tab selected 设置第一个选中项的内容
        mTabsLinearLayout = ((LinearLayout)shop_tabs.getChildAt(0));

        for(int i=0; i < mTabsLinearLayout.getChildCount(); i++){
            TextView tv = (TextView) mTabsLinearLayout.getChildAt(i);

            if(i == 0){
                tv.setTextColor(getResources().getColor(R.color.black));
            } else {
                tv.setTextColor(getResources().getColor(R.color.text_tab));
            }
        }
        
		/*bitmapUtils = new BitmapUtils(activity);
		adapter = new Shop_Adapter(activity, bitmapUtils);*/
/*		// 滑动时停止加载图片
		lv_shop_body.setOnScrollListener(new PauseOnScrollListener(bitmapUtils, false, true));
		lv_shop_body.setAdapter(adapter);*/


	}

	@Override
	protected void initData() {
		/*for (int i = 0; i < 20; i++) {
			dataList.add("");
		}
		adapter.setData(dataList);*/
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

    @Override
    public PagerSlidingTabStrip getTabs() {
        return shop_tabs;
    }

    /**
     * A {@link android.support.v4.app.FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return Fragment_shop.newInstance("1");
                case 1:
                    return Fragment_shop.newInstance("2");
                case 2:
                    return Fragment_shop.newInstance("3");
                default:
                    return Fragment_shop.newInstance("1");
            }
        }

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return getString(R.string.virtual_goods);
                case 1:
                    return getString(R.string.entity_goods);
                case 2:
                    return getString(R.string.withdraw_cash);
            }
            return null;
        }
    }

    // region Listeners
    private ViewPager.OnPageChangeListener mTabsOnPageChangeListener = new ViewPager.OnPageChangeListener() {
        @TargetApi(Build.VERSION_CODES.HONEYCOMB)
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
                shop_tabs.setTranslationY(0);
            }
        }

        @Override
        public void onPageSelected(int position) {
            for(int i=0; i < mTabsLinearLayout.getChildCount(); i++){
                TextView tv = (TextView) mTabsLinearLayout.getChildAt(i);
                if(i == position){
                    tv.setTextColor(getResources().getColor(R.color.black));
                } else {
                    tv.setTextColor(getResources().getColor(R.color.text_tab));
                }
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {
        }
    };

}
