package com.kiwilss.kiwipush;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.alibaba.sdk.android.push.CloudPushService;
import com.alibaba.sdk.android.push.CommonCallback;
import com.alibaba.sdk.android.push.noonesdk.PushServiceFactory;

/**
 * FileName: MyApp
 *
 * @author : Lss kiwilss
 * e-mail : kiwilss@163.com
 * time   : 2018/5/3
 * desc   : ${DESCRIPTION}
 * Description: ${DESCRIPTION}
 */
public class MyApp extends Application {

    public static final String TAG="MMM";

    @Override
    public void onCreate() {
        super.onCreate();

        initCloudChannel(this);



        // 开启log，要放到前面才能看到初始化过程的log
        if (BuildConfig.DEBUG) {
            //Router.openLog();
        }
        // 初始化
        //Router.initialize(this);

    }

    /**
     * 初始化云推送通道
     * @param applicationContext
     */
    private void initCloudChannel(Context applicationContext) {
        PushServiceFactory.init(applicationContext);
        CloudPushService pushService = PushServiceFactory.getCloudPushService();
        pushService.register(applicationContext, new CommonCallback() {
            @Override
            public void onSuccess(String response) {
                Log.d(TAG, "init cloudchannel success");
            }
            @Override
            public void onFailed(String errorCode, String errorMessage) {
                Log.d(TAG, "init cloudchannel failed -- errorcode:" + errorCode + " -- errorMessage:" + errorMessage);
            }
        });
    }
}
