package com.example.keyguard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.androidquery.AQuery;
import com.example.activity.common.Activity_DownloadWeb;
import com.example.activity.common.Activity_PublicWeb;
import com.example.activity.common.BaseActivity;
import com.example.activity.earnings.Activity_earnings;
import com.example.entity.LockADList_Entity;
import com.example.http.base.Code;
import com.example.http.base.HttpCallBack;
import com.example.http.base.Protocol;
import com.example.http.respose.ResponseLockADList;
import com.example.keyguard.CoverPlateView.Listener;
import com.example.ui.VerticalViewPager;
import com.example.util.LogUtil;
import com.example.util.PublicUtil;
import com.example.util.SharedPreferenceUtil;
import com.example.util.StringUtils;
import com.example.util.YouMengUtil;
import com.google.gson.Gson;
import com.lidroid.xutils.BitmapUtils;

@SuppressLint("ClickableViewAccessibility")
public class LockActivity extends BaseActivity {

	/** 屏幕宽 */
	private int screenWidth;
	/** 屏幕高 */
	private int screenHeight;
	/**  */
	private int lastX;
	/**  */
	private int lastY;
	/** 滑动组件宽度 */
	private int image_slide_width;
	/** 滑动组件右边距 */
	private int image_slide_rightMargin;
	/** 滑动组件左边距 */
	private int image_slide_leftMargin;
	/** 积分文字底边距 */
	private int image_slide_bottomMargin;
	/** 解锁组件与积分文字边距 */
	private int slideAndTextBottomMargin;

	/** 数据大小 */
	private int listCount;
	/** 当前item */
	private int currentRow;
	/** 解锁图标(圆圈) */
	private ImageView imageView_slide;

	private RelativeLayout.LayoutParams image_slide;

