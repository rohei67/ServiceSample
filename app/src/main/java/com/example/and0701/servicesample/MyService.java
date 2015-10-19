package com.example.and0701.servicesample;

import android.app.Service;
import android.content.Intent;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service implements Runnable {
	private static final String TAG = "MyService";
	private SoundPool soundPool;
	private int soundId;
	private Thread thread = null;
	private int num;

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		Log.v(TAG, "onCreate");
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		num = intent.getIntExtra("number", 0);
		Log.v(TAG, "onStartCommand" + num);

		soundPool = new SoundPool(10, AudioManager.STREAM_MUSIC, 0);
		soundId = soundPool.load(this, R.raw.sound, 1);

		thread = new Thread(this);
		thread.start();
		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	public void run() {
		for (int i = 0; i < num; i++) {
			Log.v(TAG, "run");
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			soundPool.play(soundId, 1.0f, 1.0f, 1, 0, 1.0f);
		}
		stopSelf();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		Log.v(TAG, "onDestroy");
		if (soundPool != null)
			soundPool.release();
		thread = null;
	}
}
