package com.example.trazemaze2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	
	public void english(View v) {
		Intent intent = new Intent(MainActivity.this, EnglishActivity.class);
        startActivity(intent);
	}
	
	public void hindi(View v) {
		Intent intent = new Intent(MainActivity.this, HindiActivity.class);
        startActivity(intent);
	}
	
	public void arabic(View v) {
		Intent intent = new Intent(MainActivity.this, ArabicActivity.class);
        startActivity(intent);
	}
}