	public static LockActivity instance = null;
	/** 广告图 */
	private UnRollListView mListView;
	private LockAD_PagerAdapter adapter2;
	/** 积分获得的积分数 */
	private TextView tv_appJiFen;
	/** 解锁分数 */
	private TextView tv_jiesuojifen;
	/** 下载的图标 */
	private ImageView imageView_download;
	/** 解锁的图标 */
	private ImageView imageView_normal;
	/** 向下箭头 */
	private ImageView imageView_down;
	/** 向上箭头 */
	private ImageView imageView_up;
	/** 广告 */
	private VerticalViewPager verticalViewPager1;
	private AQuery aQuery;
	private BitmapUtils bitmapUtils;
	private long getearnlistFlag;
	public ArrayList<LockADList_Entity> listDataEntities = new ArrayList<>();
	/** 布局 */
	private RelativeLayout mRelativeLayout;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// mRelativeLayout =
		// LayoutInflater.from(this).inflate(R.layout.activity_lock, null);
		setContentView(R.layout.activity_lock);
		instance = this;
		initUI();
		initData();
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK || keyCode == KeyEvent.KEYCODE_HOME) {
			return true;
		}

		return super.onKeyDown(keyCode, event);
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

	@Override
	protected void initUI() {
		// TODO Auto-generated method stub
		// ExitApplication.getInstance().addActivity(this);
		aQuery = new AQuery(activity);
		mRelativeLayout = (RelativeLayout) findViewById(R.id.rl_lock_parent);

		mRelativeLayout.setBackgroundColor(Color.GRAY);

		DisplayMetrics dm = getResources().getDisplayMetrics();
		screenWidth = dm.widthPixels;
		screenHeight = dm.heightPixels - 50;
		image_slide_width = (int) ((150f / 720f) * screenWidth);
		image_slide_rightMargin = (int) ((60f / 720f) * screenWidth);
		image_slide_leftMargin = (int) ((60f / 720f) * screenWidth);
		image_slide_bottomMargin = (int) ((210f / 1280f) * screenHeight);
		slideAndTextBottomMargin = (int) (image_slide_bottomMargin * 0.75);
		bitmapUtils = new BitmapUtils(activity);
		RelativeLayout.LayoutParams image_Vertical = new RelativeLayout.LayoutParams(screenWidth
				- image_slide_rightMargin - image_slide_leftMargin,
				(screenHeight - (image_slide_bottomMargin - image_slide_width) * 2) * 10);
		image_Vertical.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		image_Vertical.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		image_Vertical.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
		// image_Vertical.bottomMargin = image_slide_bottomMargin -
		// image_slide_width;
		// image_Vertical.topMargin = image_slide_bottomMargin -
		// image_slide_width;

		mListView = (UnRollListView) findViewById(R.id.unRollListView1);

		// 去掉分割黑线
		mListView.setDivider(null);
		listCount = listDataEntities.size();
		currentRow = 0;
		// lv_lock_ad.setAdapter(adapter);
		mListView.setVerticalScrollBarEnabled(false);
		mListView.setSmoothScrollbarEnabled(true);
		// mListView.setAdapter(adapter);
		// mRelativeLayout.addView(mListView, image_Vertical);

		imageView_download = new ImageView(this);
		imageView_download.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
		imageView_download.setImageResource(R.drawable.lock_left_download_icon_normal);
		// RelativeLayout.LayoutParams image_rl1 = new
		// RelativeLayout.LayoutParams(image_slide_width, image_slide_width);
		LayoutParams image_rl1 = new LayoutParams(image_slide_width, image_slide_width);
		image_rl1.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		image_rl1.addRule(RelativeLayout.ALIGN_PARENT_LEFT, RelativeLayout.TRUE);
		image_rl1.bottomMargin = slideAndTextBottomMargin;
		image_rl1.leftMargin = image_slide_leftMargin;
		mRelativeLayout.addView(imageView_download, image_rl1);

		imageView_normal = new ImageView(this);
		imageView_normal.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
		imageView_normal.setImageResource(R.drawable.lock_right_icon_normal);
		RelativeLayout.LayoutParams image_rl2 = new RelativeLayout.LayoutParams(image_slide_width, image_slide_width);
		image_rl2.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		image_rl2.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.TRUE);
		image_rl2.bottomMargin = slideAndTextBottomMargin;
		image_rl2.rightMargin = image_slide_rightMargin;
		mRelativeLayout.addView(imageView_normal, image_rl2);

		tv_appJiFen = new TextView(this);
		tv_appJiFen.setTextSize(30);
		tv_appJiFen.setTextColor(Color.WHITE);
		tv_appJiFen.setGravity(Gravity.CENTER);
		RelativeLayout.LayoutParams image_rl3 = new RelativeLayout.LayoutParams(image_slide_width, image_slide_width);
		image_rl3.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		image_rl3.addRule(RelativeLayout.ALIGN_PARENT_LEFT, RelativeLayout.TRUE);
		image_rl3.bottomMargin = image_slide_bottomMargin - image_slide_width;
		image_rl3.leftMargin = image_slide_leftMargin;
		mRelativeLayout.addView(tv_appJiFen, image_rl3);

		tv_jiesuojifen = new TextView(this);
		tv_jiesuojifen.setText("+2");
		tv_jiesuojifen.setTextSize(30);
		tv_jiesuojifen.setTextColor(Color.WHITE);
		tv_jiesuojifen.setGravity(Gravity.CENTER);
		RelativeLayout.LayoutParams image_rl4 = new RelativeLayout.LayoutParams(image_slide_width, image_slide_width);
		image_rl4.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		image_rl4.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.TRUE);
		image_rl4.bottomMargin = image_slide_bottomMargin - image_slide_width;
		image_rl4.rightMargin = image_slide_rightMargin;
		mRelativeLayout.addView(tv_jiesuojifen, image_rl4);

		imageView_down = new ImageView(this);
		imageView_down.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
		imageView_down.setImageResource(R.drawable.update_arrows_down);
		RelativeLayout.LayoutParams image_rl5 = new RelativeLayout.LayoutParams(80, 80);
		image_rl5.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		image_rl5.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
		image_rl5.bottomMargin = 0;
		mRelativeLayout.addView(imageView_down, image_rl5);

		imageView_up = new ImageView(this);
		imageView_up.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
		imageView_up.setImageResource(R.drawable.update_arrows_up);
		RelativeLayout.LayoutParams image_rl6 = new RelativeLayout.LayoutParams(80, 80);
		image_rl6.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		image_rl6.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
		image_rl6.topMargin = 0;
		mRelativeLayout.addView(imageView_up, image_rl6);

		imageView_slide = new ImageView(this);
		imageView_slide.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
		imageView_slide.setImageResource(R.drawable.lock_slide_icon_normal_no_quick_launcher);
		RelativeLayout.LayoutParams image_slide = new RelativeLayout.LayoutParams(image_slide_width, image_slide_width);
		image_slide.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		image_slide.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
		image_slide.bottomMargin = slideAndTextBottomMargin;
		mRelativeLayout.addView(imageView_slide, image_slide);

		imageView_slide.setOnTouchListener(imageTouchListener);

	}

	/**
	 * @Description 设置数据
	 * @author Created by qinxianyuzou on 2015-1-22.
	 */
	private void setData() {
		if (!StringUtils.isEmpty(SharedPreferenceUtil.getInstance(activity).getString(SharedPreferenceUtil.ADCACHE))) {
			ResponseLockADList msgInfo = new Gson().fromJson(
					SharedPreferenceUtil.getInstance(activity).getString(SharedPreferenceUtil.ADCACHE),
					ResponseLockADList.class);
			listDataEntities = msgInfo.getData();
			listCount = listDataEntities.size();
			if (listCount <= 1) {
				imageView_up.setVisibility(View.GONE);
				imageView_down.setVisibility(View.GONE);
			} else {
				imageView_up.setVisibility(View.GONE);
				imageView_down.setVisibility(View.VISIBLE);
			}
			LogUtil.d(setTag(), "setData");
			if (listCount > 0) {
				tv_appJiFen.setText(listDataEntities.get(currentRow).getEarn_jifen());
			}
			ArrayList<View> views = new ArrayList<>();
			adapter2 = new LockAD_PagerAdapter(this);
			for (int i = 0; i < listDataEntities.size(); i++) {
				LayoutInflater inflater = LayoutInflater.from(this);
				View view = inflater.inflate(R.layout.activity_lock_img, null);
				ImageView iv_lock_img = (ImageView) view.findViewById(R.id.iv_lock_img);
				ProgressBar progressBar1 = (ProgressBar) view.findViewById(R.id.progressBar1);
				// PublicUtil.loadNetImage(aQuery, iv_lock_img,
				// listDataEntities.get(i).getHp_url(),
				// R.drawable.launch_bg);
				PublicUtil.loadNetImage(aQuery, progressBar1, iv_lock_img, listDataEntities.get(i).getHp_url(),
						R.drawable.launch_bg);
				views.add(view);
			}
			adapter2.setData(views);
			verticalViewPager1 = (VerticalViewPager) findViewById(R.id.verticalViewPager1);
			verticalViewPager1.setAdapter(adapter2);
			verticalViewPager1.setOnPageChangeListener(new OnPageChangeListener() {

				@Override
				public void onPageSelected(int position) {
					// TODO Auto-generated method stub
					currentRow = position;
					tv_appJiFen.setText(listDataEntities.get(currentRow).getEarn_jifen());
					tv_appJiFen.setText(listDataEntities.get(currentRow).getEarn_jifen());
					if (listCount > 1) {
						if (currentRow == 0) {
							imageView_up.setVisibility(View.GONE);
							imageView_down.setVisibility(View.VISIBLE);
						} else if (currentRow == listCount - 1) {
							imageView_up.setVisibility(View.VISIBLE);
							imageView_down.setVisibility(View.GONE);
						} else {
							imageView_up.setVisibility(View.VISIBLE);
							imageView_down.setVisibility(View.VISIBLE);
						}
					} else {
						imageView_up.setVisibility(View.GONE);
						imageView_down.setVisibility(View.GONE);
					}
				}

				@Override
				public void onPageScrolled(int arg0, float arg1, int arg2) {
					// TODO Auto-generated method stub

				}

				@Override
				public void onPageScrollStateChanged(int arg0) {
					// TODO Auto-generated method stub

				}
			});
		}
	}

	@Override
	protected void initData() {
		// TODO Auto-generated method stub
		getearnlistFlag = Protocol.get_earn_list(activity, setTag(), screenWidth + "", screenHeight + 50 + "");
		setData();
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

	}

	@Override
	public <T> void onHttpSuccess(long flag, JSONObject jsonString, T response) {
		// TODO Auto-generated method stub
		if (getearnlistFlag == flag) {
			ResponseLockADList msgInfo = (ResponseLockADList) response;
			if (msgInfo.getCode().equals(Code.CODE_SUCCESS)) {
				SharedPreferenceUtil.getInstance(activity).putString(SharedPreferenceUtil.ADCACHE,
						jsonString.toString());
				LogUtil.d(setTag(), jsonString.toString());
				if (listDataEntities.size() < 1) {
					setData();
				}
			}
		}
	}

	@Override
	public void onHttpError(long flag, VolleyError error) {
		// TODO Auto-generated method stub

	}

	@Override
	public String setTag() {
		// TODO Auto-generated method stub
		return this.getClass().getSimpleName();
	}

	public List<Map<String, Object>> getMap() {

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < listCount; i++) {
			Map<String, Object> keyValuePair = new HashMap<String, Object>();
			if (i == 0) {
				keyValuePair.put("Image", R.drawable.lock_left_download_icon_normal);
			} else if (i == 1) {
				keyValuePair.put("Image", R.drawable.lock_right_icon_normal);
			} else if (i == 2) {
				keyValuePair.put("Image", R.drawable.lock_slide_icon_normal_no_quick_launcher);
			} else if (i == 3) {
				keyValuePair.put("Image", R.drawable.lock_slide_icon_pressed);
			} else if (i == 4) {
				keyValuePair.put("Image", R.drawable.lock_slide_icon_pressed_no_quick_launcher);
			} else if (i == 5) {
				keyValuePair.put("Image", R.drawable.lock_touched);
			} else if (i == 6) {
				keyValuePair.put("Image", R.drawable.page_back_button_normal);
			} else if (i == 7) {
				keyValuePair.put("Image", R.drawable.update_arrows_down);
			} else if (i == 8) {
				keyValuePair.put("Image", R.drawable.update_arrows_up);
			} else {
				keyValuePair.put("Image", R.drawable.web_back);
			}

			list.add(keyValuePair);
		}
		return list;
	}

	public OnTouchListener imageTouchListener = new OnTouchListener() {

		public boolean onTouch(View v, MotionEvent event) {
			// TODO Auto-generated method stub

			int action = event.getAction();
			Log.i(setTag(), "Touch:" + action);
			switch (action) {
			case MotionEvent.ACTION_DOWN:
				lastX = (int) event.getRawX();
				lastY = (int) event.getRawY();

				break;
			/**
			 * layout(l,t,r,b) l Left position, relative to parent t Top
			 * position, relative to parent r Right position, relative to parent
			 * b Bottom position, relative to parent
			 * */
			case MotionEvent.ACTION_MOVE:
				int dx = (int) event.getRawX() - lastX;
				int dy = (int) event.getRawY() - lastY;

				int left = v.getLeft() + dx;
				int top = v.getTop() + dy;
				int right = v.getRight() + dx;
				int bottom = v.getBottom() + dy;
				imageView_slide.setImageDrawable(getResources().getDrawable(R.drawable.lock_slide_icon_pressed));

				if ((int) event.getRawX() < image_slide_leftMargin + 100) {
					imageView_slide.setImageDrawable(getResources().getDrawable(R.drawable.lock_touched));
					left = image_slide_leftMargin;
					right = left + v.getWidth();
				}
				if ((int) event.getRawX() > screenWidth - image_slide_rightMargin - 100) {
					imageView_slide.setImageDrawable(getResources().getDrawable(R.drawable.lock_touched));
					right = screenWidth - image_slide_rightMargin;
					left = right - v.getWidth();
				}
				v.layout(left, v.getTop(), right, v.getBottom());
				Log.i(setTag(), "position" + left + ", " + top + ", " + right + ", " + bottom);
				lastX = (int) event.getRawX();
				lastY = (int) event.getRawY();
				break;
			case MotionEvent.ACTION_UP:
				lastX = (int) event.getRawX();
				lastY = (int) event.getRawY();

				int dx_down = (int) event.getRawX() - lastX;
				int left_down = v.getLeft() + dx_down;
				int right_down = v.getRight() + dx_down;

				if ((int) event.getRawX() >= (image_slide_leftMargin + 100)
						&& (int) event.getRawX() <= screenWidth - (image_slide_rightMargin + 100)) {
					imageView_slide.setImageResource(R.drawable.lock_slide_icon_normal_no_quick_launcher);
				} else {
					if ((int) event.getRawX() < (image_slide_leftMargin + 100)) {
						if (listDataEntities.get(currentRow).getApp_type() == 0) {
							YouMengUtil.onEvent(activity, YouMengUtil.OPEN_APP_DOWNLOAD);
							Activity_DownloadWeb.luanch(LockActivity.this, listDataEntities.get(currentRow).getId());
						} else {
							Activity_PublicWeb.luanch(activity, listDataEntities.get(currentRow).getTitle(),
									listDataEntities.get(currentRow).getDetail_url());
						}
						if (LockActivity.instance != null) {
							LockActivity.instance.finish();
						}
					} else {
						if (LockActivity.instance != null) {
							LockActivity.instance.finish();
							int amount = 10; // 示例增加100积分
							Protocol.lock_earn(LockActivity.this, this.getClass().getSimpleName(), new HttpCallBack() {

								@Override
								public <T> void onHttpSuccess(long flag, JSONObject jsonString, T response) {
									// TODO Auto-generated method stub
									YouMengUtil.onEvent(activity, YouMengUtil.GET_REWARD);
								}

								@Override
								public void onHttpError(long flag, VolleyError error) {
									// TODO Auto-generated method stub

								}
							});
						}
					}

				}
				break;
			}
			return true;
		}
	};

}
