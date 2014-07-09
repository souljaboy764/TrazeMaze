package com.example.trazemaze2;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;

public class ArabicActivity extends Activity {
	
	private	LinearLayout root;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_english);
		root= (LinearLayout) findViewById(R.id.main_root);
		LayoutGenerator(0, 6, 7,R.string.arabic_alphabets);
		LayoutGenerator(7,12, 6,R.string.arabic_alphabets);
		LayoutGenerator(13,18, 6,R.string.arabic_alphabets);
		LayoutGenerator(19,24, 6,R.string.arabic_alphabets);
		LayoutGenerator(25,27, 3,R.string.arabic_alphabets);
		LayoutGenerator(0, 9, 5, R.string.arabic_numbers);
	}
	
	private void LayoutGenerator(int start, int end , int limit , int res_id) {
		
		Resources res = getResources();
		int count=0;
		LinearLayout l= new LinearLayout(ArabicActivity.this);
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		
		
		for(int i=start;i<=end;i++){
			final String practice_letter=""+res.getString(res_id).charAt(2*i); //char to string, final as it is not edited in the duration of its use
			System.out.println(practice_letter);
			//button for current character
			Button b = new Button(ArabicActivity.this);
			b.setText(practice_letter);
			//centering the text in the button
			b.setGravity(Gravity.CENTER);
			b.setLayoutParams(params);
			b.setOnClickListener(new OnClickListener() {
			    public void onClick(View v) {
			    	Intent intent = new Intent(ArabicActivity.this, PracticeActivity.class);
			        intent.putExtra("practice", practice_letter);
			        startActivity(intent);
			    } 
			});
			
			//adding button to view
			l.addView(b);
			count++;
			
			// resetting the LinearLayout if the maximum in that row are reached after adding it to the root layout
			if (count==limit) {
				root.addView(l);
				l= new LinearLayout(ArabicActivity.this);
				count=0;
			}
		}
		
		//if there are any buttons remaining in l
		root.addView(l);
	}
}