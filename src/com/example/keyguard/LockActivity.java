package com.example.keyguard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.youmi.android.offers.OffersManager;

import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.example.activity.common.Activity_DownloadWeb;
import com.example.activity.common.Activity_Launch;
import com.example.activity.common.BaseActivity;
import com.example.http.Protocol;
import com.example.keyguard.CoverPlateView.Listener;
import com.example.util.LogUtil;
import com.lidroid.xutils.BitmapUtils;

@SuppressLint("ClickableViewAccessibility")
public class LockActivity extends BaseActivity {

	private int screenWidth;
	private int screenHeight;
	private int lastX;
	private int lastY;
	private int image_slide_width;
	private int image_slide_rightMargin;
	private int image_slide_leftMargin;
	private int image_slide_bottomMargin;

	/** 数据大小 */
	private int listCount;
	/** 当前item */
	private int currentRow;
	private ImageView imageView_slide;

	private RelativeLayout.LayoutParams image_slide;

	public static LockActivity instance = null;
	/** 广告图 */
	private UnRollListView mListView;
	private LockAD_Adapter adapter;
	/** 积分获得的积分数 */
	private TextView tv_appJiFen;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		RelativeLayout mRelativeLayout = new RelativeLayout(this);
		setContentView(mRelativeLayout);
		OffersManager.getInstance(this).onAppLaunch();

		instance = this;

		// ExitApplication.getInstance().addActivity(this);
		/*
		 * RelativeLayout mParent = new RelativeLayout(this);
		 * RelativeLayout.LayoutParams parent_rl = new
		 * RelativeLayout.LayoutParams(dm.widthPixels-200, dm.heightPixels-200);
		 * parent_rl.addRule(RelativeLayout.CENTER_IN_PARENT);
		 * mParent.addView(mRelativeLayout ,parent_rl);
		 */
		mRelativeLayout.setBackgroundColor(Color.GRAY);

		DisplayMetrics dm = getResources().getDisplayMetrics();
		screenWidth = dm.widthPixels;
		screenHeight = dm.heightPixels - 50;
		image_slide_width = 150;
		image_slide_rightMargin = 80;
		image_slide_leftMargin = 80;
		image_slide_bottomMargin = 250;

		RelativeLayout.LayoutParams image_Vertical = new RelativeLayout.LayoutParams(screenWidth
				- image_slide_rightMargin - image_slide_leftMargin,
				(screenHeight - (image_slide_bottomMargin - image_slide_width) * 2) * 10);
		image_Vertical.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		image_Vertical.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		image_Vertical.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
		image_Vertical.bottomMargin = image_slide_bottomMargin - image_slide_width;
		image_Vertical.topMargin = image_slide_bottomMargin - image_slide_width;

		mListView = new UnRollListView(this, null);
		// 去掉分割黑线
		// mListView.setDivider(null);
		listCount = Activity_Launch.lockADList_Entities.size();
		currentRow = 0;
		mListView.setVerticalScrollBarEnabled(false);
		// IMGAdapter adapter = new IMGAdapter(this, getMap(),
		// R.layout.scroll_item, new String[] { "Image" },
		// new int[] { R.id.id_title });
		// adapter.setlineWidth(screenWidth - image_slide_rightMargin -
		// image_slide_leftMargin);
		// adapter.setlineHeight((screenHeight - (image_slide_bottomMargin -
		// image_slide_width) * 2) * 1);
		adapter = new LockAD_Adapter(this, new BitmapUtils(this));
		adapter.setData(Activity_Launch.lockADList_Entities);
		mListView.setSmoothScrollbarEnabled(true);
		mListView.setAdapter(adapter);
		mRelativeLayout.addView(mListView, image_Vertical);

