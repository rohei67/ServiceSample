package com.example.and0701.servicesample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
	Button startButton, stopButton;
	TextView tv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		tv = (TextView)findViewById(R.id.textView);

		startButton = (Button) findViewById(R.id.startButton);
		stopButton = (Button) findViewById(R.id.stopButton);
		startButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getApplicationContext(), MyService.class);
				try {
					int num = Integer.parseInt(tv.getText().toString());
					intent.putExtra("number", num);
					startService(intent);
				} catch (NumberFormatException e) {
					Toast.makeText(getApplicationContext(), "正の数字を入れてください", Toast.LENGTH_SHORT).show();
				}
			}
		});
		stopButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getApplicationContext(), MyService.class);
				stopService(intent);
			}
		});
	}
}
