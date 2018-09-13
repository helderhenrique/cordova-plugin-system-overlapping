package com.sanches.android.cordova.plugin.systemalertwindowpermission;

import android.app.Service;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import io.ionic.starter.MainActivity;
import io.ionic.starter.R;

public class SystemAlertWindowService extends Service implements View.OnClickListener {

    private WindowManager windowManager;
    private ImageView chatHead;
    MediaPlayer mp;
    @Override public IBinder onBind(Intent intent) {
        // Not used
        return null;
    }


    @Override public void onCreate() {

        super.onCreate();
        mp = MediaPlayer.create(this, R.raw.song);
        if(mp != null) mp.setLooping(false);
        if(mp !- null) mp.start();

        windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);

        chatHead = new ImageView(this);
        chatHead.setImageResource(R.drawable.android_head);
        implementClickListeners();
        WindowManager.LayoutParams params = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_PHONE,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                PixelFormat.TRANSLUCENT);

        params.gravity = Gravity.TOP | Gravity.LEFT;
        params.x = 0;
        params.y = 100;

        windowManager.addView(chatHead, params);
       
    }

    private void implementClickListeners() {
        chatHead.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(SystemAlertWindowService.this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);

        //close the service and remove view from the view hierarchy
        stopSelf();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (chatHead != null) windowManager.removeView(chatHead);
    }
}