		CoverPlateView coverPlate = new CoverPlateView(this);
		mRelativeLayout.addView(coverPlate, image_Vertical);
		coverPlate.setListener(new Listener() {
			public void update(String string) {

				if (string == "GESTURE_DOWN") {
					if (currentRow > 0) {
						currentRow--;
					}
				} else if (string == "GESTURE_UP") {
					if (currentRow < listCount - 1) {
						currentRow++;
					}
					// mListView.smoothScrollByOffset(-(screenHeight-(
					// image_slide_bottomMargin-image_slide_width)));
					// mListView.smoothScrollToPosition(currentRow);
				}
				mListView.setSelection(currentRow);
				tv_appJiFen.setText(Activity_Launch.lockADList_Entities.get(currentRow).getEarn_jifen());
				Log.d("监听器:", string + "   " + currentRow);
			}
		});

		// Button mBtnMsgEvent2 = null;
		// mBtnMsgEvent2 = new Button(this);
		// mBtnMsgEvent2.setText("LockActivity");
		// mBtnMsgEvent2.setOnClickListener(new OnClickListener() {
		// public void onClick(View v) {
		// // TODO Auto-generated method stub
		// // loginQQ("1103513011");
		// Toast.makeText(LockActivity.this, "GO to LockActivity!",
		// Toast.LENGTH_SHORT).show();
		// // Intent intent = new Intent(LockScreen.this,
		// // MainActivity.class);
		// // startActivity(intent);
		//
		// imageView_slide.setImageResource(R.drawable.lock_slide_icon_normal_no_quick_launcher);
		// }
		// });
		// mRelativeLayout.addView(mBtnMsgEvent2);

		ImageView imageView_download;
		imageView_download = new ImageView(this);
		// imageView_download.setBackgroundColor(Color.BLUE);
		imageView_download.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
		imageView_download.setImageResource(R.drawable.lock_left_download_icon_normal);
		RelativeLayout.LayoutParams image_rl1 = new RelativeLayout.LayoutParams(image_slide_width, image_slide_width);
		image_rl1.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		image_rl1.addRule(RelativeLayout.ALIGN_PARENT_LEFT, RelativeLayout.TRUE);
		image_rl1.bottomMargin = image_slide_bottomMargin;
		image_rl1.leftMargin = image_slide_leftMargin;
		mRelativeLayout.addView(imageView_download, image_rl1);

