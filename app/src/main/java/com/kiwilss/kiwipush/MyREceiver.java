package com.kiwilss.kiwipush;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.WindowManager;

import com.alibaba.sdk.android.push.MessageReceiver;
import com.alibaba.sdk.android.push.notification.CPushMessage;

import java.util.Map;

/**
 * FileName: MyREceiver
 *
 * @author : Lss kiwilss
 * e-mail : kiwilss@163.com
 * time   : 2018/5/3
 * desc   : ${DESCRIPTION}
 * Description: ${DESCRIPTION}
 */
public class MyREceiver extends MessageReceiver {

    public static final String TAG = "MMM";

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);

        String key = intent.getStringExtra("key");
        Log.e(TAG, "onReceive: "+key );

        //WindowUtils.showFrame(context);
        AlertDialog.Builder dialogBuilder =  new AlertDialog.Builder(context);
                 dialogBuilder.setTitle("下线通知");
                dialogBuilder.setMessage("你的账户在其他地方登录 Over");
                 dialogBuilder.setCancelable(false);
                 dialogBuilder.setPositiveButton("重新登录",
                                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.e(TAG, "onClick: " );
                                    }
                });
                 AlertDialog alertDialog = dialogBuilder.create();
                 alertDialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
                 alertDialog.show();


    }

    /**接收消息时调用
     * @param context
     * @param cPushMessage
     */
    @Override
    protected void onMessage(Context context, CPushMessage cPushMessage) {
        super.onMessage(context, cPushMessage);
        Log.e(TAG, "onMessage: "+cPushMessage.toString() );
        Log.e(TAG, "onMessage: "+cPushMessage.getContent() );
    }

    /**接收通知时调用
     * @param context
     * @param s 通知标题
     * @param s1 通知内容
     * @param map
     */
    @Override
    protected void onNotification( Context context, String s, String s1, Map<String, String> map) {
        super.onNotification(context, s, s1, map);
        Log.e(TAG, "onNotification: "+s+"|||"+s1 );


    }

    /**点击通知栏时调用
     * @param context
     * @param s 通知标题
     * @param s1 通知内容
     * @param s2
     */
    @Override
    protected void onNotificationOpened(Context context, String s, String s1, String s2) {
        super.onNotificationOpened(context, s, s1, s2);
        Log.e(TAG, "onNotificationOpened: "+s+"||"+s1+"||"+s2);

    }
}
