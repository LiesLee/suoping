package com.example.receiver;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.Notification.Builder;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import com.example.keyguard.R;
import com.example.util.JSONUtil;
import com.example.util.LogUtil;
import com.example.util.PublicUtil;
import com.example.xinge.NotificationService;
import com.example.xinge.XGNotification;
import com.tencent.android.tpush.XGIOperateCallback;
import com.tencent.android.tpush.XGPushBaseReceiver;
import com.tencent.android.tpush.XGPushClickedResult;
import com.tencent.android.tpush.XGPushManager;
import com.tencent.android.tpush.XGPushRegisterResult;
import com.tencent.android.tpush.XGPushShowedResult;
import com.tencent.android.tpush.XGPushTextMessage;

/**
 * @Description 信鸽服务
 * @author Created by qinxianyuzou on 2014-11-17.
 */
public class MessageReceiver extends XGPushBaseReceiver {
	private Intent intent = new Intent("com.qq.xgdemo.activity.UPDATE_LISTVIEW");
	public static final String LogTag = "TPushReceiver";
	/** 上一次的跳转内容 */
	private int oldJump;

	// 通知展示
	@Override
	public void onNotifactionShowedResult(Context context, XGPushShowedResult notifiShowedRlt) {
		if (context == null || notifiShowedRlt == null) {
			return;
		}
		XGNotification notific = new XGNotification();
		notific.setMsg_id(notifiShowedRlt.getMsgId());
		notific.setTitle(notifiShowedRlt.getTitle());
		notific.setContent(notifiShowedRlt.getContent());
		// notificationActionType==1为Activity，2为url，3为intent
		notific.setNotificationActionType(notifiShowedRlt.getNotificationActionType());
		// Activity,url,intent都可以通过getActivity()获得
		notific.setActivity(notifiShowedRlt.getActivity());
		notific.setUpdate_time(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime()));
		NotificationService.getInstance(context).save(notific);
		context.sendBroadcast(intent);
		LogUtil.d(LogTag, "您有1条新消息, " + "通知被展示 ， " + notifiShowedRlt.toString());
	}

	@Override
	public void onUnregisterResult(Context context, int errorCode) {
		if (context == null) {
			return;
		}
		String text = null;
		if (errorCode == XGPushBaseReceiver.SUCCESS) {
			text = "反注册成功";
		} else {
			text = "反注册失败" + errorCode;
		}
		LogUtil.d(LogTag, text);
	}

	@Override
	public void onSetTagResult(Context context, int errorCode, String tagName) {
		if (context == null) {
			return;
		}
		String text = null;
		if (errorCode == XGPushBaseReceiver.SUCCESS) {
			text = "\"" + tagName + "\"设置成功";
		} else {
			text = "\"" + tagName + "\"设置失败,错误码：" + errorCode;
		}
		LogUtil.d(LogTag, text);

	}

	@Override
	public void onDeleteTagResult(Context context, int errorCode, String tagName) {
		if (context == null) {
			return;
		}
		String text = null;
		if (errorCode == XGPushBaseReceiver.SUCCESS) {
			text = "\"" + tagName + "\"删除成功";
		} else {
			text = "\"" + tagName + "\"删除失败,错误码：" + errorCode;
		}
		LogUtil.d(LogTag, text);
	}

	// 通知点击回调 actionType=1为该消息被清除，actionType=0为该消息被点击
	@Override
	public void onNotifactionClickedResult(Context context, XGPushClickedResult message) {
		if (context == null || message == null) {
			return;
		}
		String text = null;
		if (message.getActionType() == XGPushClickedResult.NOTIFACTION_CLICKED_TYPE) {
			// 通知在通知栏被点击啦。。。。。
			// APP自己处理点击的相关动作
			// 这个动作可以在activity的onResume也能监听，请看第3点相关内容
			text = "通知被打开 :" + message;
		} else if (message.getActionType() == XGPushClickedResult.NOTIFACTION_DELETED_TYPE) {
			// 通知被清除啦。。。。
			// APP自己处理通知被清除后的相关动作
			text = "通知被清除 :" + message;
		}

		LogUtil.d(LogTag, "广播接收到通知被点击:" + message.toString());
		// 获取自定义key-value
		String customContent = message.getCustomContent();
		if (customContent != null && customContent.length() != 0) {
			try {
				JSONObject obj = new JSONObject(customContent);
				// key1为前台配置的key
				if (!obj.isNull("key")) {
					String value = obj.getString("key");
					LogUtil.d(LogTag, "get custom value:" + value);
				}
				// ...
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		// APP自主处理的过程。。。
		LogUtil.d(LogTag, text);
	}

	@Override
	public void onRegisterResult(Context context, int errorCode, XGPushRegisterResult message) {
		// TODO Auto-generated method stub
		if (context == null || message == null) {
			return;
		}
		String text = null;
		if (errorCode == XGPushBaseReceiver.SUCCESS) {
			text = message + "注册成功";
			// 在这里拿token
			String token = message.getToken();
		} else {
			text = message + "注册失败，错误码：" + errorCode;
		}
		LogUtil.d(LogTag, text);
	}

	// 消息透传
	@Override
	public void onTextMessage(Context context, XGPushTextMessage message) {
		// TODO Auto-generated method stub
		if (message == null) {
			return;
		}
		try {
			if (PublicUtil.getCookies(context) == null || PublicUtil.getCookies(context).equals("")) {
				// 如果没有登录就反注册信鸽
				XGPushManager.unregisterPush(context, new XGIOperateCallback() {

					@Override
					public void onSuccess(Object arg0, int arg1) {
						// TODO Auto-generated method stub
						LogUtil.d(LogTag, "反注册成功");
					}

					@Override
					public void onFail(Object arg0, int arg1, String arg2) {
						// TODO Auto-generated method stub
						LogUtil.d(LogTag, "反注册失败编码：" + arg1);
					}
				});
				return;
			}
			String text = "收到消息:" + message.toString();
			// 获取自定义key-value
			String customContent = message.getCustomContent();
			if (customContent != null && customContent.length() != 0) {
				try {
					JSONObject obj = new JSONObject(customContent);
					String id = JSONUtil.getString(obj, "id", "");
					String msgId = JSONUtil.getString(obj, "msgId", "");
					int type = JSONUtil.getInt(obj, "type", -1);
					int jump = getJump(type);
					String remark = JSONUtil.getString(obj, "remark", "");
					String title = message.getTitle();
					String content = message.getContent();
					LogUtil.d(LogTag, id + "--" + jump + "--" + remark);
					// 创建通知
					Notification nf;
					NotificationManager nm = (NotificationManager) context
							.getSystemService(Context.NOTIFICATION_SERVICE);
					LogUtil.d(LogTag, jump + "");
					if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.JELLY_BEAN) {
						nf = oldNotification(context, title, content, msgId, jump, id, remark);
					} else {
						nf = v16Notification(context, title, content, msgId, jump, id, remark);
					}
					int noticeId;
					noticeId = jump * 10000000;
					noticeId = noticeId + Math.abs(id.hashCode());
					nm.notify(noticeId, nf);
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
			// APP自主处理消息的过程...
			LogUtil.d(LogTag, text);
		} catch (Exception e) {
			// TODO: handle exception
			LogUtil.w(LogTag, e.toString());
		}
	}

	/**
	 * @Description 4.1以前的版本创建通知
	 * @author Created by qinxianyuzou on 2014-11-25.
	 * @param context
	 * @param title
	 *            标题
	 * @param content
	 *            内容
	 * @param jump
	 *            跳转页面的编码
	 * @param id
	 * @param remark
	 *            楼层
	 * @return
	 */
	private Notification oldNotification(Context context, String title, String content, String msgId, int jump,
			String id, String remark) {
		Notification nf;
		nf = new Notification(R.drawable.ic_launcher, title, System.currentTimeMillis());
		nf.flags = Notification.FLAG_AUTO_CANCEL;
		nf.defaults = Notification.DEFAULT_ALL;
		// Intent it = new Intent();
		// Intent it = new Intent(context, MainFramgentActivity.class);
		// it.putExtra("jump", jump);
		// int requestCode = jump;
		// if (!StringUtils.isEmpty(msgId)) {
		// it.putExtra("msgId", msgId);
		// }
		// if (!StringUtils.isEmpty(id)) {
		// it.putExtra("id", id);
		// }
		// if (!StringUtils.isEmpty(remark)) {
		// it.putExtra("remark", remark);
		// }
		// it.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);// 这行代码会解决此问题
		// PendingIntent pi = PendingIntent.getActivity(context, requestCode,
		// it, PendingIntent.FLAG_UPDATE_CURRENT);
		// nf.setLatestEventInfo(context, title, content, pi);
		return nf;
	}

	/**
	 * @Description 4.1版本创建通知
	 * @author Created by qinxianyuzou on 2014-11-25.
	 * @param context
	 * @param title
	 *            标题
	 * @param content
	 *            内容
	 * @param jump
	 *            跳转页面的编码
	 * @param id
	 * @param remark
	 *            楼层
	 * @return
	 */
	@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
	private Notification v16Notification(Context context, String title, String content, String msgId, int jump,
			String id, String remark) {
		Notification nf;
		Builder builder = new Builder(context);
		builder.setContentTitle(title);
		builder.setContentText(content);
		builder.setSmallIcon(R.drawable.ic_launcher);
		builder.setWhen(System.currentTimeMillis());
		// Intent it = new Intent(context, MainFramgentActivity.class);
		// it.putExtra("jump", jump);
		// int requestCode = jump;
		// if (!StringUtils.isEmpty(msgId)) {
		// it.putExtra("msgId", msgId);
		// }
		// if (!StringUtils.isEmpty(id)) {
		// it.putExtra("id", id);
		// }
		// if (!StringUtils.isEmpty(remark)) {
		// it.putExtra("remark", remark);
		// }
		// it.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);// 这行代码会解决此问题
		// builder.setContentIntent(PendingIntent.getActivity(context,
		// requestCode, it, PendingIntent.FLAG_UPDATE_CURRENT));
		nf = builder.build();
		nf.flags = Notification.FLAG_AUTO_CANCEL;
		nf.defaults = Notification.DEFAULT_ALL;
		return nf;
		/*
		 * 安卓通知消息新机制备用代码 Notification noti = new Notification.Builder(context)
		 * .setContentTitle(title) .setContentText(content)
		 * .setSmallIcon(R.drawable.ic_launcher) .setLargeIcon(new
		 * AQuery(context).getCachedImage(R.drawable.ic_launcher))
		 * .setWhen(System.currentTimeMillis())
		 * .setContentIntent(PendingIntent.getActivity(context, noticeId, it,
		 * PendingIntent.FLAG_UPDATE_CURRENT)) .build(); noti.flags =
		 * Notification.FLAG_AUTO_CANCEL; noti.defaults =
		 * Notification.DEFAULT_ALL; noti.category =
		 * Notification.CATEGORY_MESSAGE;
		 */
	}

	/**
	 * @Description 获取要跳转的页面.
	 * @author Created by qinxianyuzou on 2014-11-25.
	 * @param type
	 *            1私信,2评论话题，3点赞，4解答疑问， 5被转发，6圈子激活成功， 7子激活失败，
	 *            8系统公告,9圈子激活中，10班级更新提醒，11关注,12申请加入班级审核失败,
	 *            13评论回复，14回复提问，15发布家长会，16提醒签到，17签到，18;家长会回复， 19发布了分享，20分享回复
	 *            29赞帖子时，帖子的作者会收到30赞帖子回复时，会收到
	 * @return
	 */
	private int getJump(int type) {
		switch (type) {
		case 1:
		case 2:
		case 3:
		case 4:
		case 5:
		case 7:
		case 8:
		case 9:
		case 10:
		case 11:
		case 12:
		case 13:
		case 14:
		case 29:
		case 30:
			return 1;
		case 6:
		case 22:
			return 0;
		case 15:
		case 16:
		case 17:
		case 18:
		case 21:
			return 2;
		case 19:
		case 20:
			return 3;
		case 23:
		case 24:
		case 25:
		case 26:
		case 27:
			return 4;
		case 28:
			return 5;
		default:
			return 0;
		}
	}
}