		ImageView imageView_normal;
		imageView_normal = new ImageView(this);
		// imageView_normal.setBackgroundColor(Color.BLUE);
		imageView_normal.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
		imageView_normal.setImageResource(R.drawable.lock_right_icon_normal);
		// RelativeLayout.LayoutParams image_rl2 = new
		// RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
		// ViewGroup.LayoutParams.WRAP_CONTENT);
		RelativeLayout.LayoutParams image_rl2 = new RelativeLayout.LayoutParams(image_slide_width, image_slide_width);
		image_rl2.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		image_rl2.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.TRUE);
		image_rl2.bottomMargin = image_slide_bottomMargin;
		image_rl2.rightMargin = image_slide_rightMargin;
		mRelativeLayout.addView(imageView_normal, image_rl2);

		tv_appJiFen = new TextView(this);
		tv_appJiFen.setText(Activity_Launch.lockADList_Entities.get(currentRow).getEarn_jifen());
		tv_appJiFen.setTextSize(30);
		tv_appJiFen.setTextColor(Color.WHITE);
		// tv1.setBackgroundColor(Color.RED);
		tv_appJiFen.setGravity(Gravity.CENTER);
		RelativeLayout.LayoutParams image_rl3 = new RelativeLayout.LayoutParams(image_slide_width, image_slide_width);
		image_rl3.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		image_rl3.addRule(RelativeLayout.ALIGN_PARENT_LEFT, RelativeLayout.TRUE);
		image_rl3.bottomMargin = image_slide_bottomMargin - image_slide_width;
		image_rl3.leftMargin = image_slide_leftMargin;
		mRelativeLayout.addView(tv_appJiFen, image_rl3);

		TextView tv2 = new TextView(this);
		tv2.setText("+10");
		tv2.setTextSize(30);
		tv2.setTextColor(Color.WHITE);
		// tv2.setBackgroundColor(Color.RED);
		tv2.setGravity(Gravity.CENTER);
		RelativeLayout.LayoutParams image_rl4 = new RelativeLayout.LayoutParams(150, 150);
		image_rl4.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		image_rl4.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.TRUE);
		image_rl4.bottomMargin = image_slide_bottomMargin - image_slide_width;
		image_rl4.rightMargin = image_slide_rightMargin;
		mRelativeLayout.addView(tv2, image_rl4);

		ImageView imageView_down;
		imageView_down = new ImageView(this);
		// imageView_down.setBackgroundColor(Color.BLUE);
		imageView_down.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
		imageView_down.setImageResource(R.drawable.update_arrows_down);
		RelativeLayout.LayoutParams image_rl5 = new RelativeLayout.LayoutParams(80, 80);
		image_rl5.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		image_rl5.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
		image_rl5.bottomMargin = 0;
		mRelativeLayout.addView(imageView_down, image_rl5);

		ImageView imageView_up;
		imageView_up = new ImageView(this);
		// imageView_up.setBackgroundColor(Color.BLUE);
		imageView_up.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
		imageView_up.setImageResource(R.drawable.update_arrows_up);
		RelativeLayout.LayoutParams image_rl6 = new RelativeLayout.LayoutParams(80, 80);
		image_rl6.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		image_rl6.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
		image_rl6.topMargin = 0;
		mRelativeLayout.addView(imageView_up, image_rl6);

		imageView_slide = new ImageView(this);
		// imageView_slide.setBackgroundColor(Color.BLUE);
		imageView_slide.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
		imageView_slide.setImageResource(R.drawable.lock_slide_icon_normal_no_quick_launcher);
		// RelativeLayout.LayoutParams image_rl2 = new
		// RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
		// ViewGroup.LayoutParams.WRAP_CONTENT);
		RelativeLayout.LayoutParams image_slide = new RelativeLayout.LayoutParams(image_slide_width, image_slide_width);
		image_slide.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		image_slide.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
		image_slide.bottomMargin = image_slide_bottomMargin;
		// image_rl7.rightMargin = 80;
		mRelativeLayout.addView(imageView_slide, image_slide);

		// imageView_slide.setOnTouchListener(LockScreen.this);

		// image_slide= new RelativeLayout.LayoutParams(150, 150);
		imageView_slide.setOnTouchListener(imageTouchListener);

		/*
		 * mMianLayout = (VerticalLinearLayout) findViewById(R.id.id_main_ly);
		 * //mMianLayout = new VerticalLinearLayout(this, null);
		 * mMianLayout.setBackgroundColor(Color.GREEN);
		 * mMianLayout.setOnPageChangeListener(new OnPageChangeListener() {
		 * 
		 * @Override public void onPageChange(int currentPage) { //
		 * mMianLayout.getChildAt(currentPage);
		 * Toast.makeText(LockActivity.this, "绗�+(currentPage+1)+"椤�,
		 * Toast.LENGTH_SHORT).show(); } }); RelativeLayout.LayoutParams
		 * image_Vertical = new RelativeLayout.LayoutParams(500, 500);
		 * image_Vertical.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		 * image_Vertical.addRule(RelativeLayout.CENTER_IN_PARENT,
		 * RelativeLayout.TRUE); image_Vertical.bottomMargin =
		 * image_slide_bottomMargin; //image_rl7.rightMargin = 80;
		 * mRelativeLayout.addView(mMianLayout ,image_Vertical);
		 */
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
			Log.i("@@@@@@", "Touch:" + action);
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
				// imageView_slide.setImageResource(R.drawable.lock_slide_icon_pressed);
				imageView_slide.setImageDrawable(getResources().getDrawable(R.drawable.lock_slide_icon_pressed));

				if ((int) event.getRawX() < image_slide_leftMargin + 100) {
					imageView_slide.setImageDrawable(getResources().getDrawable(R.drawable.lock_touched));
					left = image_slide_leftMargin;
					right = left + v.getWidth();
					// imageView_slide.setImageResource(R.drawable.lock_touched);
					// v.layout(left,v.getTop(), right,v.getBottom());
					// break;
				}
				if ((int) event.getRawX() > screenWidth - image_slide_rightMargin - 100) {
					imageView_slide.setImageDrawable(getResources().getDrawable(R.drawable.lock_touched));
					right = screenWidth - image_slide_rightMargin;
					left = right - v.getWidth();
					// imageView_slide.setImageResource(R.drawable.lock_touched);
					// v.layout(left,v.getTop(), right,v.getBottom());
					// imageView_slide.setX(left);
					// break;
					// finish();
				} /*
				 * if(top < 0){ top = 0; bottom = top + v.getHeight(); }
				 * if(bottom > screenHeight){ bottom = screenHeight; top =
				 * bottom - v.getHeight(); }
				 */
				// 璁╂寜閽殢鐫�Е鎺х瑪鐨勭Щ鍔ㄤ竴璧风Щ鍔�
				v.layout(left, v.getTop(), right, v.getBottom());
				Log.i("@@@@@@", "position锟斤拷" + left + ", " + top + ", " + right + ", " + bottom);
				lastX = (int) event.getRawX();
				lastY = (int) event.getRawY();
				break;
			case MotionEvent.ACTION_UP:
				lastX = (int) event.getRawX();
				lastY = (int) event.getRawY();

				// image_slide.rightMargin = lastX;
				int dx_down = (int) event.getRawX() - lastX;
				int left_down = v.getLeft() + dx_down;
				int right_down = v.getRight() + dx_down;

				if ((int) event.getRawX() >= (image_slide_leftMargin + 100)
						&& (int) event.getRawX() <= screenWidth - (image_slide_rightMargin + 100)) {
					// 璁╂寜閽洖鍒颁腑蹇冨
					// v.layout((screenWidth-image_slide_width)/2,v.getTop(),
					// (screenWidth+image_slide_width)/2,v.getBottom());
					imageView_slide.setImageResource(R.drawable.lock_slide_icon_normal_no_quick_launcher);
				} else {
					// finish();
					if ((int) event.getRawX() < (image_slide_leftMargin + 100)) {
						// Toast.makeText(LockActivity.this,
						// "GO to DownLoadWebActivity!",
						// Toast.LENGTH_SHORT).show();
						// Intent intent = new Intent(LockActivity.this,
						// DownLoadWebActivity.class);
						// startActivity(intent);
						// finish();
						//
						Activity_DownloadWeb.luanch(LockActivity.this,
								Activity_Launch.lockADList_Entities.get(currentRow).getId());
						Activity_YouMi.luanch(LockActivity.this);
						// OffersManager.getInstance(LockActivity.this).showOffersWall();
						// 积分墙配置检查（没有使用“通过 Receiver来获取积分订单”功能）：
						// boolean isSuccess =
						// OffersManager.getInstance(LockActivity.this).checkOffersAdConfig();
						// LogUtil.d(setTag(), "" + isSuccess);
						if (LockActivity.instance != null) {
							LockActivity.instance.finish();
						}
					} else {

						/*
						 * Intent intent = new Intent(LockActivity.this,
						 * MainActivity.class); Bundle bundle = new Bundle();
						 * bundle.putString("Name", "test");
						 * bundle.putBoolean("IsClose", true);
						 * intent.putExtras(bundle); startActivity(intent);
						 */
						/*
						 * if (MainActivity.instance != null) {
						 * MainActivity.instance.finish(); }
						 */
						// if (DownLoadWebActivity.instance != null) {
						// DownLoadWebActivity.instance.finish();
						// }
						if (LockActivity.instance != null) {
							LockActivity.instance.finish();
							int amount = 10; // 示例增加100积分
							// boolean isSuccess =
							// PointsManager.getInstance(activity).awardPoints(amount);
							// LogUtil.d(setTag(), "" + isSuccess);
							Protocol.lock_earn(LockActivity.this, this.getClass().getSimpleName());
						}
					}

				}
				break;
			}
			return true;
		}
	};

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
		OffersManager.getInstance(this).onAppExit();
	}

	@Override
	protected void initUI() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void initData() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

	}

	@Override
	public <T> void onHttpSuccess(long flag, JSONObject jsonString, T response) {
		// TODO Auto-generated method stub

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
}
