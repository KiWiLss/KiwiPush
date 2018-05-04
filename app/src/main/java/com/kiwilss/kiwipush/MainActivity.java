package com.kiwilss.kiwipush;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void sendBroadcast(View view) {
        //askForPermission();
        //WindowUtils.showPopupWindow(this);

        //gotoBroadcast();

        //不申请权限,显示悬浮窗
        showDialogNoRequest();
    }

    private void showDialogNoRequest() {

        TextView textView = new TextView(this);
        textView.setGravity(Gravity.CENTER);
        textView.setBackgroundColor(Color.BLACK);
        textView.setText("zhang phil @ csdn");
        textView.setTextSize(10);
        textView.setTextColor(Color.RED);

        //类型是TYPE_TOAST，像一个普通的Android Toast一样。这样就不需要申请悬浮窗权限了。
        WindowManager.LayoutParams params = new WindowManager.LayoutParams(WindowManager.LayoutParams.TYPE_TOAST);

        //初始化后不首先获得窗口焦点。不妨碍设备上其他部件的点击、触摸事件。
        params.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;

        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        params.height = 300;
        //params.gravity=Gravity.BOTTOM;

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplication(), "不需要权限的悬浮窗实现", Toast.LENGTH_LONG).show();
            }
        });

        WindowManager windowManager = (WindowManager) getApplication().getSystemService(WINDOW_SERVICE);
        windowManager.addView(textView, params);
    }

    /**
     * 请求用户给予悬浮窗的权限
     */
    @RequiresApi(api = Build.VERSION_CODES.M)
    public void askForPermission() {
        if (!Settings.canDrawOverlays(this)) {
            Toast.makeText(this, "当前无权限，请授权！", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION);
            intent.setData(Uri.parse("package:" + getPackageName()));
            startActivityForResult(intent, 1);
        } else {
            Toast.makeText(this, "当前有权限！", Toast.LENGTH_SHORT).show();
            gotoBroadcast();
        }
    }

    private void gotoBroadcast() {
        Intent intent = new Intent();
        intent.setAction("com.broadcast.myreceiver");
        //携带数据
        intent.putExtra("key","hello");
        //发送广播
        sendBroadcast(intent);
    }

    /**
     * 用户返回
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    @RequiresApi(api = Build.VERSION_CODES.M)
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            if (!Settings.canDrawOverlays(this)) {
                Toast.makeText(this, "权限授予失败，无法开启悬浮窗", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "权限授予成功！", Toast.LENGTH_SHORT).show();
                //启动FxService
                gotoBroadcast();
            }

        }
    }

}
