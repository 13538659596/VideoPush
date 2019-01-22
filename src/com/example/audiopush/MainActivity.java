package com.example.audiopush;

import com.example.audiopush.jni.PushNative;
import com.example.audiopush.listener.LiveStateChangeListener;
import com.example.audiopush.pusher.LivePusher;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;
import android.os.Build;

public class MainActivity extends Activity implements LiveStateChangeListener{

    private LivePusher live;
    static final String URL = "rtmp://192.168.31.230/live/push";
    //static final String URL = "rtmp://134.175.115.72/live/push";
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SurfaceView surfaceView = (SurfaceView) findViewById(R.id.surface);
		//相机图像的预览
		live = new LivePusher(surfaceView.getHolder());
    }
    
    /**
	 * 开始直播
	 * @param btn
	 */
	public void mStartLive(View view) {
		Button btn = (Button)view;
		if(btn.getText().equals("开始直播")){
			live.startPush(URL, this);
			btn.setText("停止直播");
		}else{
			live.stopPush();
			btn.setText("开始直播");
		}
	}

	/**
	 * 切换摄像头
	 * @param btn
	 */
	public void mSwitchCamera(View btn) {
		live.switchCamera();
	}
	
	private Handler handler = new Handler(){
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case PushNative.CONNECT_FAILED:
				Toast.makeText(MainActivity.this, "连接失败", Toast.LENGTH_SHORT).show();
				//Log.d("jason", "连接失败..");
				break;
			case PushNative.INIT_FAILED:
				Toast.makeText(MainActivity.this, "初始化失败", Toast.LENGTH_SHORT).show();
				break;	
			default:
				break;
			}
		}
	};

	@Override
	public void onError(int code) {
		// TODO Auto-generated method stub
		
	}
}
