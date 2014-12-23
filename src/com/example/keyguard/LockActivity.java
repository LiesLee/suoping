package com.example.keyguard;


import android.R.color;
import android.R.integer;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.KeyguardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.TextureView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

@SuppressLint("ClickableViewAccessibility")
public class LockActivity extends Activity {
	
    int screenWidth;  
    int screenHeight;  
    int lastX;  
    int lastY; 
    int image_slide_width;
    int image_slide_rightMargin;
    int image_slide_leftMargin;
    int image_slide_bottomMargin;
    
    ImageView imageView_slide;
    
    RelativeLayout.LayoutParams image_slide;
    
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);		
		RelativeLayout mRelativeLayout = new RelativeLayout(this);  
	    setContentView(mRelativeLayout); 	 
	    //ExitApplication.getInstance().addActivity(this);
        /*
        RelativeLayout mParent = new RelativeLayout(this); 
        RelativeLayout.LayoutParams parent_rl = new RelativeLayout.LayoutParams(dm.widthPixels-200, dm.heightPixels-200);  
        parent_rl.addRule(RelativeLayout.CENTER_IN_PARENT);  
        mParent.addView(mRelativeLayout ,parent_rl); 
		*/
	    mRelativeLayout.setBackgroundColor(Color.GRAY);
	    
        DisplayMetrics dm = getResources().getDisplayMetrics();  
        screenWidth = dm.widthPixels;  
        screenHeight = dm.heightPixels - 50; 
        image_slide_width = 150;
        image_slide_rightMargin = 80;
        image_slide_leftMargin = 80;
        image_slide_bottomMargin = 250;

        Button mBtnMsgEvent2 = null; 
        mBtnMsgEvent2 = new Button(this);  
        mBtnMsgEvent2.setText("LockActivity");   
        mBtnMsgEvent2.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//loginQQ("1103513011");	
				Toast.makeText(LockActivity.this, "GO to LockActivity!",
					     Toast.LENGTH_SHORT).show();
		        //Intent intent = new Intent(LockScreen.this, MainActivity.class); 
		        //startActivity(intent); 
		        
		        imageView_slide.setImageResource(R.drawable.lock_slide_icon_normal_no_quick_launcher);
			}  
        }); 
        mRelativeLayout.addView(mBtnMsgEvent2);

        ImageView imageView_download;
        imageView_download = new ImageView(this);
        //imageView_download.setBackgroundColor(Color.BLUE);
        imageView_download.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        imageView_download.setImageResource(R.drawable.lock_left_download_icon_normal);
        RelativeLayout.LayoutParams image_rl1 = new RelativeLayout.LayoutParams(image_slide_width, image_slide_width);  
        image_rl1.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);  
        image_rl1.addRule(RelativeLayout.ALIGN_PARENT_LEFT, RelativeLayout.TRUE);  
        image_rl1.bottomMargin = image_slide_bottomMargin;
        image_rl1.leftMargin = image_slide_leftMargin; 
        mRelativeLayout.addView(imageView_download ,image_rl1); 
        
        ImageView imageView_normal;
        imageView_normal = new ImageView(this);
        //imageView_normal.setBackgroundColor(Color.BLUE);
        imageView_normal.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        imageView_normal.setImageResource(R.drawable.lock_right_icon_normal);
        //RelativeLayout.LayoutParams image_rl2 = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);  
        RelativeLayout.LayoutParams image_rl2 = new RelativeLayout.LayoutParams(image_slide_width, image_slide_width);  
        image_rl2.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);  
        image_rl2.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.TRUE);  
        image_rl2.bottomMargin = image_slide_bottomMargin;
        image_rl2.rightMargin = image_slide_rightMargin; 
        mRelativeLayout.addView(imageView_normal ,image_rl2); 
        
        TextView tv1 = new TextView(this);
        tv1.setText("+60");
        tv1.setTextSize(30);
        tv1.setTextColor(Color.WHITE);
        //tv1.setBackgroundColor(Color.RED);
        tv1.setGravity(Gravity.CENTER);
        RelativeLayout.LayoutParams image_rl3 = new RelativeLayout.LayoutParams(image_slide_width, image_slide_width);  
        image_rl3.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);  
        image_rl3.addRule(RelativeLayout.ALIGN_PARENT_LEFT, RelativeLayout.TRUE);  
        image_rl3.bottomMargin = image_slide_bottomMargin-image_slide_width;
        image_rl3.leftMargin = image_slide_leftMargin; 
        mRelativeLayout.addView(tv1 ,image_rl3); 
        
        TextView tv2 = new TextView(this);
        tv2.setText("+2");
        tv2.setTextSize(30);
        tv2.setTextColor(Color.WHITE);
        //tv2.setBackgroundColor(Color.RED);
        tv2.setGravity(Gravity.CENTER);
        RelativeLayout.LayoutParams image_rl4 = new RelativeLayout.LayoutParams(150, 150);  
        image_rl4.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);  
        image_rl4.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.TRUE);  
        image_rl4.bottomMargin =  image_slide_bottomMargin-image_slide_width;
        image_rl4.rightMargin = image_slide_rightMargin; 
        mRelativeLayout.addView(tv2 ,image_rl4); 
        
        ImageView imageView_down;
        imageView_down = new ImageView(this);
        //imageView_down.setBackgroundColor(Color.BLUE);
        imageView_down.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        imageView_down.setImageResource(R.drawable.update_arrows_down);
        RelativeLayout.LayoutParams image_rl5 = new RelativeLayout.LayoutParams(80,80);  
        image_rl5.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);  
        image_rl5.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);  
        image_rl5.bottomMargin = 0;
        mRelativeLayout.addView(imageView_down ,image_rl5); 
        
        ImageView imageView_up;
        imageView_up = new ImageView(this);
        //imageView_up.setBackgroundColor(Color.BLUE);
        imageView_up.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        imageView_up.setImageResource(R.drawable.update_arrows_up);
        RelativeLayout.LayoutParams image_rl6 = new RelativeLayout.LayoutParams(80,80);  
        image_rl6.addRule(RelativeLayout.ALIGN_PARENT_TOP);  
        image_rl6.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);  
        image_rl6.topMargin = 0;
        mRelativeLayout.addView(imageView_up ,image_rl6); 
        
        
        imageView_slide = new ImageView(this);
        //imageView_slide.setBackgroundColor(Color.BLUE);
        imageView_slide.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        imageView_slide.setImageResource(R.drawable.lock_slide_icon_normal_no_quick_launcher);
        //RelativeLayout.LayoutParams image_rl2 = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);  
        RelativeLayout.LayoutParams image_slide = new RelativeLayout.LayoutParams(image_slide_width, image_slide_width);  
        image_slide.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);  
        image_slide.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);  
        image_slide.bottomMargin = image_slide_bottomMargin;
        //image_rl7.rightMargin = 80; 
        mRelativeLayout.addView(imageView_slide ,image_slide); 
        
        //imageView_slide.setOnTouchListener(LockScreen.this);  

        
        //image_slide= new RelativeLayout.LayoutParams(150, 150);
        imageView_slide.setOnTouchListener(imageTouchListener); 
        
        /*
        mMianLayout = (VerticalLinearLayout) findViewById(R.id.id_main_ly);  
        //mMianLayout = new VerticalLinearLayout(this, null);
        mMianLayout.setBackgroundColor(Color.GREEN);
        mMianLayout.setOnPageChangeListener(new OnPageChangeListener()  
        {  
            @Override  
            public void onPageChange(int currentPage)  
            {  
//              mMianLayout.getChildAt(currentPage);  
                Toast.makeText(LockActivity.this, "绗�+(currentPage+1)+"椤�, Toast.LENGTH_SHORT).show();  
            }  
        });  
        RelativeLayout.LayoutParams image_Vertical = new RelativeLayout.LayoutParams(500, 500);  
        image_Vertical.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);  
        image_Vertical.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);  
        image_Vertical.bottomMargin = image_slide_bottomMargin;
        //image_rl7.rightMargin = 80; 
        mRelativeLayout.addView(mMianLayout ,image_Vertical); 
        */
	}
	
	public OnTouchListener imageTouchListener = new OnTouchListener(){
	
		public boolean onTouch(View v, MotionEvent event) {  
	        // TODO Auto-generated method stub  
			
			 
	        int action=event.getAction();  
	        Log.i("@@@@@@", "Touch:"+action);  
	        switch(action){  
	        case MotionEvent.ACTION_DOWN:  
	            lastX = (int) event.getRawX();  
	            lastY = (int) event.getRawY();  

	            break;  
	            /** 
	             * layout(l,t,r,b) 
	             * l  Left position, relative to parent  
	            t  Top position, relative to parent  
	            r  Right position, relative to parent  
	            b  Bottom position, relative to parent   
	             * */  
	        case MotionEvent.ACTION_MOVE:  
	            int dx =(int)event.getRawX() - lastX;  
	            int dy =(int)event.getRawY() - lastY;  
	          
	            int left = v.getLeft() + dx;  
	            int top = v.getTop() + dy;  
	            int right = v.getRight() + dx;  
	            int bottom = v.getBottom() + dy;    
                //imageView_slide.setImageResource(R.drawable.lock_slide_icon_pressed);
	            imageView_slide.setImageDrawable(getResources().getDrawable(R.drawable.lock_slide_icon_pressed));

	            if((int)event.getRawX() < image_slide_leftMargin+100){  
	                imageView_slide.setImageDrawable(getResources().getDrawable(R.drawable.lock_touched));
	                left = image_slide_leftMargin;  
	                right = left + v.getWidth();  
	                //imageView_slide.setImageResource(R.drawable.lock_touched);
	                //v.layout(left,v.getTop(), right,v.getBottom());
	                //break;
	            }                     
	            if((int)event.getRawX()> screenWidth-image_slide_rightMargin-100){  
	                imageView_slide.setImageDrawable(getResources().getDrawable(R.drawable.lock_touched));
	                right = screenWidth-image_slide_rightMargin;  
	                left = right - v.getWidth();  
	                //imageView_slide.setImageResource(R.drawable.lock_touched);
	                //v.layout(left,v.getTop(), right,v.getBottom());
	                //imageView_slide.setX(left);
	                //break;
	                //finish();
	            }  /*                   
	            if(top < 0){  
	                top = 0;  
	                bottom = top + v.getHeight();  
	            }                     
	            if(bottom > screenHeight){  
	                bottom = screenHeight;  
	                top = bottom - v.getHeight();  
	            } */        
        		//璁╂寜閽殢鐫�Е鎺х瑪鐨勭Щ鍔ㄤ竴璧风Щ鍔�
	            v.layout(left,v.getTop(), right,v.getBottom());  
	            Log.i("@@@@@@", "position锟斤拷" + left +", " + top + ", " + right + ", " + bottom);     
	            lastX = (int) event.getRawX();  
	            lastY = (int) event.getRawY();                    
	            break;  
	        case MotionEvent.ACTION_UP:
	            lastX = (int) event.getRawX();  
	            lastY = (int) event.getRawY();  
	            
	           // image_slide.rightMargin = lastX;
	            int dx_down =(int)event.getRawX() - lastX; 
	            int left_down = v.getLeft() + dx_down;  
	            int right_down = v.getRight() + dx_down;  
                    
	            if((int)event.getRawX() >= (image_slide_leftMargin+100)&&(int)event.getRawX()<=screenWidth-(image_slide_rightMargin+100)){  
	        		//璁╂寜閽洖鍒颁腑蹇冨 
		            //v.layout((screenWidth-image_slide_width)/2,v.getTop(), (screenWidth+image_slide_width)/2,v.getBottom());  
	                imageView_slide.setImageResource(R.drawable.lock_slide_icon_normal_no_quick_launcher);
	            }else {
					//finish();
	            	if ((int)event.getRawX() < (image_slide_leftMargin+100)) {
	            		Toast.makeText(LockActivity.this, "GO to DownLoadWebActivity!",
							     Toast.LENGTH_SHORT).show();
				        Intent intent = new Intent(LockActivity.this, DownLoadWebActivity.class); 
				        startActivity(intent);
					}else {
						//ExitApplication.getInstance().exit(LockActivity.this);
						
						Intent mIntent = new Intent("shut_down");                    
						//鍙戦�骞挎挱  
						sendBroadcast(mIntent);
						finish();
					}
					 
				}
	            break;                
	        }  
	        return true;     
		}  
	};
}
