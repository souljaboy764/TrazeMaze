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

public class HindiActivity extends Activity {
	
	private	LinearLayout root;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_english);
		root= (LinearLayout) findViewById(R.id.main_root);
		LayoutGenerator(0, 10, 6,R.string.hindi_alphabets);
		LayoutGenerator(11,35, 5,R.string.hindi_alphabets);
		LayoutGenerator(36,43, 4,R.string.hindi_alphabets);
		LayoutGenerator(0, 9, 5, R.string.hindi_numbers);
	}
	
	private void LayoutGenerator(int start, int end , int limit , int res_id) {
		
		Resources res = getResources();
		int count=0;
		LinearLayout l= new LinearLayout(HindiActivity.this);
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		
		
		for(int i=start;i<=end;i++){
			final String practice_letter=""+res.getString(res_id).charAt(i); //char to string, final as it is not edited in the duration of its use

			//button for current character
			Button b = new Button(HindiActivity.this);
			b.setText(practice_letter);
			//centering the text in the button
			b.setGravity(Gravity.CENTER);
			b.setLayoutParams(params);
			b.setOnClickListener(new OnClickListener() {
			    public void onClick(View v) {
			    	Intent intent = new Intent(HindiActivity.this, PracticeActivity.class);
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
				l= new LinearLayout(HindiActivity.this);
				count=0;
			}
		}
		
		//if there are any buttons remaining in l
		root.addView(l);
	}
}