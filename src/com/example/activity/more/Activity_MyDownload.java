package com.example.activity.more;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.example.activity.common.BaseActivity;
import com.example.activity.common.KeyGuardActivityManager;
import com.example.activity.common.ShopInterface;
import com.example.fragments.Fragment_MyDownload;
import com.example.fragments.Fragment_shop;
import com.example.keyguard.R;
import com.example.ui.astuetz.PagerSlidingTabStrip;
import com.example.util.UIHelper;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import org.json.JSONObject;

public class Activity_MyDownload extends BaseActivity implements ShopInterface {

    /** 标题栏 */
    @ViewInject(R.id.tv_public_top_title)
    private TextView tv_public_top_title;

    /** ViewPager **/
    @ViewInject(R.id.my_download_pager)
    private ViewPager my_download_pager;
    /** taps **/
    @ViewInject(R.id.my_download_tabs)
    private PagerSlidingTabStrip my_download_tabs;

    private SectionsPagerAdapter mSectionsPagerAdapter;
    private LinearLayout mTabsLinearLayout;

    public static void luanch(Activity act){
        Intent intent = new Intent(act, Activity_MyDownload.class);
        KeyGuardActivityManager.getInstance().goTo(act, intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_download);
        ViewUtils.inject(this);
        initUI();
        initData();
    }

    @Override
    protected void initUI() {
        tv_public_top_title.setText("我的下载");

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        my_download_pager.setAdapter(mSectionsPagerAdapter);
        my_download_tabs.setAllCaps(false);
        my_download_tabs.setShouldExpand(true);
        //字体大小
        my_download_tabs.setTextSize(UIHelper.sp2px(this, 16));
//        my_download_tabs.setUnderlineColor(getResources().getColor(R.color.gray5));
        my_download_tabs.setUnderlineHeight(UIHelper.dp2px(this, 1));
        //设置背景
        my_download_tabs.setTabBackground(R.drawable.background_tab);
        //设置分割线颜色
        my_download_tabs.setDividerColor(getResources().getColor(android.R.color.transparent));
        //滚动条颜色
        my_download_tabs.setIndicatorColorResource(R.color.prevailing_red);
        //滚动条大小
        my_download_tabs.setIndicatorHeight(UIHelper.dp2px(this, 4));
        //Viewpager监听器
        my_download_tabs.setOnPageChangeListener(mTabsOnPageChangeListener);
        //设置Viewpager
        my_download_tabs.setViewPager(my_download_pager);

        // Set first tab selected 设置第一个选中项的内容
        mTabsLinearLayout = ((LinearLayout)my_download_tabs.getChildAt(0));

        for(int i=0; i < mTabsLinearLayout.getChildCount(); i++){
            TextView tv = (TextView) mTabsLinearLayout.getChildAt(i);

            if(i == 0){
                tv.setTextColor(getResources().getColor(R.color.black));
            } else {
                tv.setTextColor(getResources().getColor(R.color.text_tab));
            }
        }


    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public <T> void onHttpSuccess(long flag, JSONObject jsonString, T response) {

    }

    @Override
    public void onHttpError(long flag, VolleyError error) {

    }

    @Override
    public String setTag() {
        return this.getClass().getSimpleName();
    }

    @Override
    public PagerSlidingTabStrip getTabs() {
        return my_download_tabs;
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return Fragment_MyDownload.newInstance("1");
                case 1:
                    return Fragment_MyDownload.newInstance("2");
                case 2:
                    return Fragment_MyDownload.newInstance("3");
                default:
                    return Fragment_MyDownload.newInstance("1");
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
                    return "下载";
                case 1:
                    return "下载管理";
                case 2:
                    return "安装包管理";
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
                my_download_tabs.setTranslationY(0);
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
