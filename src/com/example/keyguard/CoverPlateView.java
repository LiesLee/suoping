package com.example.keyguard;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

public class CoverPlateView extends View {
	
	private GestureDetector mGD;

	public CoverPlateView(Context context) {
		super(context);
	       mGD = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {    
			    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
			    float velocityY) {
			        float x = e2.getX() - e1.getX();
			        float y = e2.getY() - e1.getY();
			        //限制必须得划过屏幕的1/4才能算划过
			        DisplayMetrics dm = getResources().getDisplayMetrics();  
			        float x_limit = dm.widthPixels / 6;
			        float y_limit = dm.heightPixels / 6;
			        float x_abs = Math.abs(x);
			        float y_abs = Math.abs(y);
			        if(x_abs >= y_abs){
			            //gesture left or right
			            if(x > x_limit || x < -x_limit){
			                if(x>0){
			                    //right
			                	//Log.d("手势判断", "GESTURE_RIGHT");
			                    if (mListener != null) {
			                        mListener.update("GESTURE_RIGHT");
			                    }
			                }else if(x<=0){
			                    //left
			                	//Log.d("手势判断", "GESTURE_LEFT");
			                    if (mListener != null) {
			                        mListener.update("GESTURE_LEFT");
			                    }
			                }
			            }
			        }else{
			            //gesture down or up
			            if(y > y_limit || y < -y_limit){
			                if(y>0){
			                    //down
			                	//Log.d("手势判断", "GESTURE_DOWN");
			                    if (mListener != null) {
			                        mListener.update("GESTURE_DOWN");
			                    }
			                }else if(y<=0){
			                    //up
			                	//Log.d("手势判断", "GESTURE_UP");
			                    if (mListener != null) {
			                        mListener.update("GESTURE_UP");
			                    }
			                }
			            }
			        }
			        return true;
			    }	
           });    
   }    
 /*提示大家上面仅仅探测了Fling动作仅仅实现了onFling方法，这里相关的还有以下几种方法来实现具体的可以参考我们以前的文章有详细的解释:   
boolean  onDoubleTap(MotionEvent e)    
boolean  onDoubleTapEvent(MotionEvent e)    
boolean  onDown(MotionEvent e)    
void  onLongPress(MotionEvent e)    
boolean  onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY)    
void  onShowPress(MotionEvent e)    
boolean  onSingleTapConfirmed(MotionEvent e)    
boolean  onSingleTapUp(MotionEvent e)    
*/   
  //接下来是重点，让我们的View接受触控，需要使用下面两个方法让GestureDetector类去处理onTouchEvent和onInterceptTouchEvent方法。    
   @Override   
   public boolean onTouchEvent(MotionEvent event) {      
       mGD.onTouchEvent(event);    
       return true;    
   }    
   public boolean onInterceptTouchEvent(MotionEvent event) {    
       return mGD.onTouchEvent(event);    
   }   
   
   public interface Listener {
	    public void update(String string);
	}
   private Listener mListener;
   public void setListener(Listener listener) {
       mListener = listener;
   }
}
